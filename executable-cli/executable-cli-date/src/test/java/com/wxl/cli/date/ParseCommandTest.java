package com.wxl.cli.date;

import com.wxl.cli.CommandExecutor;
import com.wxl.cli.CommandExecutorBuilder;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/08/16
 */
public class ParseCommandTest {

    @Test
    public void test() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jdate")
                .addCommand(new ParseCommand())
                .build();

        executor.execute(new String[]{"-p", "2020-08-13 12:12:19"});
    }
}