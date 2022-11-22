package com.github.ba.command;

import java.io.*;

/**
 * @author wang xiao
 * date 2022/11/17
 */
public class JavaRunCommand {
    public static void main(String args[]) throws IOException {

        String Python_Script =
                "print(\"Hello, This is Delftstack.com!The Best Tutorial Site!\")\n";

        BufferedWriter Buffered_Writer = new BufferedWriter(
                new FileWriter("DemoPythonFile.py"));
        Buffered_Writer.write(Python_Script);
        Buffered_Writer.close();

        Process Demo_Process = Runtime.getRuntime().exec("python DemoPythonFile.py");

        BufferedReader Buffered_Reader = new BufferedReader(
                new InputStreamReader(
                        Demo_Process.getInputStream()
                ));
        String s = Buffered_Reader.readLine();
        System.out.println(s);

    }
}
