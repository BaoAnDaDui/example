package com.github.ba.es6;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * es6 测试
 * @author wang xiao
 * date 2022/11/11
 */
public class JsEs6 {

    public static void main(String[] args) throws ScriptException {
        // 开启JVM参数 -Dnashorn.args=--language=es6

        //1..创建引擎
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine engine = scriptEngineManager.getEngineByName("nashorn");

        //2.设置参数
        engine.put("$name", "Tom");

        //3.执行脚本
        //3.1 可以进行 nashorn debug
        String jsFilePath1 = "src/main/resources/hello.js";
        Object eval = engine.eval("load('" + jsFilePath1 + "')");
        System.out.println(eval);
    }
}
