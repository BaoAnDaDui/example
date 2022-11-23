package com.github.ba.command;

import org.python.apache.commons.compress.utils.IOUtils;

import java.io.IOException;

/**
 * @author wang xiao
 * date 2022/11/23
 */
public class JavaCommandDemo {

    public static void main(String[] args) throws InterruptedException, IOException {
        // 不使用工具类的写法
        Process process = Runtime.getRuntime().exec("cmd /c ping 192.168.1.10");
        // // 阻塞等待完成
        int exitCode = process.waitFor();
        // 状态码0表示执行成功
//        if (exitCode == 0) {
//            String result = IOUtils(process.getInputStream());
//            System.out.println(result);
//        } else {
//            String errMsg = IOUtils.toString(process.getErrorStream());
//            System.out.println(errMsg);
//        }
    }
}
