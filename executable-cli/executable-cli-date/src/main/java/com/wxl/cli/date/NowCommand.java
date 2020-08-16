package com.wxl.cli.date;

import com.wxl.cli.AbstractCommand;
import com.wxl.cli.CommandChain;
import com.wxl.cli.CommandContext;
import com.wxl.cli.exception.CommandExecuteException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Create by wuxingle on 2020/08/07
 * 输出当前时间
 */
public class NowCommand extends AbstractCommand {

    private static final Option NOW_OPT = Option.builder("n")
            .longOpt("now")
            .desc("输出当前时间")
            .optionalArg(true)
            .numberOfArgs(1)
            .argName("[fmt]")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) throws CommandExecuteException {
        CommandLine commandLine = context.commandLine();
        if (commandLine.hasOption("n") || commandLine.getOptions().length == 0) {
            checkOptionValueLen(context, 0, 1);
            String fmt = getOptionValue(context, 0, JDateConstant.DEFAULT_FORMAT);

            try {
                LocalDateTime now = LocalDateTime.now();

                context.stdout().println(now.format(DateTimeFormatter.ofPattern(fmt)));
                context.stdout().println(now.atZone(JDateConstant.DEFAULT_ZONE).toInstant().toEpochMilli());
            } catch (IllegalArgumentException | DateTimeException e) {
                throw new CommandExecuteException("fmt error by:" + fmt);
            }
        }

        chain.doNext(context);
    }

    @Override
    public Option option() {
        return NOW_OPT;
    }
}
