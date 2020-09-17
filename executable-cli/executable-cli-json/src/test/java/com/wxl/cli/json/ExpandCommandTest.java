package com.wxl.cli.json;

import com.wxl.cli.CommandExecutor;
import com.wxl.cli.CommandExecutorBuilder;
import com.wxl.cli.json.addition.LenientCommand;
import com.wxl.cli.json.addition.NullableCommand;
import org.junit.Test;

/**
 * Create by wuxingle on 2020/09/16
 */
public class ExpandCommandTest {

    @Test
    public void test1() {
        CommandExecutor executor = new CommandExecutorBuilder()
                .setName("json")
                .addCommand(
                        new LenientCommand(),
                        new NullableCommand(),
                        new ExpandCommand()
                )
                .build();

        executor.execute(new String[]{"-e", "[12,{\"name\":\"age\",\"a\":{\"b\":\"c\",\"d\":null}}]1",
                "--ignore", "--nullable"});
    }
}
