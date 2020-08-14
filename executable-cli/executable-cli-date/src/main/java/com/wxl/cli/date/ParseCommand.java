package com.wxl.cli.date;

import com.wxl.cli.AbstractCommand;
import com.wxl.cli.CommandChain;
import com.wxl.cli.CommandContext;
import com.wxl.cli.exception.CommandExecuteException;
import com.wxl.cli.exception.OptionArgumentException;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Create by wuxingle on 2020/08/07
 * 时间字符串解析为时间戳命令
 */
public class ParseCommand extends AbstractCommand {

    private static final Option FORMAT_OPT = Option.builder("p")
            .longOpt("parse")
            .desc("时间字符串解析为时间戳")
            .optionalArg(true)
            .numberOfArgs(2)
            .argName("time [fmt]")
            .build();

    private static final String[] FORMATS = new String[]{
            JDateConstant.DEFAULT_FORMAT,
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd"
    };

    @Override
    public void execute(CommandContext context, CommandChain chain) throws CommandExecuteException {
        CommandLine commandLine = context.commandLine();
        if (commandLine.hasOption("p")) {
            checkOptionValueLen(context, 1, 2);

            // 第一个参数，时间
            String time = getRequireOptionValue(context, 0);
            // 格式化参数
            String fmt = getOptionValue(context, 1);
            Date date;
            if (fmt != null) {
                try {
                    date = new SimpleDateFormat(fmt).parse(time);
                } catch (Exception e) {
                    throw new OptionArgumentException("解析失败:" + time + ",fmt=" + fmt);
                }
            } else {
                date = parse(time);
                if (date == null) {
                    throw new OptionArgumentException("解析失败:" + time);
                }
            }
            context.stdout().println(date.getTime());
        }

        chain.doNext(context);
    }

    private Date parse(String time) {
        for (String format : FORMATS) {
            try {
                return new SimpleDateFormat(format).parse(time);
            } catch (ParseException e) {
                //ignore
            }
        }
        return null;
    }

    @Override
    public Option option() {
        return FORMAT_OPT;
    }
}
