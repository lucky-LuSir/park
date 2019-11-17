package com.kfwy.park.common.alipay;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @Author gengtao
 * @Date 2019/7/25 9:26
 *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 */

public class AlipayConfig {
    /**
     *应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
     **/
    public static String app_id = "2016101000652301";

    /**
     *商户私钥，您的PKCS8格式RSA2私钥
     **/
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCwAMk+OwBGsNSAmChl/k/IxQG2uvKsgbGiv4q5T4ejdNE8sJDqVdWuYWTqWNVu21/RZ3pxKeQ4AewSzMexrFDpg6D5DijypZcpYWkB/siyKPYsglIJnoT5fr+Q2XoPRoBdiP2VsQHxvys47qkw4cAGedbd9BPWQGxL/XAMCeoz9meVbVeF7qBHGsmOnnKmWHLV1CUaCjlTb7HgATYXk+qBQ5HhHffBj8Ki+HyYUASkBx2fWnbY6mAcGIE3LTIPIMedUAWlvJQCi9fEJDMtSfCkIh+07w5zy1p0q0pkMznDzFQJoanrfWgoju1hKP7imlGPAk1DjyyNgXroM4evB28ZAgMBAAECggEAd/HhM/bTpllmSMjpZzDcQK/6DgTz0xqD66zrpoSLpJq86cZOzFR4zmFze73UnrsEXtpNeIebD4xHzTwfr3cGoU1iS7kZsNum272V6ZUwQzJlKkoaDJIqfzmg5d9ALt7WJmhwoS5Da/PjSeZkCXsf+YnZI+PuDOpUo6jxTlMHeCcDY4ZwV2X8IAb1eQTK1RA44Ws1eF0WssjZLIxEVJ4rucFh8vJ3i/1OaDEKnRM+vqixwEduHAHhjRc/rZFQXhk9ZdqhWowgKw/DIQXxyZBR4xlM86p4g1zfNnV01Z7qKPn3mLzIDF8MjHbkjKAkw9kYKMrWDExsM8ecU7115byVLQKBgQD/9cthkQul4uNMfs1nnCQ4Dvit0XOerbeq2KQlM3ziVFGoDE90OfbxaUL5y5InR0w579AIK8ndqZiH/zX3iaMX2HlhiywvEtvjh8c4sfRkuJjLFYLFRgkLpSrWa4Sr7+lq+iRhM4M15tzHIpzHDsVW1h1kQW1SwOSfsVxzqizWhwKBgQCwB826yzKBBqZvtmeMyTjd8M7T88V9btnOJlMDWOykqQVbfzpz1VafwFReiK7TUJ2/Gv0A2irqUzj4GKK8fmLBdOzij/RexgR2eySxcDg1Y+RZ+fjFhpI+SKmpvzF1W/QCxm31WrJ95XxVVAVXLMAZLQ+VnwJf4G81II5a4s9VXwKBgQC+vaZQEMzDU+7apGvhWCRNeiqrLTih+Qaz1DmOV24rI3auam4TSNbTVAPLLn69so4FZsZ6MFeATdATGIZY70KQlIrvu8yzT7R5PEej4kkjfcSvIlqPM/+to+nmW71Ee8ZQKBRQMxkGZAIGi66VUFaSvYCto2wajRBSSNOChkBfpQKBgDY2AQaww1n3CVTIEPXLlobLPXcK0NxDQjJ5nQEf5qkDIJJACWlY3zeNjK4cW/LaFeqfziDwD11gyd+l/qAcMB+x+a4AdVqmkwNGA1Pd4oGWxOFs2U9DlVA3IoETT5D8A5bDM2PnTab2AKdqm8ZwEqzuMql6ccnDHZx37T9SMFXzAoGARBLlJqurviU8ke21Lpsh6TfpC8W1Z7j/ViJRUCy1cAOhBRU8BG4oY3sxIEyKKw2h7JqKvNfAG9x3LEr6Qbxoh8P8HBIO9wHxzaJcvWTFCh2xTLTKLikO4c0/sBxoFXfMjZ2GSdE+OOsdoaiIZO5JDVNwwidTZ6aXOCJlBUVO0HU=";

    /**
     *支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
     * 注意 使用的是支付宝公钥,而不是自己用软件生成的公钥,否则会出现验签失败
     **/
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoYLuGBIQi6v34iJuif/ESbrw8aaVgAUW4NnsPmWWON1oUeT3WAj1N1fJdpnps0sK1Yiyj1tsBMLTX1xLcmE98qqyhY6YCx3z/UwfR3M93m1JqD5093+dp0834xResHrWP21dbMIWzjNc3j3ToytomFom3uCheg2ql984zvwkGq3hgLRblH827AOICU4G2Nb6V+1ak3HMsbGk0DyI1DKXtuFuktlgqjQ/yPPYW4DJ9ap1JlniF5/g7PrV86G8OzjKC3slAocYHcajJlgQ1ZhY8wYOWUdpEfKbQOEYK1JcCkM206rSfvtMYuaU9NQ6u8DcCEdGipwdSghavGNckwvykwIDAQAB";

    /**
     *服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     **/
    public static String notify_url = "http://poms.yuanquwuyou.com/park/alipay/notifyUrl";

    /**
     *页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
     **/
    public static String return_url = "http://poms.yuanquwuyou.com/park/alipay/returnUrl";

    /**
     *签名方式
     **/
    public static String sign_type = "RSA2";

    /**
     *字符编码格式
     **/
    public static String charset = "utf-8";

    /**
     *支付宝网关
     **/
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    /**
     *日志地址
     **/
    public static String log_path = "D:\\";

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}