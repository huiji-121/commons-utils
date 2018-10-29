package com.endofmaster.commons.util.sign;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author YQ.Huang
 */
public class Md5SignUtilsTest {

    @Test
    public void sign() throws Exception {
        String content = "appid=wxd930ea5d5a258f4f&body=test&device_info=1000&mch_id=10000100&nonce_str=ibuaiVcKdpRxkhJA";

        String sign = Md5SignUtils.sign(content, "&key=192006250b4c09247ec02edce69f6a2d", "UTF-8");

        Assert.assertEquals("9A0A8659F005D6984697E2CA0A9CF3B7", sign.toUpperCase());
    }

    @Test
    public void sign_weixin() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("appId", "wx3e0d4215b7601aaf");
        params.put("timeStamp", "1475921804");
        params.put("nonceStr", "DCdKfYopf095h4XOsLwBGLmzYPB1tUyC");
        params.put("package", "prepay_id=wx20161008181644ab15d762230996647661");
        params.put("signType", "MD5");

        String content = PresignUtils.createLinkString(params, true);
        String sign = Md5SignUtils.sign(content, "&key=" + "MIIEvQIBADANBgkqhkiG9w0BAQEFAASC", "UTF-8").toUpperCase();

        Assert.assertEquals("appId=wx3e0d4215b7601aaf&nonceStr=DCdKfYopf095h4XOsLwBGLmzYPB1tUyC&package=prepay_id=wx20161008181644ab15d762230996647661&signType=MD5&timeStamp=1475921804", content);
        Assert.assertEquals("9D78C6547F5FA331C94237A2447972F0", sign);
    }

    /**
     * 测试数据来源于支付宝即时到账
     *
     * @throws Exception
     */
    @Test
    public void verify() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("discount", "0.00");
        params.put("payment_type", "1");
        params.put("subject", "XXX商品");
        params.put("trade_no", "2016101821001004660297691678");
        params.put("buyer_email", "huang.yu.qiang@163.com");
        params.put("gmt_create", "2016-10-18 11:23:10");
        params.put("notify_type", "trade_status_sync");
        params.put("quantity", "1");
        params.put("out_trade_no", "order1476760957671");
        params.put("seller_id", "2088421378707458");
        params.put("notify_time", "2016-10-18 11:23:21");
        params.put("trade_status", "TRADE_SUCCESS");
        params.put("is_total_fee_adjust", "N");
        params.put("total_fee", "0.01");
        params.put("gmt_payment", "2016-10-18 11:23:20");
        params.put("seller_email", "haixushi@hotmail.com");
        params.put("price", "0.01");
        params.put("buyer_id", "2088302809365662");
        params.put("notify_id", "cf03fbdf76743cedebf6fdc00d53d5dl3e");
        params.put("use_coupon", "N");
        String content = PresignUtils.createLinkString(params, true);
        String sign = "939414adfc5d2bbeba686c0e7f16bbe1";
        String md5Key = "e7syd6pxavvqyc1hnkwd52730ufe9azm";

        Assert.assertTrue(Md5SignUtils.verify(content, sign, md5Key, "UTF-8"));
    }
}