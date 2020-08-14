package com.wxl.cli.exception;

/**
 * Create by wuxingle on 2020/08/06
 * 命令执行异常
 */
public class CommandExecuteException extends RuntimeException {

    private static final long serialVersionUID = 8959404835518911125L;

    public CommandExecuteException(String message) {
        super(message);
    }

    public CommandExecuteException(String message, Throwable cause) {
        super(message, cause);
    }
}
