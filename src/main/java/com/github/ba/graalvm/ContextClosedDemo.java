package com.github.ba.graalvm;

import com.oracle.truffle.js.lang.JavaScriptLanguage;
import com.oracle.truffle.js.runtime.JSContextOptions;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;

import java.io.OutputStream;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author wang xiao
 * date 2022/11/24
 */
public class ContextClosedDemo {

    private  Context.Builder ctxBuilder;

    public ContextClosedDemo() {
        HostAccess hostAccess = HostAccess.newBuilder(HostAccess.ALL)
                // js -> java
                .targetTypeMapping(Date.class, Object.class, null, v -> v)
                .targetTypeMapping(Double.class, Object.class, null, v -> {
                    // 无小数的double转成long，解决Date.now()的问题
                    if (v.longValue() == v) {
                        return v.longValue();
                    } else {
                        return v;
                    }
                })
                .build();
        ctxBuilder = Context.newBuilder(JavaScriptLanguage.ID)
                .allowHostAccess(hostAccess)
                .allowExperimentalOptions(true)
                .option(JSContextOptions.ECMASCRIPT_VERSION_NAME, "2017")
                .option(JSContextOptions.FOREIGN_OBJECT_PROTOTYPE_NAME, "true")
                .option(JSContextOptions.CONSOLE_NAME, "true")
                .option(JSContextOptions.PRINT_NAME, "true")
                .option(JSContextOptions.UNHANDLED_REJECTIONS_NAME, "throw");
    }

    public Object execJs(String jsBody, Object param, String... argNames) {
        String functionName = "graalvmInternal_" + UUID.randomUUID().toString().replace('-', '_');
        String functionJs = formatJS(functionName, jsBody, argNames);
        return   executeCustomJsFuncWithLog(functionName, functionJs, param, null);
    }

    private Object executeCustomJsFuncWithLog(String func, String script, Object params, OutputStream out) {
        if (out != null) {
            ctxBuilder.out(out).err(out);
        }
        try ( Context context = ctxBuilder.build();){
            return doExec(context, func, script, params);
        }
    }


    private  Object doExec(Context context, String func, String script, Object params) {
        context.eval(JavaScriptLanguage.ID, script);
        Value jsFunc = context.getBindings(JavaScriptLanguage.ID).getMember(func);
        return jsFunc.execute(params).as(Object.class);
    }

    private String formatJS(String functionName, String scriptBody, String... argNames) {
        return String.format(JS_TEMPLATE, functionName, argNames[0], scriptBody);
    }

   final String JS_TEMPLATE = "function %s (%s) { " +
            " %s " +
            "}";


    public static void main(String[] args) {
        String js = "state.js2 = {\"name\" : \"我是js2\"}\n" +"return state;";
        Map<String, String> params = new HashMap<>(8);
        System.out.println(Instant.now());
        Object o = new ContextClosedDemo().execJs(js, params,"state");
        System.out.println(Instant.now());
        System.out.println(o);
        System.out.println(Instant.now());
        Object o1 = new ContextClosedDemo().execJs(js, params,"state");
        System.out.println(Instant.now());
        System.out.println(o1);
    }

}
