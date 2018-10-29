package com.jifenke.commons.util.sign;

import org.junit.Assert;
import org.junit.Test;

import java.security.SignatureException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YQ.Huang
 */
public class RsaSignUtilsTest {

    /**
     * 测试数据来源于支付宝即时到账
     */
    @Test
    public void verify_alipay_direct() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("discount", "0.00");
        params.put("payment_type", "1");
        params.put("subject", "商品XXX");
        params.put("trade_no", "2016100921001004660282899545");
        params.put("buyer_email", "huang.yu.qiang@163.com");
        params.put("gmt_create", "2016-10-09 21:34:56");
        params.put("notify_type", "trade_status_sync");
        params.put("quantity", "1");
        params.put("out_trade_no", "1476020048144");
        params.put("seller_id", "2088421378707458");
        params.put("notify_time", "2016-10-09 22:59:04");
        params.put("body", "商品XXX");
        params.put("trade_status", "TRADE_SUCCESS");
        params.put("is_total_fee_adjust", "N");
        params.put("total_fee", "0.01");
        params.put("gmt_payment", "2016-10-09 21:35:08");
        params.put("seller_email", "haixushi@hotmail.com");
        params.put("price", "0.01");
        params.put("buyer_id", "2088302809365662");
        params.put("notify_id", "b57d36ebda496dbc9dae57be3e3396el3e");
        params.put("use_coupon", "N");
        String content = PresignUtils.createLinkString(params, true);
        String sign = "GOMQaW4XF06pxOuzpQB7UySwuIamI9o4SsvNoY4W6K/nI1P15ixvFeBnPL0VpkwzGp5MREhMM+bNGURYtPZDwYI+azXRlbg7lxLSRowdph4VHfQCB95iuRbNQQC9exTM8kjss+LjNcQbliWOdHP00qMw73PTmR+Fshz59975i4s=";
        String rsaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";

        Assert.assertTrue(RsaSignUtils.sha1Verify(content, sign, rsaPublicKey, "UTF-8"));
    }

    /**
     * 测试数据来源于支付宝手机网站支付
     */
    @Test
    public void rsaVerify_alipay_wap() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("total_amount", "0.01");
        params.put("buyer_id", "2088302809365662");
        params.put("trade_no", "2016101821001004660290416011");
        params.put("notify_time", "2016-10-18 11:56:37");
        params.put("subject", "XXX商品");
        params.put("notify_type", "trade_status_sync");
        params.put("out_trade_no", "order1476762985733");
        params.put("trade_status", "TRADE_SUCCESS");
        params.put("gmt_payment", "2016-10-18 11:56:36");
        params.put("gmt_create", "2016-10-18 11:56:35");
        params.put("app_id", "2016072401659296");
        params.put("seller_id", "2088421378707458");
        params.put("notify_id", "08d679ba91e76ad47873d09550fae83l3e");
        String content = PresignUtils.createLinkString(params, true);
        String sign = "iIbnYN7mpeWwQjWx0IxlgmbaVKCbCHSaWZ6k7xeM/Bc5vFcYBI4GXTbgQc9iV0m249cibUNo/WWUH8Vf9cWpUroIFupxRxfBMGl/Qag2EtcltLUhL6VJHN2kork4yLzIx9+hCK28lrqbp43Hke+uKpakLFB05wLVtmT1Y+ua3hc=";
        String rsaPublicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

        Assert.assertTrue(RsaSignUtils.sha1Verify(content, sign, rsaPublicKey, "UTF-8"));
    }

    /**
     * 测试数据来自支付宝sdk签名方法
     */
    @Test
    public void sha256SignTest() throws SignatureException {
        String content = "test";
        String privateKey = "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQCqyOEbZgAUwQlEPJxT/fTgKNEcQfm7rPMT4tn3Qyh1MbKAJVqrGwfch50Osjbyo9hCkr6cXv+krIzRV2oaiKOXKr+6qfKgchi9qX3W8htP9u5gSnNv3/d4T0n+b3QBWgw92WL5pmSPC1S5lIf5QtQfI3R8X9RKUC1UsDtvXtKjt8SCJdfZkfLkNkEfs6vAF+76TDIp5Tlz54MzwLiJuw6HW2ZbjNDOAl+BtBtAyp1WqHg54yhW2xhx6lTZNI115RWf6btx1cas9JPa3ewmVfPBexH2L/+1wsyDPOYgBEnKcGFUiw16XLWUQNMZS7fxwxng9ca+Nbed6GpjmxZy+gZZAgMBAAECggEAUKKd3lDPkvz9ghFYAjQyzS+YY84mulzTPD1sillUHdlEHUsFvyn5ET+agRjV5sRDVFNy2ePksfyiVCkAuNxw2OvfMKQTIXwdgFcpSjZvfNbq/93HGThW4KAXIKHA1O6OPxmHqLXOXteLvj+J37+U5crYseyREDFA5e57cX8E1zUjkpeTB/4xAmsqo4bdTwybDMZVXT/4eS0Uau8BKjsUnXQt2e04NnUJD0VhDt2xoInk41Ycq9h8sXlq/NvhjqVQr7k2TWtXGhrUqtqz1UWjK3p7mKXyzekLtBeg5XMbBAMfohdco1k2968wBAJi7SQkZjzrqIHSznsf75upm7DJ8QKBgQDmx6k31+sHE/ejOZIIjBj+1xtyZuEtFKK2TDWfjS9C+CK0d5Dyol3ke5/R89grhryDAjMv48idgofiPDHAUSR3xBTRPM2mEUbVkqelSkdc0XbSlqP0XOGg1p8AS7yY0DrSZ2N0aI4pppRS+LT/Z8NAVgBkx7gsTykEVRtnECipVQKBgQC9csf27/ZA6mUco8rHGgB+Go7n9PE4e+oe0WzmOl7IEm3XAwqJBC1KnUDRk3IY318zevUX+rP2O2giylucjN1H4evyjLcDCSa3jvqDYxkI+9Vtr5pgz7F+sgqTXE0MbeByHfeQxhuI++TxelNeX13c4c8JcYsLJuuwuaShu3AY9QKBgQDjPa7ugfm3Wn35h3K4X9ss9HreR1dqoGsnvhr/JHJpKxRhR2q+wApcNS7F+aneEioFhmEIwu6DykgrCMKkcEHMfJNTBOAqqdBp9KKHK4DhcI/kBNkkgWDakvq1u93VSxTgoVVZBqsVcCWVHbh71Zx4+TYiBlbfqoLk9TiqX0rqkQJ/RwrD8dYKgk1jnVnOsNsPVDqhXFp+GrBdGzFtpOg+oshGaFq7mKX4VgKiS/T+1FTvXl69ikB5O90LMACyWgJ4+Hu//zTFbF4CJtN3Hc4LlVLatkcjnSpFZT1euePjAMYzZJfYPnIL4VKUh/lXSgx5JluHSANMIOJ3f84d7ohbgQKBgAt/WMhkZte8w1fUiOX/zEEFAH4HMTf5OkJQD4FsrBVQvbqsJUxSNAP2I8lDJNmUnaK8pHdJRTEWQwTaobaW5SKAZd0wFdnWEsq5lIXCDaGrzmTGg1YrjVMLcUEyBf9s4mghGLfsKgtQkd5e2O1vM6so+qSwyOC5u+iIV+bFYJrV";
        String target = "F6UTi2Vq3MYr6kA9vMXagqI8pRqTEWOjB+j5dKfdNqhiZKSy4hwdfY1eb/z+LkNuW1g+aQdlPeTmwGYWRutHnG91MHPwxzneIbVB/M/pnZJ0SxCBezp4KDy7dimoST4pWPiTGsiDM4gnPfD8MvHNFroukVyYDFhEAHkQm6ucm4uLUmD5REQATuTFX2a3mmICCi1Uj8pPhi1nHEcYtUBkPr/pLHyS1CnIv0umF2dO2wqIb9lhjz4HSTUG6oLXMdZqagNycxaemvpu+++Ad6sk0Eq9pfkxsywtxa/fsnPkK4hQsdY3VqvQxSOd/PO/Z4MlQHkV6/z/5plkxkzJ79uGGw==";
        String sign = RsaSignUtils.sha256Sign(content, privateKey, "gbk");

        Assert.assertEquals(target, sign);
    }

    @Test
    public void sha256Verify() throws SignatureException {
        String content = "test";
        String sign = "F6UTi2Vq3MYr6kA9vMXagqI8pRqTEWOjB+j5dKfdNqhiZKSy4hwdfY1eb/z+LkNuW1g+aQdlPeTmwGYWRutHnG91MHPwxzneIbVB/M/pnZJ0SxCBezp4KDy7dimoST4pWPiTGsiDM4gnPfD8MvHNFroukVyYDFhEAHkQm6ucm4uLUmD5REQATuTFX2a3mmICCi1Uj8pPhi1nHEcYtUBkPr/pLHyS1CnIv0umF2dO2wqIb9lhjz4HSTUG6oLXMdZqagNycxaemvpu+++Ad6sk0Eq9pfkxsywtxa/fsnPkK4hQsdY3VqvQxSOd/PO/Z4MlQHkV6/z/5plkxkzJ79uGGw==";
        String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqsjhG2YAFMEJRDycU/304CjRHEH5u6zzE+LZ90ModTGygCVaqxsH3IedDrI28qPYQpK+nF7/pKyM0VdqGoijlyq/uqnyoHIYval91vIbT/buYEpzb9/3eE9J/m90AVoMPdli+aZkjwtUuZSH+ULUHyN0fF/USlAtVLA7b17So7fEgiXX2ZHy5DZBH7OrwBfu+kwyKeU5c+eDM8C4ibsOh1tmW4zQzgJfgbQbQMqdVqh4OeMoVtsYcepU2TSNdeUVn+m7cdXGrPST2t3sJlXzwXsR9i//tcLMgzzmIARJynBhVIsNely1lEDTGUu38cMZ4PXGvjW3nehqY5sWcvoGWQIDAQAB";
        Assert.assertTrue(RsaSignUtils.sha256Verify(content, sign, publicKey, "gbk"));
    }
}