package com.endofmaster.commons.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ZM.Wang
 */
public class HttpRequestUtils {
    public static Map<String, String> getRequestParams(HttpServletRequest request, String charset) throws UnsupportedEncodingException {
        Map<String, String> params = new HashMap<>();
        for (Map.Entry<String, String[]> entry : request.getParameterMap().entrySet()) {
            String value = StringUtils.join(entry.getValue(), ',');
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            value = new String(value.getBytes("ISO-8859-1"), "gbk");
            params.put(entry.getKey(), URLDecoder.decode(value, charset));
        }
        return params;
    }


    public static Map<String, String> getRequestParamsNoDecode(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        if (null != request) {
            Set<String> paramsKey = request.getParameterMap().keySet();
            for (String key : paramsKey) {
                params.put(key, request.getParameter(key));
            }
        }
        return params;
    }
}
