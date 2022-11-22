//package com.github.ba.command;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
///**
// * @author wang xiao
// * date 2022/11/17
// */
//public class JavaRunCommand {
//    public static void main(String args[]) {
//        String workDir = "E:/PycharmProjects/pythonProject";
//        CommandLine cmdLine = new CommandLine(env);
//        cmdLine.addArgument(mainpy);
//        cmdLine.addArgument(workDir);
//        cmdLine.addArgument("testdir");
////        Map map = new HashMap();
////        map.put("file", new File(mainpy));
////        cmdLine.addArgument("${file}");
////        cmdLine.setSubstitutionMap(map);
//
//        DefaultExecutor executor = new DefaultExecutor();
//
//        //创建监控时间60秒，超过60秒则中断执行
//        ExecuteWatchdog watchdog = new ExecuteWatchdog(60*1000);
//        executor.setWatchdog(watchdog);
//
//        executor.setExitValue(0);
//        int exitValue = executor.execute(cmdLine);
//        System.out.println(exitValue);
//
//
//    }
//}
