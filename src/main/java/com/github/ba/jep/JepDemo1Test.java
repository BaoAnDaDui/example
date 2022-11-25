package com.github.ba.jep;

import jep.Interpreter;
import jep.SharedInterpreter;

/**
 * @author wang xiao
 * date 2022/11/25
 */
public class JepDemo1Test {

    public static void main(String[] args) {
        try (Interpreter interp = new SharedInterpreter()) {
            interp.exec("import somePyModule");
            // any of the following work, these are just pseudo-examples

            // using exec(String) to invoke methods
            interp.set("arg", new Object());
            interp.exec("x = somePyModule.foo1(arg)");
            Object result1 = interp.getValue("x");

            // using getValue(String) to invoke methods
            Object result2 = interp.getValue("somePyModule.foo2()");

            // using invoke to invoke methods
            interp.exec("foo3 = somePyModule.foo3");
            Object result3 = interp.invoke("foo3", new Object());
        }
    }
}
