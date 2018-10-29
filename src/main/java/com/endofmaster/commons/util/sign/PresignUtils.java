package com.endofmaster.commons.util.sign;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

/**
 * 预签名相关工具
 *
 * @author YQ.Huang
 */
public class PresignUtils {

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params      需要排序并参与字符拼接的参数组
     * @param ignoreEmpty 是否过滤空值参数
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params, boolean ignoreEmpty) {
        Map<String, String> temp = params;
        if (ignoreEmpty) {
            //过滤掉空值参数
            temp = new HashMap<>();
            for (String key : params.keySet()) {
                String value = params.get(key);
                if (value != null && !value.isEmpty())
                    temp.put(key, value);
            }
        }

        List<String> keys = new ArrayList<>(temp.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = temp.get(key);
            sb.append(key).append("=").append(value);
            //拼接时，不包括最后一个&字符
            if (i < keys.size() - 1) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    public static String createLinkStringOnlyValueByKey(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            String value = params.get(key);
            if (value != null) {
                sb.append(value);
            }
        }
        return sb.toString();
    }

    public static String createLinkStringOnlyValueByValue(Map<String, String> params) {
        List<String> keys = new ArrayList<>(params.keySet());
        List<String> values = new ArrayList<>();
        for (String key : keys) {
            String value = params.get(key);
            if (value != null) {
                values.add(value);
            }
        }
        Collections.sort(values);
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            sb.append(value);
        }
        return sb.toString();
    }

    public static Map<String, String> ignoreEmpty(Map<String, String> params) {
        Map<String, String> temp = new HashMap<>();
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (value != null && !value.isEmpty())
                temp.put(key, value);
        }
        return temp;
    }

    public static void urldecode(Map<String, String> params) throws UnsupportedEncodingException {
        for (String key : params.keySet()) {
            String value = params.get(key);
            params.put(key, URLDecoder.decode(value, "UTF-8"));
        }
    }

    public static void urlencode(Map<String, String> params) throws UnsupportedEncodingException {
        for (String key : params.keySet()) {
            String value = params.get(key);
            params.put(key, URLEncoder.encode(value, "UTF-8"));
        }
    }

}
