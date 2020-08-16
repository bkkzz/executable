package com.wxl.cli.date;

import com.wxl.cli.AbstractCommand;
import com.wxl.cli.CommandChain;
import com.wxl.cli.CommandContext;
import com.wxl.cli.exception.CommandExecuteException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.time.DateTimeException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Create by wuxingle on 2020/08/07
 * 时间戳格式化命令
 */
public class FormatCommand extends AbstractCommand {

    private static final Option FORMAT_OPT = Option.builder("f")
            .longOpt("format")
            .desc("时间戳格式化")
            .optionalArg(true)
            .numberOfArgs(2)
            .argName("timestamp [fmt]")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) throws CommandExecuteException {
        CommandLine commandLine = context.commandLine();
        if (commandLine.hasOption("f")) {
            checkOptionValueLen(context, 1, 2);

            // 第一个参数，时间戳
            long timestamp = getRequireOptionLong(context, 0);
            // 格式化参数
            String fmt = getOptionValue(context, 1, JDateConstant.DEFAULT_FORMAT);

            try {
                Instant instant = Instant.ofEpochMilli(timestamp);
                LocalDateTime dateTime = LocalDateTime.ofInstant(instant, JDateConstant.DEFAULT_ZONE);

                String result = DateTimeFormatter.ofPattern(fmt).format(dateTime);

                context.stdout().println(result);
            } catch (IllegalArgumentException | DateTimeException e) {
                throw new CommandExecuteException("fmt error by:" + fmt);
            }
        }

        chain.doNext(context);
    }

    @Override
    public Option option() {
        return FORMAT_OPT;
    }
}
