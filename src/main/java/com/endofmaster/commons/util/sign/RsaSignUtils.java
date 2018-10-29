package com.endofmaster.commons.util.sign;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA算法的签名/验签工具
 */
public abstract class RsaSignUtils {

    /**
     * 构建私钥
     *
     * @param privateKey PKCS8格式存储的私钥字符串
     * @return PrivateKey
     * @throws SignatureException
     */
    public static PrivateKey buildPrivateKey(String privateKey) throws SignatureException {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new SignatureException(e);
        }
    }

    /**
     * 构建公钥
     *
     * @param publicKey PKCS8格式的公钥
     * @return PublicKey
     * @throws SignatureException
     */
    public static PublicKey buildPublicKey(String publicKey) throws SignatureException {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decodeBase64(publicKey);
            return keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new SignatureException(e);
        }
    }

    /**
     * Sha1WithRSA签名
     *
     * @param content    待签名数据
     * @param privateKey 私钥，PKCS8格式
     * @param charset    编码格式
     * @return Base64后的签名值
     */
    public static String sha1Sign(String content, String privateKey, String charset) throws SignatureException {
        PrivateKey key = buildPrivateKey(privateKey);
        return sha1Sign(content, key, charset);
    }

    /**
     * SHA1WithRSA签名
     *
     * @param content    待签名数据
     * @param privateKey 私钥
     * @param charset    编码格式
     * @return Base64后的签名值
     */
    public static String sha1Sign(String content, PrivateKey privateKey, String charset) throws SignatureException {
        return sign(content, privateKey, charset, RsaSignAlgorithms.SHA1);
    }

    /**
     * SHA256WithRSA签名
     *
     * @param content    待签名数据
     * @param privateKey 私钥字符串
     * @param charset    编码格式
     * @return Base64后的签名值
     */
    public static String sha256Sign(String content, String privateKey, String charset) throws SignatureException {
        PrivateKey key = buildPrivateKey(privateKey);
        return sha256Sign(content, key, charset);
    }

    /**
     * SHA256WithRSA签名
     *
     * @param content    待签名数据
     * @param privateKey 私钥
     * @param charset    编码格式
     * @return Base64后的签名值
     */
    public static String sha256Sign(String content, PrivateKey privateKey, String charset) throws SignatureException {
        return sign(content, privateKey, charset, RsaSignAlgorithms.SHA256);
    }

    /**
     * SHA512WithRSA签名
     *
     * @param content    待签名数据
     * @param privateKey 私钥，PKCS8格式
     * @param charset    编码格式
     * @return Base64后的签名值
     */
    public static String sha512Sign(String content, String privateKey, String charset) throws SignatureException {
        PrivateKey key = buildPrivateKey(privateKey);
        return sha512Sign(content, key, charset);
    }

    /**
     * SHA512WithRSA签名
     *
     * @param content    待签名数据
     * @param privateKey 私钥
     * @param charset    编码格式
     * @return Base64后的签名值
     */
    public static String sha512Sign(String content, PrivateKey privateKey, String charset) throws SignatureException {
        return sign(content, privateKey, charset, RsaSignAlgorithms.SHA512);
    }

    /**
     * RSA签名
     *
     * @param content    待签名数据
     * @param privateKey PKCS8格式私钥
     * @param charset    编码格式
     * @param algorithm  算法
     * @return Base64后的签名值
     */
    public static String sign(String content, String privateKey, String charset, String algorithm) throws SignatureException {
        PrivateKey key = buildPrivateKey(privateKey);
        return sign(content, key, charset, algorithm);
    }

    /**
     * RSA签名
     *
     * @param content    待签名数据
     * @param privateKey 私钥
     * @param charset    编码格式
     * @param algorithm  算法
     * @return Base64后的签名值
     */
    public static String sign(String content, PrivateKey privateKey, String charset, String algorithm) throws SignatureException {
        try {
            return Base64.encodeBase64String(signCore(content, privateKey, charset, algorithm));
        } catch (Exception e) {
            throw new SignatureException(e);
        }
    }

    /**
     * RSA签名
     *
     * @param content    待签名数据
     * @param privateKey 私钥
     * @param charset    编码格式
     * @param algorithm  算法
     * @return 签名值
     */
    public static byte[] signCore(String content, PrivateKey privateKey, String charset, String algorithm) throws SignatureException {
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initSign(privateKey);
            signature.update(content.getBytes(charset));
            return signature.sign();
        } catch (Exception e) {
            throw new SignatureException(e);
        }
    }

    /**
     * SHA1WithRSA验签
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 公钥
     * @param charset   编码格式
     * @return true or false
     */
    public static boolean sha1Verify(String content, String sign, String publicKey, String charset) throws SignatureException {
        PublicKey key = buildPublicKey(publicKey);
        return sha1Verify(content, sign, key, charset);
    }

    /**
     * SHA1WithRSA验签
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 公钥
     * @param charset   编码格式
     * @return true or false
     */
    public static boolean sha1Verify(String content, String sign, PublicKey publicKey, String charset) throws SignatureException {
        return verify(content, sign, publicKey, charset, RsaSignAlgorithms.SHA1);
    }

    /**
     * SHA256WithRSA验签
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 公钥
     * @param charset   编码格式
     * @return true or false
     */
    public static boolean sha256Verify(String content, String sign, PublicKey publicKey, String charset) throws SignatureException {
        return verify(content, sign, publicKey, charset, RsaSignAlgorithms.SHA256);
    }

    /**
     * SHA256WithRSA验签
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 公钥字符串
     * @param charset   编码格式
     * @return true or false
     */
    public static boolean sha256Verify(String content, String sign, String publicKey, String charset) throws SignatureException {
        PublicKey key = buildPublicKey(publicKey);
        return sha256Verify(content, sign, key, charset);
    }

    /**
     * SHA512WithRSA验签
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 公钥
     * @param charset   编码格式
     * @return true or false
     */
    public static boolean sha512Verify(String content, String sign, String publicKey, String charset) throws SignatureException {
        PublicKey key = buildPublicKey(publicKey);
        return sha512Verify(content, sign, key, charset);
    }

    /**
     * SHA512WithRSA验签
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 公钥
     * @param charset   编码格式
     * @return true or false
     */
    public static boolean sha512Verify(String content, String sign, PublicKey publicKey, String charset) throws SignatureException {
        return verify(content, sign, publicKey, charset, RsaSignAlgorithms.SHA512);
    }

    /**
     * RSA验签
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey PKCS8格式公钥
     * @param charset   编码格式
     * @param algorithm 算法
     * @return true or false
     */
    public static boolean verify(String content, String sign, String publicKey, String charset, String algorithm) throws SignatureException {
        PublicKey key = buildPublicKey(publicKey);
        return verify(content, sign, key, charset, algorithm);
    }

    /**
     * RSA验签
     *
     * @param content   待签名数据
     * @param sign      签名值
     * @param publicKey 公钥
     * @param charset   编码格式
     * @param algorithm 算法
     * @return true or false
     */
    public static boolean verify(String content, String sign, PublicKey publicKey, String charset, String algorithm) throws SignatureException {
        try {
            Signature signature = Signature.getInstance(algorithm);
            signature.initVerify(publicKey);
            signature.update(content.getBytes(charset));
            return signature.verify(Base64.decodeBase64(sign));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | SignatureException | InvalidKeyException e) {
            throw new SignatureException(e);
        }
    }

}