package com.github.ba.pyexec;

import java.io.IOException;

/**
 * @author wang xiao
 * date 2022/11/29
 */
public class Test {

    public static void main(String[] args) throws IOException {
        String code = " state.res =1  ";
        PythonNode pythonNode = new PythonNode(code);
        Object exec = pythonNode.exec("test", "sss");
        System.out.println(exec);
    }



}
