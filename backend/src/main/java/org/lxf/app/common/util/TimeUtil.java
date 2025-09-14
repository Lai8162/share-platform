package org.lxf.app.common.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Month;

import java.util.Date;

/**
 * 时间工具类
 *
 * @author lxf
 * @version 1.0
 * @since 2025/4/6 16:31
 */
public class TimeUtil {
    public static void main(String[] args) {
        String str1 = DateUtil.format(new Date(), "YYYY-MM-dd HH:mm:ss");
        System.out.println(str1);
        String str2 = DateUtil.format(new Date(), "YYYY-MM-dd");
        System.out.println(str2);
        Date date = DateUtil.date();
        System.out.println(date);
        System.out.println(date.getClass());
        System.out.println(DateUtil.format(date, "YYYY-MM-dd"));
        Date date1 = DateUtil.dateSecond();
        System.out.println(date1);
        int month = DateUtil.month(DateUtil.date());
        Month monthEnum = DateUtil.monthEnum(DateUtil.date());
        System.out.println(month);
        System.out.println(monthEnum);

        System.out.println("-id".split("-").length);
        System.out.println("name__".split("__").length);
    }
}
