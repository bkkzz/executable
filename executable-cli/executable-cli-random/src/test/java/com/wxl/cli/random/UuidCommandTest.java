package com.wxl.cli.random;

import com.wxl.cli.CommandExecutor;
import com.wxl.cli.CommandExecutorBuilder;
import com.wxl.cli.random.addition.CountCommand;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/08/27
 */
public class UuidCommandTest {


    @Test
    public void test1() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("jrdm")
                .addCommand(
                        new CountCommand(),
                        new UuidCommand()
                )
                .build();

        executor.execute(new String[]{"-u", "-c", "10"});
    }
}
