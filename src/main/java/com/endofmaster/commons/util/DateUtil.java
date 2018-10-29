package com.endofmaster.commons.util;

import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZM.Wang
 */
public class DateUtil {

    /**
     * 从一个时间区间获取中间所有的日期
     *
     * @param start 开始
     * @param end   结束
     * @return date的list
     */
    public static List<Date> getDatesOfDateRange(Date start, Date end) {
        LocalDate localDate1 = date2LocalDate(start);
        LocalDate localDate2 = date2LocalDate(end);
        return getLocalDateOfLocalDateRange(localDate1, localDate2).stream().map(DateUtil::localDate2Date).collect(Collectors.toList());
    }

    /**
     * 从一个时间区间获取中间所有的日期
     *
     * @param start 开始
     * @param end   结束
     * @return dateLocal的list
     */
    public static List<LocalDate> getLocalDatesOfDateRange(Date start, Date end) {
        LocalDate localDate1 = date2LocalDate(start);
        LocalDate localDate2 = date2LocalDate(end);
        return getLocalDateOfLocalDateRange(localDate1, localDate2);
    }

    /**
     * 从一个时间区间获取中间所有的日期
     *
     * @param start 开始
     * @param end   结束
     * @return localDate的list
     */
    public static List<LocalDate> getLocalDateOfLocalDateRange(LocalDate start, LocalDate end) {
        if (start.equals(end)) {
            return Collections.singletonList(start);
        }
        long startEpochDay = start.toEpochDay();
        long endEpochDay = end.toEpochDay();
        List<LocalDate> dates = new ArrayList<>();
        if (startEpochDay > endEpochDay) {
            for (int i = 0; i <= startEpochDay - endEpochDay; i++) {
                dates.add(end.plusDays(i));
            }
        } else {
            for (int i = 0; i <= endEpochDay - startEpochDay; i++) {
                dates.add(start.plusDays(i));
            }
        }
        return dates;
    }

    /** localDate转时间戳 */
    public static long localDate2Long(LocalDate localDate) {
        LocalDateTime localDateTime = localDate.atTime(LocalTime.MIN);
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static Date getLastDayStart() {
        return getLastDay(LocalTime.MIN);
    }

    public static Date getLastDayEnd() {
        return getLastDay(LocalTime.MAX);
    }

    public static Date getAnyDayStart(LocalDate localDate) {
        LocalDateTime anyDayStart = LocalDateTime.of(localDate, LocalTime.MIN);
        return localDateTime2Date(anyDayStart);
    }

    public static Date getAnyDayEnd(LocalDate localDate) {
        LocalDateTime anyDayEnd = LocalDateTime.of(localDate, LocalTime.MAX);
        return localDateTime2Date(anyDayEnd);
    }

    public static Date getLastDay(LocalTime localTime) {
        return getPlusDay(localTime, -1);
    }

    public static Date getTodayAngDate(LocalTime localTime) {
        return localDateTime2Date(LocalDate.now(), localTime);
    }

    public static Date getPlusDayStart(int ago) {
        LocalDate agoDay = LocalDate.now().plusDays(ago);
        LocalDateTime agoDayStart = LocalDateTime.of(agoDay, LocalTime.MIN);
        return localDateTime2Date(agoDayStart);
    }

    public static Date getPlusDayEnd(int ago) {
        LocalDate agoDay = LocalDate.now().plusDays(ago);
        LocalDateTime agoDayEnd = LocalDateTime.of(agoDay, LocalTime.MAX);
        return localDateTime2Date(agoDayEnd);
    }

    public static Date getPlusDay(LocalTime localTime, int plusDay) {
        LocalDate lastDay = LocalDate.now().plusDays(plusDay);
        LocalDateTime lastDayStart = LocalDateTime.of(lastDay, localTime);
        return localDateTime2Date(lastDayStart);
    }

    public static Date localDate2Date(LocalDate localDate) {
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDate.atStartOfDay(zoneId);
        return Date.from(zdt.toInstant());
    }

    public static Date localDateTime2Date(LocalDate localDate, LocalTime localTime) {
        return localDateTime2Date(LocalDateTime.of(localDate, localTime));
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 比较日期先后
     *
     * @param date1 被比较的date
     * @param date2
     * @return date2大于date1返回true
     */
    public static boolean compareByDate(Date date1, Date date2) {
        LocalDate localDate1 = date2LocalDate(date1);
        LocalDate localDate2 = date2LocalDate(date2);
        return compareByDate(localDate1, localDate2);
    }

    /**
     * 比较两个时间区间总共含有几天
     */
    public static int compareDayByDate(Date date1, Date date2) {
        LocalDate localDate1 = date2LocalDate(date1);
        LocalDate localDate2 = date2LocalDate(date2);
        return localDate2.compareTo(localDate1) + 1;
    }

    public static boolean compareByDate(LocalDate date1, LocalDate date2) {
        int i = date2.compareTo(date1);
        return i > 0;
    }

    public static LocalTime date2LocalTime(Date date) {
        return date2LocalDateTime(date).toLocalTime();
    }

    public static LocalDate date2LocalDate(Date date) {
        return date2LocalDateTime(date).toLocalDate();
    }

    public static LocalDateTime date2LocalDateTime(Date date) {
        Instant instant = date.toInstant();
        ZoneId zone = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zone);
    }

}
