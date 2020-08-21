package com.wxl.cli.date;

import com.wxl.cli.AbstractCommand;
import com.wxl.cli.CommandChain;
import com.wxl.cli.CommandContext;
import com.wxl.cli.exception.CommandExecuteException;
import com.wxl.cli.exception.OptionArgumentException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.time.Instant;
import java.time.LocalDateTime;

/**
 * Create by wuxingle on 2020/08/07
 * 时间字符串解析为时间戳命令
 */
public class ParseCommand extends AbstractCommand {

    private static final Option PARSE_OPT = Option.builder("p")
            .longOpt("parse")
            .desc("时间字符串解析为时间戳")
            .optionalArg(true)
            .numberOfArgs(2)
            .argName("time [fmt]")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) throws CommandExecuteException {
        CommandLine commandLine = context.commandLine();
        if (commandLine.hasOption("p")) {
            checkOptionValueLen(context, 1, 2);

            // 第一个参数，时间
            String time = getRequireOptionValue(context, 0);
            // 格式化参数
            String fmt = getOptionValue(context, 1);
            LocalDateTime dateTime;
            if (fmt != null) {
                dateTime = JDateUtils.parseDateTime(fmt, time);
                if (dateTime == null) {
                    throw new OptionArgumentException("parse error by:" + time + ", fmt=" + fmt);
                }
            } else {
                dateTime = JDateUtils.parseDateTime(time);
                if (dateTime == null) {
                    throw new OptionArgumentException("parse error by:" + time);
                }
            }

            Instant instant = dateTime.atZone(JDateConstant.DEFAULT_ZONE).toInstant();
            context.stdout().println(instant.toEpochMilli());
        }

        chain.doNext(context);
    }

    @Override
    public Option option() {
        return PARSE_OPT;
    }
}
