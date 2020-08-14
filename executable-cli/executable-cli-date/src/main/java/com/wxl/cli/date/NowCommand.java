package com.wxl.cli.date;

import com.wxl.cli.AbstractCommand;
import com.wxl.cli.CommandChain;
import com.wxl.cli.CommandContext;
import com.wxl.cli.exception.CommandExecuteException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by wuxingle on 2020/08/07
 * 输出当前时间
 */
public class NowCommand extends AbstractCommand {

    private static final Option NOW_DATETIME = Option.builder("n")
            .longOpt("now")
            .desc("输出当前时间")
            .optionalArg(true)
            .numberOfArgs(1)
            .argName("[fmt]")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) throws CommandExecuteException {
        CommandLine commandLine = context.commandLine();
        if (commandLine.hasOption("n")) {
            checkOptionValueLen(context, 0, 1);
            String fmt = getOptionValue(context, 0, JDateConstant.DEFAULT_FORMAT);

            try {
                Date date = new Date();
                String format = new SimpleDateFormat(fmt).format(date);
                context.stdout().println(format);
                context.stdout().println(date.getTime());
            } catch (Exception e) {
                throw new CommandExecuteException("fmt格式非法:" + fmt);
            }
        }

        chain.doNext(context);
    }

    @Override
    public Option option() {
        return NOW_DATETIME;
    }
}
