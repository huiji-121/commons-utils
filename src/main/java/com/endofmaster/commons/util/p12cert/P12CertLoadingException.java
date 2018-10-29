package com.endofmaster.commons.util.p12cert;

/**
 * @author YQ.Huang
 */
public class P12CertLoadingException extends RuntimeException {
    public P12CertLoadingException(Throwable cause) {
        super("Unable to load certificate", cause);
    }
}
