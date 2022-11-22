package com.github.ba.jython;

import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import javax.script.ScriptException;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HashMap;

/**
 * @author wang xiao
 * date 2022/11/17
 */
public class PythonTest {
    public static void main(String[] args) throws ScriptException {
        //首先调用python的解释器
        System.out.println("0"+Instant.now());
        PythonInterpreter interpreter = new PythonInterpreter();
        //选择执行的的Python语句
        String source = "def covert(state):\n" +
                "    return state['id']+1";
        System.out.println("1"+Instant.now());

        interpreter.execfile(new ByteArrayInputStream(source.getBytes(StandardCharsets.UTF_8)));
        System.out.println("2"+Instant.now());

        PyFunction pyFunction = interpreter.get("covert", PyFunction.class);

        HashMap<String,Object> v = new HashMap<String,Object>();
        v.put("id",1);

        PyObject pyObject = pyFunction.__call__(Py.java2py(v));
        System.out.println(pyObject);
        System.out.println("3"+Instant.now());


    }
}
