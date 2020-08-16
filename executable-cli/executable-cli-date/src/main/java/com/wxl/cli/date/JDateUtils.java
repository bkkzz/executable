package com.wxl.cli.date;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Create by wuxingle on 2020/08/14
 * 日期相关工具类
 */
public class JDateUtils {

    private static DateTimeFormatter[] dateTimeFormatters = {
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS"),
            DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒"),
            DateTimeFormatter.ofPattern("yyyy年MM月dd日"),
            DateTimeFormatter.ofPattern("yyyyMMddHHmmss"),
            DateTimeFormatter.ofPattern("yyyyMMdd"),
            DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"),
            DateTimeFormatter.ISO_INSTANT,
            DateTimeFormatter.ISO_LOCAL_DATE_TIME,
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ISO_DATE_TIME,
            DateTimeFormatter.ISO_DATE,
            DateTimeFormatter.RFC_1123_DATE_TIME,
            DateTimeFormatter.ISO_ORDINAL_DATE,
            DateTimeFormatter.ISO_ZONED_DATE_TIME,
            DateTimeFormatter.ISO_WEEK_DATE,
    };

    /**
     * 尝试不同格式解析日期
     */
    public static LocalDateTime parse(String date) {
        try {
            long ts = Long.parseLong(date);
            Instant instant = Instant.ofEpochMilli(ts);
            return LocalDateTime.ofInstant(instant, JDateConstant.DEFAULT_ZONE);
        } catch (NumberFormatException e) {
            //ignore
        }
        for (DateTimeFormatter dateTimeFormatter : dateTimeFormatters) {
            try {
                return LocalDateTime.parse(date, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                //ignore
            }
        }
        return null;
    }


}





