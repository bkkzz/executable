package com.wxl.cli.date;

import java.time.ZoneId;

/**
 * Create by wuxingle on 2020/08/10
 * 常量
 */
public interface JDateConstant {

    /**
     * 默认格式
     */
    String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    /**
     * 时区
     */
    ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    /**
     * 星期格式
     */
    String[] WEEK_NAME = {
            "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"
    };


}
