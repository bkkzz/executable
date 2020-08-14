package com.wxl.cli.exception;

/**
 * Create by wuxingle on 2020/08/10
 * 选项参数异常
 */
public class OptionArgumentException extends CommandExecuteException {

    private static final long serialVersionUID = 4217230003873995251L;

    private static final String TEMPLATE = "option argument illegal! except: %s, real: %s";

    public OptionArgumentException(String except, String real) {
        super(String.format(TEMPLATE, except, real));
    }

    public OptionArgumentException(String message) {
        super(message);
    }
}
