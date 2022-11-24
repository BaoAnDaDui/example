package com.github.ba.graalvm;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

import javax.script.*;
import java.util.function.Predicate;

/**
 * @author wang xiao
 * date 2022/11/24
 */
public class ArrMapTest {

    public static void main(String[] args) throws ScriptException {


        String arrMap = "let result = null\n" +
                "        let callback = %s \n" +
                "        try {\n" +
                "            result = arr.map(callback)\n" +
                "        } catch (error) {\n" +
                "            result = arr\n" +
                "        }";

        String format = String.format(arrMap, "item => (item + 2)");
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
        Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
        bindings.put("polyglot.js.allowHostAccess", true);
        bindings.put("polyglot.js.allowHostClassLookup", (Predicate<String>) s -> true);
        bindings.put("arr", new int[]{1,2,3});
        bindings.put("callback", "");
        Object eval = engine.eval(format);
        String s = JSONObject.toJSONString(eval);
        System.out.println(s);
        System.out.println(JSON.parse(s));
    }
}
