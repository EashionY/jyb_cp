package cn.jyb.util;
/**
 * 微信支付配置文件
 * @author Eashion
 *
 */
public class WxpayConfig {

	public static final String APPID = "wx304a916155d09656";//应用号
	public static final String MCH_ID = "1480632002";//商户号
	public static final String API_KEY = "dFMhDmgw1mT4501Re03BcaIjkiJ7lH8U";//API密钥,微信支付后台查看
	public static final String SIGN_TYPE = "MD5";//签名加密方式
	
	//下单地址
	public static final String UNIFIED_ORDER_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
}
