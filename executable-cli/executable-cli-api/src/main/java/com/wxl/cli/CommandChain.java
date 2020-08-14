package com.wxl.cli;

import com.wxl.cli.exception.CommandExecuteException;

/**
 * Create by wuxingle on 2020/08/07
 * 命令执行链
 */
public interface CommandChain {

    /**
     * 执行命令
     */
    void doNext(CommandContext context) throws CommandExecuteException;

}
