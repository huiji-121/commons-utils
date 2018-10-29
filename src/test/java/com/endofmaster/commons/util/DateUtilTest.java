package com.endofmaster.commons.util;

import org.junit.Test;

import java.time.LocalTime;
import java.util.Date;

public class DateUtilTest {

    @Test
    public void getTodayAngDate() {
        Date date = DateUtil.getTodayAngDate(LocalTime.of(9, 0, 0));
        System.err.println(date);
    }
}