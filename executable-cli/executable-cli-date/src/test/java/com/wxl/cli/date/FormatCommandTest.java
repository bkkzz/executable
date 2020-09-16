package com.wxl.cli.date;

import com.wxl.cli.CommandExecutor;
import com.wxl.cli.CommandExecutorBuilder;
import com.wxl.cli.date.addition.PatternCommand;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/08/14
 */
public class FormatCommandTest {

    @Test
    public void test() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jdate")
                .addCommand(new PatternCommand(),
                        new FormatCommand())
                .build();

        executor.execute(new String[]{"-f", "1597398956967"});
    }
}


