package com.github.ba.jython;

import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;

/**
 * @author wang xiao
 * date 2022/11/22
 */
public class JythonZhTest {

    public static void main(String[] args) {
        String code = "import json\n" +
                "print('中文测试')\n" +
                "def parse(data):\n" +
                "    jsonObj = json.loads(data)\n" +
                "    jsonObj[\"职位\"] = \"测试工程师\"\n" +
                "    return json.dumps(jsonObj)";
        PythonInterpreter interpreter = new PythonInterpreter();
        PyString codeStr = Py.newStringUTF8(code);
        interpreter.exec(codeStr);
        PyFunction func = interpreter.get("parse",PyFunction.class);
        String str = "{\"workId\":100,\"userName\":\"张三\"}";
        PyString str2 = Py.newStringUTF8(str);
        PyObject pyObject = func.__call__(str2);
        System.out.println(pyObject);


    }
}



