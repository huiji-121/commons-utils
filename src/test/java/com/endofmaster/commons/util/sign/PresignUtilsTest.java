package com.endofmaster.commons.util.sign;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YQ.Huang
 */
public class PresignUtilsTest {
    @Test
    public void createLinkString() {
        Map<String, String> params = new HashMap<>();
        params.put("k2", "v2");
        params.put("k1", "v1");
        params.put("k4", "");
        params.put("k5", null);

        String content = PresignUtils.createLinkString(params, true);

        Assert.assertEquals("k1=v1&k2=v2", content);
    }

    @Test
    public void createLinkStringOnlyValue() {
        Map<String, String> params = new HashMap<>();
        params.put("k2", "v2");
        params.put("k1", "v1");
        params.put("k4", "");
        params.put("k5", null);

        String content = PresignUtils.createLinkStringOnlyValueByKey(params);
        System.err.println(content);
        Assert.assertEquals("k1=v1&k2=v2", content);
    }
}