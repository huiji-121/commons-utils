package com.endofmaster.commons.util.p12cert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * @author YQ.Huang
 */
public abstract class P12CertUtils {

    private static final Logger logger = LoggerFactory.getLogger(P12CertUtils.class);

    public static P12Cert load(InputStream inputStream, char[] password) {
        KeyStore keyStore = loadKeyStore(inputStream, password);
        try {
            Enumeration<String> aliases = keyStore.aliases();
            String keyAlias = null;
            if (aliases.hasMoreElements()) {
                keyAlias = aliases.nextElement();
            }
            logger.debug("keyAlias:" + keyAlias);
            PrivateKey privateKey = (PrivateKey) keyStore.getKey(keyAlias, password);
            X509Certificate certificate = (X509Certificate) keyStore.getCertificate(keyAlias);
            PublicKey publicKey = certificate.getPublicKey();
            BigInteger serialNumber = certificate.getSerialNumber();
            return new P12Cert(keyStore, serialNumber, privateKey, publicKey);
        } catch (Exception e) {
            throw new P12CertLoadingException(e);
        }
    }

    public static PrivateKey loadPrivateKey(InputStream inputStream, char[] password) {
        KeyStore keyStore = loadKeyStore(inputStream, password);
        try {
            Enumeration<String> aliases = keyStore.aliases();
            String keyAlias = null;
            if (aliases.hasMoreElements()) {
                keyAlias = aliases.nextElement();
            }
            logger.debug("keyAlias:" + keyAlias);
            return (PrivateKey) keyStore.getKey(keyAlias, password);
        } catch (Exception e) {
            throw new P12CertLoadingException(e);
        }
    }

    public static PublicKey loadPublicKey(InputStream inputStream, char[] password) {
        KeyStore keyStore = loadKeyStore(inputStream, password);
        try {
            Enumeration<String> aliases = keyStore.aliases();
            String keyAlias = null;
            if (aliases.hasMoreElements()) {
                keyAlias = aliases.nextElement();
            }
            logger.debug("keyAlias:" + keyAlias);
            Certificate certificate = keyStore.getCertificate(keyAlias);
            return certificate.getPublicKey();
        } catch (Exception e) {
            throw new P12CertLoadingException(e);
        }
    }

    public static KeyStore loadKeyStore(InputStream inputStream, char[] password) {
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(inputStream, password);
            return ks;
        } catch (Exception e) {
            throw new P12CertLoadingException(e);
        }
    }


}
