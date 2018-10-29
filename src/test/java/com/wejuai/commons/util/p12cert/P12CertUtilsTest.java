package com.jifenke.commons.util.p12cert;

import com.jifenke.commons.util.sign.RsaSignUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.InputStream;
import java.math.BigInteger;

/**
 * @author YQ.Huang
 */
public class P12CertUtilsTest {

    @Test
    public void load() throws Exception {
        String certPath = "/cvw643.pfx";
        String certPwd = "000000";
        BigInteger certId = new BigInteger("40220995861346480087409489142384722381");
        P12Cert p12Cert;
        try (InputStream in = this.getClass().getResourceAsStream(certPath)) {
            p12Cert = P12CertUtils.load(in, certPwd.toCharArray());
        }

        Assert.assertEquals(certId, p12Cert.getCertId());

        String sign = RsaSignUtils.sha1Sign("content", p12Cert.getPrivateKey(), "UTF-8");
        Assert.assertTrue(RsaSignUtils.sha1Verify("content", sign, p12Cert.getPublicKey(), "UTF-8"));
    }

}