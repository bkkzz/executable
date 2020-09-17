package com.wxl.cli.json.addition;

import com.wxl.cli.AbstractCommand;
import com.wxl.cli.CommandChain;
import com.wxl.cli.CommandContext;
import com.wxl.cli.json.JsonConstant;
import org.apache.commons.cli.Option;

/**
 * Create by wuxingle on 2020/09/16
 * 允许null值
 */
public class NullableCommand extends AbstractCommand {

    private static final Option NULLABLE_OPT = Option.builder()
            .longOpt("nullable")
            .desc("允许输出null值")
            .optionalArg(true)
            .hasArg(false)
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            context.setAttr(JsonConstant.ATTR_NULLABLE, true);
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return NULLABLE_OPT;
    }
}
