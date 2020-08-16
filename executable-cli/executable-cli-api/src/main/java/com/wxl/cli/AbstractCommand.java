package com.wxl.cli;

import com.wxl.cli.exception.OptionArgumentException;
import org.apache.commons.lang3.StringUtils;

/**
 * Create by wuxingle on 2020/08/12
 * 命令
 * 提供公共方法
 */
public abstract class AbstractCommand implements Command {

    /**
     * 检查选项参数长度
     *
     * @param context
     * @param min     最小个数
     * @param max     最大个数
     */
    public void checkOptionValueLen(CommandContext context, int min, int max) {
        String opt = option().getOpt();
        String[] values = context.commandLine().getOptionValues(opt);
        int len = values == null ? 0 : values.length;
        if (len < min || len > max) {
            throw new OptionArgumentException(option().getArgName(),
                    values == null ? "" : StringUtils.join(values, " "));
        }
    }

    /**
     * 获取选项值
     *
     * @param index 参数索引
     */
    public String getOptionValue(CommandContext context, int index) {
        return getOptionValue(context, index, false);
    }

    public String getOptionValue(CommandContext context, int index, String defaultVal) {
        String val = getOptionValue(context, index, false);
        return val == null ? defaultVal : val;
    }

    public Long getOptionLong(CommandContext context, int index) {
        return getOptionLong(context, index, false);
    }

    public Long getOptionLong(CommandContext context, int index, Long defaultVal) {
        Long val = getOptionLong(context, index, false);
        return val == null ? defaultVal : val;
    }

    public Integer getOptionInteger(CommandContext context, int index) {
        Long val = getOptionLong(context, index, false);
        return val == null ? null : val.intValue();
    }

    public Integer getOptionInteger(CommandContext context, int index, Integer defaultVal) {
        Long val = getOptionLong(context, index, false);
        return val == null ? defaultVal : val.intValue();
    }

    /**
     * 获取必选选项值
     *
     * @param index 参数索引
     */
    public String getRequireOptionValue(CommandContext context, int index) {
        return getOptionValue(context, index, true);
    }

    public Long getRequireOptionLong(CommandContext context, int index) {
        return getOptionLong(context, index, true);
    }

    public Integer getRequireOptionInteger(CommandContext context, int index) {
        return getOptionLong(context, index, true).intValue();
    }

    /**
     * 获取选项值
     *
     * @param index   参数索引
     * @param require 是否必选
     */
    private String getOptionValue(CommandContext context, int index, boolean require) {
        String opt = option().getOpt();
        String[] values = context.commandLine().getOptionValues(opt);
        if (values == null || index >= values.length) {
            if (require) {
                throw new OptionArgumentException(option().getArgName(),
                        values == null ? "" : StringUtils.join(values, " "));
            }
            return null;
        }
        return values[index];
    }

    private Long getOptionLong(CommandContext context, int index, boolean require) {
        String val = getOptionValue(context, index, require);
        if (val == null) {
            return null;
        }
        try {
            return Long.parseLong(val);
        } catch (NumberFormatException e) {
            throw new OptionArgumentException("[-" + option().getOpt()
                    + "] value type error! " + e.getMessage());
        }
    }
}
