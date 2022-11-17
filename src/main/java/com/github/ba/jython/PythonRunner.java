package com.github.ba.jython;

import org.python.core.PyFunction;
import org.python.util.PythonInterpreter;

/**
 * @author wang xiao
 * date 2022/11/17
 */
public class PythonRunner {

    private static final PythonInterpreter intr = new PythonInterpreter();

    private static final String FUNC_TPL = String.join("\n", new String[]{
            "def __call__():",
            "    %s",
            "",
    });

    private final PyFunction func;

    public PythonRunner(String code) {
        // Render the function body
        String[] lines = code.split("\n");
        for (int i = 1; i < lines.length; i++)
            lines[i] = "    " + lines[i];
        code = String.join("\n", lines);
        code = String.format(FUNC_TPL, code);

        intr.exec(code);
        func = (PyFunction) intr.get("__call__");
    }

    public Object run() {
        return func.__call__();
    }
}
