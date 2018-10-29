package com.endofmaster.commons.util.p12cert;

import java.math.BigInteger;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author YQ.Huang
 */
public class P12Cert {
    private final KeyStore keyStore;
    private final BigInteger certId;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public P12Cert(KeyStore keyStore, BigInteger certId, PrivateKey privateKey, PublicKey publicKey) {
        this.keyStore = keyStore;
        this.certId = certId;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public BigInteger getCertId() {
        return certId;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
