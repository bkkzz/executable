package com.wxl.cli;

import com.wxl.cli.exception.CommandExecuteException;
import com.wxl.cli.exception.OptionArgumentException;
import org.apache.commons.cli.Option;
import org.apache.commons.lang3.StringUtils;

/**
 * Create by wuxingle on 2020/08/06
 * 命令
 */
public interface Command {

    /**
     * 该选项执行的命令
     */
    void execute(CommandContext context, CommandChain chain) throws CommandExecuteException;

    /**
     * 选项
     */
    Option option();

}
