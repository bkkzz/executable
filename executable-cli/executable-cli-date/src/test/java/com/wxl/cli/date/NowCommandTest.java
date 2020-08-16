package com.wxl.cli.date;

import com.wxl.cli.CommandExecutor;
import com.wxl.cli.CommandExecutorBuilder;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/08/14
 */
public class NowCommandTest {


    @Test
    public void test() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jdate")
                .addCommand(new NowCommand())
                .build();

        executor.execute(new String[]{});
    }
}