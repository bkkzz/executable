package com.wxl.cli.json.addition;

import com.wxl.cli.AbstractCommand;
import com.wxl.cli.CommandChain;
import com.wxl.cli.CommandContext;
import com.wxl.cli.json.JsonConstant;
import org.apache.commons.cli.Option;

/**
 * Create by wuxingle on 2020/09/16
 * json格式化宽松校验
 */
public class LenientCommand extends AbstractCommand {

    private static final Option LENIENT_OPT = Option.builder()
            .longOpt("lenient")
            .desc("宽松校验")
            .optionalArg(true)
            .hasArg(false)
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            context.setAttr(JsonConstant.ATTR_LENIENT, true);
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return LENIENT_OPT;
    }
}
