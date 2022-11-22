package com.github.ba.pyengine;

import javax.script.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringWriter;

/**
 * @author wang xiao
 * date 2022/11/22
 */
public class PyEngineTest {

    public static void main(String[] args) throws FileNotFoundException, ScriptException {
        StringWriter writer = new StringWriter();
        ScriptContext context = new SimpleScriptContext();
        context.setWriter(writer);

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("python");
        engine.eval(new FileReader(""), context);

    }
}
