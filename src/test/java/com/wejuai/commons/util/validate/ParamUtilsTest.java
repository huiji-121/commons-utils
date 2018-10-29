package com.jifenke.commons.util.validate;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ParamUtilsTest {

    @Test
    public void test() {
        Map<String, Object> map = new HashMap<>();
        map.put("s", 1);
        map.put("a", "s");
        System.err.println(ParamUtils.findParam(map, "s", Integer.class));
        System.err.println(ParamUtils.findParam(map, "a", String.class));
    }

}