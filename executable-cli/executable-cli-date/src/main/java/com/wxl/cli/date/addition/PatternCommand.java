package com.wxl.cli.date.addition;

import com.wxl.cli.AbstractCommand;
import com.wxl.cli.CommandChain;
import com.wxl.cli.CommandContext;
import com.wxl.cli.date.JDateConstant;
import org.apache.commons.cli.Option;

import java.time.format.DateTimeFormatter;

/**
 * Create by wuxingle on 2020/08/28
 * 指定日期格式
 */
public class PatternCommand extends AbstractCommand {

    private static final Option PATTERN_OPT = Option.builder()
            .longOpt("pattern")
            .desc("指定日期格式")
            .optionalArg(true)
            .numberOfArgs(1)
            .argName("[pattern]")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) {
        if (isCurrentCommand(context)) {
            String pattern = getRequireOptionValue(context, 0);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
            context.setAttr(JDateConstant.ATTR_FORMAT, dateTimeFormatter);
        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return PATTERN_OPT;
    }

}
