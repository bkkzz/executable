package com.wxl.cli.date;

import com.wxl.cli.AbstractCommand;
import com.wxl.cli.CommandChain;
import com.wxl.cli.CommandContext;
import com.wxl.cli.exception.CommandExecuteException;
import com.wxl.cli.exception.OptionArgumentException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;

/**
 * Create by wuxingle on 2020/08/18
 * 时间增加命令
 */
public class AddCommand extends AbstractCommand {

    private static final Option PLUS_OPT = Option.builder("a")
            .longOpt("add")
            .desc("时间增加某个增量")
            .optionalArg(true)
            .numberOfArgs(2)
            .argName("[time] unit:1d,1m...")
            .build();

    @Override
    public void execute(CommandContext context, CommandChain chain) throws CommandExecuteException {
        CommandLine commandLine = context.commandLine();
        if (commandLine.hasOption("a")) {
            checkOptionValueLen(context, 1, 2);

            boolean isTimestamp = false;
            DateTimeFormatter dateTimeFormatter = null;
            LocalDateTime dateTime;
            String unitStr;
            // 1个参数为单位，时间基数为当前时间
            if (commandLine.getOptionValues("a").length == 1) {
                dateTime = LocalDateTime.now();
                dateTimeFormatter = DateTimeFormatter.ofPattern(JDateConstant.DEFAULT_FORMAT);
                unitStr = getOptionValue(context, 0);
            }
            // 第一个参数为时间，第二个参数为单位
            else {
                String dateStr = getOptionValue(context, 0);
                isTimestamp = JDateUtils.isTimestamp(dateStr);
                if (isTimestamp) {
                    dateTime = JDateUtils.parseDateTime(dateStr);
                } else {
                    dateTimeFormatter = JDateUtils.detectFormatter(dateStr);
                    if (dateTimeFormatter == null) {
                        throw new OptionArgumentException("parse error by:" + dateStr);
                    }
                    dateTime = JDateUtils.parseDateTime(dateTimeFormatter, dateStr);
                }
                unitStr = getOptionValue(context, 1);
            }

            // 时间单位处理
            if (!JDateConstant.JDateUnit.UNIT_EXP_REGEX.matcher(unitStr).matches()) {
                throw new OptionArgumentException("parse error by unit:" + unitStr
                        + ", must is:" + JDateConstant.JDateUnit.UNIT_EXP_REGEX.pattern());
            }
            Matcher matcher = JDateConstant.JDateUnit.UNIT_REGEX.matcher(unitStr);

            while (matcher.find()) {
                int val = Integer.parseInt(matcher.group(1));
                JDateConstant.JDateUnit unit = JDateConstant.JDateUnit.parse(matcher.group(2));
                dateTime = dateTime.plus(val, unit.getUnit());
            }

            if (isTimestamp) {
                context.stdout().println(dateTime.atZone(JDateConstant.DEFAULT_ZONE).toInstant().toEpochMilli());
            } else {
                context.stdout().println(dateTime.format(dateTimeFormatter));
            }

        }
        chain.doNext(context);
    }

    @Override
    public Option option() {
        return PLUS_OPT;
    }
}
