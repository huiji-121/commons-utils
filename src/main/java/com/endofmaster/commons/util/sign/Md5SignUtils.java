package com.endofmaster.commons.util.sign;

import org.apache.commons.codec.digest.DigestUtils;

import java.security.SignatureException;

/**
 * MD5算法的签名/验签工具
 *
 * @author YQ.Huang
 */
public class Md5SignUtils {

    /**
     * MD5签名
     *
     * @param content 待签名的内容
     * @param key     密钥
     * @param charset 编码格式
     * @return 签名（十六进制字符串）
     */
    public static String sign(String content, String key, String charset) throws SignatureException {
        try {
            byte[] bytes = (content + key).getBytes(charset);
            return DigestUtils.md5Hex(bytes);
        } catch (Exception e) {
            throw new SignatureException(e);
        }
    }

    /**
     * MD5验签
     *
     * @param content 原始字符串
     * @param sign    待验证的签名（十六进制字符串）
     * @param key     密钥
     * @param charset 编码格式
     * @return true or false
     */
    public static boolean verify(String content, String sign, String key, String charset) throws SignatureException {
        return sign(content, key, charset).equals(sign);
    }

}
