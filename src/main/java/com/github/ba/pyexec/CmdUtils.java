package com.github.ba.pyexec;

import org.apache.commons.exec.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author wang xiao
 * date 2022/11/29
 */
public class CmdUtils {

    private static final Logger log = LoggerFactory.getLogger(CmdUtils.class);

    /**
     * 执行系统命令, 返回执行结果
     *
     * @param cmd      需要执行的命令
     * @param distPath 执行命令的子进程的工作目录, null 表示和当前主进程工作目录相同
     */
    public static String execCmd(String cmd, String distPath) throws IOException {
        int exitValue = 1;
        try {


            log.info("开始执行命令和目录:[{}],[{}]" , cmd,distPath);
            CommandLine cmdLine = CommandLine.parse(cmd);
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValues(null);
            ExecuteWatchdog watchdog = new ExecuteWatchdog(600000);
            executor.setWatchdog(watchdog);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
            executor.setStreamHandler(streamHandler);
            exitValue = executor.execute(cmdLine);
            //获取程序外部程序执行结果
            String out = outputStream.toString(StandardCharsets.UTF_8);
            String error = errorStream.toString(StandardCharsets.UTF_8);
            log.info("执行命令处理结果exitValue:[{}],out:[{}],error:[{}]",exitValue,out,error);
        } catch (Exception e) {
            log.error("执行批处理命令异常", e);
            throw e;
        }
        log.info("结束执行命令和目录:[{}],[{}]" , cmd,distPath);
        if(exitValue != 0){
            log.error("执行批处理命令失败");
        }
        return String.valueOf(exitValue);
    }


    /**
     * 执行系统命令, 返回执行结果
     *
     * @param cmd      需要执行的命令
     * @param distPath 执行命令的子进程的工作目录, null 表示和当前主进程工作目录相同
     */
    public static boolean execAsyncCmd(String cmd, String distPath) {

        boolean result = true;

        int exitValue = 1;
        try {
            // 执行命令, 返回一个子进程对象（命令在子进程中执行）
            log.info("开始执行命令和目录:[{}],[{}]", cmd, distPath);
            CommandLine cmdLine = CommandLine.parse(cmd);
            DefaultExecutor executor = new DefaultExecutor();
            executor.setExitValues(null);
            ExecuteWatchdog watchdog = new ExecuteWatchdog(600000);
            executor.setWatchdog(watchdog);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ByteArrayOutputStream errorStream = new ByteArrayOutputStream();
            PumpStreamHandler streamHandler = new PumpStreamHandler(outputStream, errorStream);
            executor.setStreamHandler(streamHandler);
            //非阻塞
            DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
            try {
                executor.execute(cmdLine, resultHandler);
                resultHandler.waitFor();
            } catch (Exception e) {
                result = false;
                e.printStackTrace();
            }
        } catch (Exception e) {
            log.error("执行批处理命令异常", e);
            throw e;
        }
        log.info("结束执行命令和目录:[{}],[{}]", cmd, distPath);
        if (exitValue != 0) {
            log.error("执行批处理命令失败");
        }
        return result;
    }

}
