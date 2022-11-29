package com.github.ba.pyexec;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author wang xiao
 * date 2022/11/29
 */
public class PythonNode {


    private final String pythonCode;

    public PythonNode(String pythonCode) {
        this.pythonCode = pythonCode;
    }

    public Object exec(String appId,String serviceId) throws IOException {
        String fileName = String.format("%s_%s.py", appId, serviceId);
        BufferedWriter bufferedWriter = new BufferedWriter(
                new FileWriter(fileName));
        bufferedWriter.write(pythonCode);
        bufferedWriter.close();
        return CmdUtils.execCmd("python " + fileName +" --state=="+ new HashMap<>(8), null);
    }



}
