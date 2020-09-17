package com.wxl.cli.json;

import com.wxl.cli.CommandExecutor;
import com.wxl.cli.CommandExecutorBuilder;
import com.wxl.cli.HelpCommand;
import com.wxl.cli.json.addition.LenientCommand;
import com.wxl.cli.json.addition.NullableCommand;

/**
 * Create by wuxingle on 2020/09/16
 * json
 * json相关命令
 */
public class JJson {

    public static void main(String[] args) {
        HelpCommand helpCommand = new HelpCommand();

        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("json")
                .addCommand(
                        helpCommand,
                        new NullableCommand(),
                        new LenientCommand(),

                        new CompressCommand(),
                        new ExpandCommand()
                )
                .build();

        executor.execute(args);
    }
}
