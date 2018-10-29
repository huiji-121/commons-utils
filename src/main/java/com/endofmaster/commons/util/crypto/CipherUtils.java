package com.endofmaster.commons.util.crypto;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.security.spec.InvalidParameterSpecException;
import java.util.zip.CRC32;

/**
 * @author YQ.Huang
 * @update ZM.Wang
 */
public class CipherUtils {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public static long crc32(String data) {
        if (data == null) {
            return -1;
        }
        byte bytes[] = data.getBytes(StandardCharsets.UTF_8);
        CRC32 crc32 = new CRC32();
        crc32.update(bytes, 0, bytes.length);
        return crc32.getValue();
    }

    public static byte[] decryptHex(byte[] key, String hex, String algorithm, String transform) throws CryptoException {
        try {
            byte[] target = Hex.decodeHex(hex.toCharArray());
            return decrypt(key, target, algorithm, transform);
        } catch (DecoderException e) {
            throw new CryptoException(e);
        }
    }

    public static String encryptHex(byte[] key, byte[] target, String algorithm, String transform) throws CryptoException {
        return Hex.encodeHexString(encrypt(key, target, algorithm, transform));
    }

    public static byte[] encrypt(byte[] key, byte[] target, String algorithm, String transform) throws CryptoException {
        SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
        return encrypt(keySpec, target, transform);
    }

    public static byte[] encrypt(Key key, byte[] target, String transform) throws CryptoException {
        try {
            Cipher cipher = Cipher.getInstance(transform);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(target);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException
                | IllegalBlockSizeException | InvalidKeyException e) {
            throw new CryptoException(e);
        }
    }

    public static byte[] decrypt(byte[] key, byte[] target, String algorithm, String transform) throws CryptoException {
        SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
        return decrypt(keySpec, target, transform);
    }

    public static byte[] decrypt(Key key, byte[] target, String transform) throws CryptoException {
        try {
            Cipher cipher = Cipher.getInstance(transform);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(target);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException
                | IllegalBlockSizeException | InvalidKeyException e) {
            throw new CryptoException(e);
        }
    }

    public static byte[] decrypt(byte[] key, byte[] target, String algorithm, String transform, AlgorithmParameters parameters) throws CryptoException {
        SecretKeySpec keySpec = new SecretKeySpec(key, algorithm);
        return decrypt(keySpec, target, transform, parameters);
    }

    public static byte[] decrypt(Key key, byte[] target, String transform, AlgorithmParameters parameters) throws CryptoException {
        try {
            Cipher cipher = Cipher.getInstance(transform);
            cipher.init(Cipher.DECRYPT_MODE, key, parameters);
            return cipher.doFinal(target);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | BadPaddingException
                | IllegalBlockSizeException | InvalidKeyException | InvalidAlgorithmParameterException e) {
            throw new CryptoException(e);
        }
    }

    public static AlgorithmParameters generateIV(byte[] iv, String algorithm) throws CryptoException {
        try {
            AlgorithmParameters params = AlgorithmParameters.getInstance(algorithm);
            params.init(new IvParameterSpec(iv));
            return params;
        } catch (NoSuchAlgorithmException | InvalidParameterSpecException e) {
            throw new CryptoException(e);
        }
    }
}
