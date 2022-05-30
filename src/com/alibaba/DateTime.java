package com.alibaba;

import java.text.SimpleDateFormat;
import java.time.Instant;

public class DateTime {
    public static void main(String[] args) {
        //日期格式化时，传入pattern中表示年份统一使用小写的y
        //M:月份 m：分钟 H：24小时制 h：12小时制
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取当前毫秒数
        System.currentTimeMillis();
        //再Jdk8中，针对统计时间等场景，推荐使用Instant类
        //不允许在程序任何地方中使用 java.sql.Date(不记录时间，getHours（）抛出异常) Time（不记录日期，getYear()抛出异常）
        // Timestamp（java.util.Date.after(Date)进行时间比较时，入场为Timestamp触发JDK BUG）
    }
}
