package com.github.ba.graalvm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.oracle.truffle.js.lang.JavaScriptLanguage;
import com.oracle.truffle.js.runtime.JSContextOptions;
import org.apache.commons.lang3.ClassUtils;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.HostAccess;
import org.graalvm.polyglot.Value;
import org.graalvm.polyglot.proxy.ProxyObject;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.SimpleBindings;

public class GraalvmDemo {
    private static Context.Builder ctxBuilder;
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 忽略未定义字段，避免反序列化报错
        OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 避免枚举类型反序列化报错
        OBJECT_MAPPER.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE);
        OBJECT_MAPPER.registerModule(new JavaTimeModule());
        // 关闭 Date默认转成Long
        OBJECT_MAPPER.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        // 不打印warning日志
        System.setProperty("polyglot.engine.WarnInterpreterOnly", "false");
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
                .option(JSContextOptions.EXPERIMENTAL_FOREIGN_OBJECT_PROTOTYPE_NAME, "true")
                .option(JSContextOptions.CONSOLE_NAME, "true")
                .option(JSContextOptions.PRINT_NAME, "true")
                .option(JSContextOptions.UNHANDLED_REJECTIONS_NAME, "throw");
    }

    public static void main(String[] args) throws JsonProcessingException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String source = "module.exports = async function (event, state) {\n\n  let output = new Array()\n  for " +
                "(index in state.apiResp.items) {\n    var item = state.apiResp.items[index]\n    if (item.title == " +
                "'标题3' || item.title == '标题6' || item.title == '标题9') {\n      console.log(111)\n      " +
                "output.push(item)\n    }\n  }\n  state.output = output\n  return state\n}";
        SimpleBindings state = new SimpleBindings();
        state.put("apiResp", OBJECT_MAPPER.readValue(apiResp, Object.class));
        Map<String, Object> result = executeCustomFuncWithLog("jsFunc", formatScript(source),
                state, out);
        System.out.println(result);
        System.out.println("===================");
        System.out.println(out);
    }

    private static final String apiResp = "{\n" +
            "  \"items\": [\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"标题0\",\n" +
            "      \"subscribe\": \"摘要0\",\n" +
            "      \"date\": 1668580875202\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"标题1\",\n" +
            "      \"subscribe\": \"摘要1\",\n" +
            "      \"date\": 1668580875202\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"标题2\",\n" +
            "      \"subscribe\": \"摘要2\",\n" +
            "      \"date\": 1668580875202\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"标题3\",\n" +
            "      \"subscribe\": \"摘要3\",\n" +
            "      \"date\": 1668580875202\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"标题4\",\n" +
            "      \"subscribe\": \"摘要4\",\n" +
            "      \"date\": 1668580875202\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"标题5\",\n" +
            "      \"subscribe\": \"摘要5\",\n" +
            "      \"date\": 1668580875202\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"标题6\",\n" +
            "      \"subscribe\": \"摘要6\",\n" +
            "      \"date\": 1668580875202\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"标题7\",\n" +
            "      \"subscribe\": \"摘要7\",\n" +
            "      \"date\": 1668580875202\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"标题8\",\n" +
            "      \"subscribe\": \"摘要8\",\n" +
            "      \"date\": 1668580875202\n" +
            "    },\n" +
            "    {\n" +
            "      \"id\": 1,\n" +
            "      \"title\": \"标题9\",\n" +
            "      \"subscribe\": \"摘要9\",\n" +
            "      \"date\": 1668580875202\n" +
            "    }\n" +
            "  ],\n" +
            "  \"total\": 200\n" +
            "}";

    private static String formatScript(String source) {
        return source.replaceFirst("module.exports", "var jsFunc")
                .replaceAll("\\(event,[\\s]*state\\)", "(state)");
    }

    /**
     * 执行用户自定义 function 并记录日志，用于 JS 节点
     * 1. 加载用户脚本的func
     * 2. 执行用户自定义func
     *
     * @return 返回一个 Map —— JS 节点执行后返回的一定是对象
     */
    public static Map<String, Object> executeCustomFuncWithLog(
            String func, String script, Object params, OutputStream out) {
        if (out != null) {
            ctxBuilder.out(out).err(out);
        }
        try (Context context = ctxBuilder.build()) {
            Object result = doExec(context, func, script, params);
            return OBJECT_MAPPER.convertValue(result, Map.class);
        }
    }

    /**
     * 执行用户自定义 function，用于 mock API
     */
    public static Map<String, Object> executeCustomFunc(String func, String script, Object params) {

        try (Context context = ctxBuilder.build()) {
            Object result = doExec(context, func, script, params);
            return OBJECT_MAPPER.convertValue(result, Map.class);
        }
    }

    /**
     * 执行用户自定义脚本，用于 HTTP 转发结果返回适配器
     * 和{@link this#executeCustomFuncWithLog}的区别就在于出入参不同
     *
     * @return 返回一个 Object —— 返回结果可能是对象也可能是数组，所以统一用 Object
     */
    public static Object executeCustomFunc(String func, String script, Object params, OutputStream out) {
        if (out != null) {
            ctxBuilder.out(out).err(out);
        }
        try (Context context = ctxBuilder.build()) {
            Object result = doExec(context, func, script, params);
            if (result instanceof Map) {
                return OBJECT_MAPPER.convertValue(result, Map.class);
            }
            return result;
        }
    }

    /**
     * 使用context的写法可以拿到console.log的打印日志，ScriptEngine的方式拿不到
     */
    private static Object doExec(Context context, String func, String script, Object params) {
        try {
            params = covertObject(params);
        } catch (IllegalArgumentException ignore) {
        }

        // 加载用户自定义的 脚本func
        context.eval(JavaScriptLanguage.ID, script);
        // 执行
        Value jsFunc = context.getBindings(JavaScriptLanguage.ID).getMember(func);
        Object result = jsFunc.execute(params).as(Object.class);

        if (jsFunc.getMetaObject().getMetaSimpleName().startsWith("Async")) {
            // async 异步function result 为空，直接返回入参
            return params;
        } else {
            return result;
        }
    }

    /**
     * 深度遍历params参数
     * <p>
     * 只要是数组类型都转成 CustomProxyList
     * 只要是对象类型都转成 CustomProxyObject
     */
    private static Object covertObject(Object params) {
        if (params == null) {
            return null;
        }
        boolean array = params.getClass().isArray()
                || (params instanceof ArrayNode) || (params instanceof Collection);
        boolean basic = ClassUtils.isPrimitiveOrWrapper(params.getClass())
                || params instanceof CharSequence || params instanceof ValueNode || params instanceof Number;

        if (array) {
            List<Object> resParams = new ArrayList<>();
            OBJECT_MAPPER.convertValue(params, ArrayList.class).forEach(member -> {
                resParams.add(covertObject(member));
            });
            return resParams;
        } else if (!basic) {
            try {
                CustomProxyObject resParams = OBJECT_MAPPER.convertValue(params, CustomProxyObject.class);
                resParams.forEach((key, value) -> {
                    Object member = covertObject(value);
                    if (member == resParams) {
                        return;
                    }
                    resParams.put(key, member);
                });
                return resParams;
            } catch (IllegalArgumentException ignored) {
            }
        }
        return params;
    }

    private static class CustomProxyObject extends HashMap<String, Object> implements ProxyObject {
        @Override
        public Object getMember(String key) {
            return ProxyObject.fromMap(this).getMember(key);
        }

        @Override
        public Object getMemberKeys() {
            return ProxyObject.fromMap(this).getMemberKeys();
        }

        @Override
        public boolean hasMember(String key) {
            return ProxyObject.fromMap(this).hasMember(key);
        }

        @Override
        public void putMember(String key, Value value) {
            this.put(key, value.as(Object.class));
        }

        @Override
        public boolean removeMember(String key) {
            return ProxyObject.fromMap(this).removeMember(key);
        }
    }
}
