package com.github.ba.command;

import java.io.*;

/**
 * @author wang xiao
 * date 2022/11/17
 */
public class JavaRunCommand {
    public static void main(String[] args) throws IOException {

        String pythonScript =
                "print(\"Hello, This is Delftstack.com!The Best Tutorial Site!\")\n";

        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter("DemoPythonFile.py"));
        bufferedWriter.write(pythonScript);
        bufferedWriter.close();

        Process demoProcess = Runtime.getRuntime().exec("python DemoPythonFile.py");

        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        demoProcess.getInputStream()
                ));
        String s = bufferedReader.readLine();
        System.out.println(s);

    }
}
