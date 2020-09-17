package com.wxl.cli.encode;

import com.wxl.cli.CommandExecutor;
import com.wxl.cli.CommandExecutorBuilder;
import com.wxl.cli.HelpCommand;

/**
 * Create by wuxingle on 2020/09/17
 * jenc
 * 编解码命令
 */
public class JEncode {

    public static void main(String[] args) {
        HelpCommand helpCommand = new HelpCommand();
        helpCommand.setEnd(true);

        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jenc")
                .addCommand(
                        new URLEncodeCommand(),
                        new URLDecodeCommand(),
                        helpCommand
                )
                .build();

        executor.execute(args);
    }
}
