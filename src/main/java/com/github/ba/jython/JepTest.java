package com.github.ba.jython;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;

/**
 * @author wang xiao
 * date 2022/11/22
 */
public class JepTest {


    public static void main(String[] args) {
        try (Interpreter interp = new SharedInterpreter()) {
            interp.runScript("# -*- coding: utf-8 -*-\n" +
                    "\n" +
                    "def __convert__(state):\n" +
                    "    a = 21\n" +
                    "    b = 10\n" +
                    "    c = 0\n" +
                    "\n" +
                    "    c = a + b\n" +
                    "    output = {\"ss\": \"1-c的值为\"}\n" +
                    "\n" +
                    "    c = a - b\n" +
                    "    output['2 - c 的值为'] = c\n" +
                    "\n" +
                    "    c = a * b\n" +
                    "    output['3 - c 的值为：'] = c\n" +
                    "\n" +
                    "    c = a / b\n" +
                    "    output['4 - c 的值为：'] = c\n" +
                    "\n" +
                    "    c = a % b\n" +
                    "    output['5 - c 的值为：'] = c\n" +
                    "\n" +
                    "    # 修改变量 a 、b 、c\n" +
                    "    a = 2\n" +
                    "    b = 3\n" +
                    "    c = a ** b\n" +
                    "    output['6 - c 的值为：'] = c\n" +
                    "\n" +
                    "    a = 10\n" +
                    "    b = 5\n" +
                    "    c = a // b\n" +
                    "    output['7 - c 的值为：'] = c\n" +
                    "\n" +
                    "    state['output'] = output\n" +
                    "   \n" +
                    "    return  state");
        } catch (JepException e) {
            //do something with exception
        }
    }
}
