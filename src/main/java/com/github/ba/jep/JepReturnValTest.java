package com.github.ba.jep;

import jep.Interpreter;
import jep.JepException;
import jep.SharedInterpreter;

/**
 * @author wang xiao
 * date 2022/11/25
 */
public class JepReturnValTest {
    public static void main(String[] args) {
        try (Interpreter interp = new SharedInterpreter()) {
            interp.set("user_name", "name");
            interp.exec("result = 'Hi, ' + user_name");
            String output = (String) interp.getValue("result");
            System.out.println(output);
        } catch (JepException e) {
            //do something with exception
        }
    }
}
