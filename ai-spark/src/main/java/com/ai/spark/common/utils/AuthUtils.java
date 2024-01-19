package com.ai.spark.common.utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import okhttp3.HttpUrl;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Locale;
import java.util.Objects;

public class AuthUtils {

    /**
     * 日期格式化
     */
    public final static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
    public final static String preStr = "host: %s\n" +
            "date: %s\n" +
            "%s %s HTTP/1.1";
    private static final char[] MD5_TABLE = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    @Getter
    @AllArgsConstructor
    public enum RequestMethod {
        GET("GET"),
        POST("POST");
        private String method;
    }

    /**
     * 鉴权方法，适用于对话接口
     *
     * @param requestMethod 请求方式
     * @param hostUrl       地址
     * @param apiKey        apikey
     * @param apiSecret     apiSecret
     * @return 鉴权信息
     */
    public static String getAuthUrl(String requestMethod, String hostUrl, String apiKey, String apiSecret) throws MalformedURLException, InvalidKeyException, NoSuchAlgorithmException {
        URL url = new URL(hostUrl);
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of("GMT"));
        String date = now.format(dateTimeFormatter);
        // SHA256加密
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec spec = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "hmacsha256");
        mac.init(spec);
        byte[] hexDigits = mac.doFinal(String.format(preStr, url.getHost(), date, requestMethod, url.getPath()).getBytes(StandardCharsets.UTF_8));
        // Base64加密
        String sha = Base64.getEncoder().encodeToString(hexDigits);
        // 拼接
        String authorization = String.format("api_key=\"%s\", algorithm=\"%s\", headers=\"%s\", signature=\"%s\"", apiKey, "hmac-sha256", "host date request-line", sha);
        // 拼接地址
        HttpUrl httpUrl = Objects.requireNonNull(HttpUrl.parse("https://" + url.getHost() + url.getPath())).newBuilder().
                addQueryParameter("authorization", Base64.getEncoder().encodeToString(authorization.getBytes(StandardCharsets.UTF_8))).
                addQueryParameter("date", date).
                addQueryParameter("host", url.getHost()).
                build();
        return httpUrl.toString();
    }

    /**
     * 获取签名，适用于文档问答
     *
     * @param appId  签名的key
     * @param secret 签名秘钥
     * @return 返回签名
     */
    public static String getSignature(String appId, String secret, long ts) {
        try {
            String auth = md5(appId + ts);
            return hmacSHA1Encrypt(auth, secret);
        } catch (SignatureException e) {
            return null;
        }
    }

    /**
     * sha1加密
     *
     * @param encryptText 加密文本
     * @param encryptKey  加密键
     * @return 加密
     */
    private static String hmacSHA1Encrypt(String encryptText, String encryptKey)
            throws SignatureException {
        byte[] rawHmac;
        try {
            byte[] data = encryptKey.getBytes(StandardCharsets.UTF_8);
            SecretKeySpec secretKey = new SecretKeySpec(data, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
            byte[] text = encryptText.getBytes(StandardCharsets.UTF_8);
            rawHmac = mac.doFinal(text);
        } catch (InvalidKeyException e) {
            throw new SignatureException("InvalidKeyException:" + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new SignatureException(
                    "NoSuchAlgorithmException:" + e.getMessage()
            );
        }
        return Base64.getEncoder().encodeToString(rawHmac);
    }

    private static String md5(String cipherText) {
        try {
            byte[] data = cipherText.getBytes();
            // 信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
            MessageDigest mdInst = MessageDigest.getInstance("MD5");

            // MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
            mdInst.update(data);

            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
            byte[] md = mdInst.digest();

            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) { // i = 0
                str[k++] = MD5_TABLE[byte0 >>> 4 & 0xf]; // 5
                str[k++] = MD5_TABLE[byte0 & 0xf]; // F
            }
            // 返回经过加密后的字符串
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }
}
