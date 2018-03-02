package com.yy.crm.utils.shell;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author luyuanyuan on 2018/3/2.
 */
@Slf4j
public class ShellUtil {

    public static synchronized ShellResult exceCommand(String command) {

        InputStreamReader stdISR = null;
        InputStreamReader errISR = null;
        Process process = null;
        long timeout = 10 * 10000;
        try {
            process = Runtime.getRuntime().exec(command);

            CommandStreamGobbler errorGobbler = new CommandStreamGobbler(process.getErrorStream(), command, "ERR");
            CommandStreamGobbler outputGobbler = new CommandStreamGobbler(process.getInputStream(), command, "STD");

            errorGobbler.start();
            // 必须先等待错误输出ready再建立标准输出
            while (!errorGobbler.isReady()) {
                Thread.sleep(10);
            }
            outputGobbler.start();
            while (!outputGobbler.isReady()) {
                Thread.sleep(10);
            }

            CommandWaitForThread commandThread = new CommandWaitForThread(process);
            commandThread.start();

            long commandTime = System.currentTimeMillis();
            long nowTime = System.currentTimeMillis();
            boolean timeoutFlag = false;
            while (!commandIsFinish(commandThread, errorGobbler, outputGobbler)) {
                if (nowTime - commandTime > timeout) {
                    timeoutFlag = true;
                    break;
                } else {
                    Thread.sleep(100);
                    nowTime = System.currentTimeMillis();
                }
            }
            if (timeoutFlag) {
                // 命令超时
                errorGobbler.setTimeout(1);
                outputGobbler.setTimeout(1);
                log.info("正式执行命令：" + command + "超时");
            } else {
                // 命令执行完成
                errorGobbler.setTimeout(2);
                outputGobbler.setTimeout(2);
            }

            while (true) {
                if (errorGobbler.isReadFinish() && outputGobbler.isReadFinish()) {
                    ShellResult shellResult = new ShellResult();
                    shellResult.setCode(commandThread.getExitValue());
                    shellResult.setErrorInfoList(errorGobbler.getInfoList());
                    shellResult.setStdInfoList(outputGobbler.getInfoList());
                    return shellResult;
                }
                Thread.sleep(10);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            //应用内部发生异常
            return null;
        } finally {
            if (process != null) {
                process.destroy();
            }
        }
    }


    private static boolean commandIsFinish(CommandWaitForThread commandThread, CommandStreamGobbler errorGobbler, CommandStreamGobbler outputGobbler) {
        if (commandThread != null) {
            return commandThread.isFinish();
        } else {
            return (errorGobbler.isReadFinish() && outputGobbler.isReadFinish());
        }
    }
}
