package cn.jyb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 微信公众号工具类
 * @author Eashion
 *
 */
@Component
public class WxpublicUtil {

	public static String ACCESS_TOKEN = null;
	
	public static String JSAPI_TICKET = null;
	
	//日志文件
	protected static Logger logger = LoggerFactory.getLogger(WxpublicUtil.class);
	
	/**
	 * 获取access_token，每隔3600s获取一次
	 */
	@Scheduled(fixedRate = 3600000)
	public static void getAccessToken(){
		// 访问微信服务器
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" 
		           + WxpublicConfig.APPID + "&secret="+ WxpublicConfig.APPSECRET;
		try {
			String result = HttpUtil.sendGet(url, "UTF-8");
//			System.out.println(result);
			JSONObject json = JSONObject.fromObject(result);
			ACCESS_TOKEN = json.getString("access_token");
			logger.error("定时ACCESS_TOKEN:"+ACCESS_TOKEN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 从缓存中获取token
	 * @return
	 */
	public static String getFromCache(){
		return ACCESS_TOKEN;
	}
	/**
	 * 强制获取token
	 * @return
	 */
	public static synchronized String getNew(){
		WxpublicUtil.getAccessToken();
		return ACCESS_TOKEN;
	}
	/**
	 * 获取jsapi_ticket，每隔3600s获取一次
	 */
	@Scheduled(fixedRate = 3600000)
	public static void getJsapiTicket(){
		String accessToken = getFromCache();
		if(accessToken == null){
			accessToken = getNew();
		}
		String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+accessToken+"&type=jsapi";
		String result;
		try {
			result = HttpUtil.sendGet(url, "UTF-8");
			JSONObject json = JSONObject.fromObject(result);
			JSAPI_TICKET = json.getString("ticket"); 
			logger.error("定时JSAPI_TICKET:"+JSAPI_TICKET);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 从缓存中获取ticket
	 * @return
	 */
	public static String getTicketFromCache(){
		return JSAPI_TICKET;
	}
	/**
	 * 强制更新ticket
	 * @return
	 */
	public static synchronized String getTicketNew(){
		WxpublicUtil.getJsapiTicket();
		return JSAPI_TICKET;
	}
	/**
	 * 字典序排序方法
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static String sort(String token, String timestamp, String nonce) {
	    String[] strArray = { token, timestamp, nonce };
	    Arrays.sort(strArray);
	 
	    StringBuilder sbuilder = new StringBuilder();
	    for (String str : strArray) {
	        sbuilder.append(str);
	    }
	    return sbuilder.toString();
	}
	
	/**
	 * 哈希算法加密
	 * @param decript
	 * @return
	 */
	public static String SHA1(String decript){
		try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            digest.update(decript.getBytes());
            byte messageDigest[] = digest.digest();
            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            // 字节数组转换为 十六进制 数
            for (int i = 0; i < messageDigest.length; i++) {
                String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
                if (shaHex.length() < 2) {
                    hexString.append(0);
                }
                hexString.append(shaHex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }
	/**
	 * 生成微信公众号的菜单
	 * @return
	 */
	public static String getMenu(){
		// 菜单一
		JSONObject button1 = new JSONObject();
		button1.put("type", "view");
		String button1Name = "首页";
		button1.put("name", button1Name);
		String button1Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ WxpublicConfig.APPID
				+ "&redirect_uri="
				+ WxpayUtil.urlEncodeUTF8("http://api.drivingyeepay.com/jyb/wxpublic/check")
				+ "&response_type=code&scope=snsapi_base&state="
				+ WxpayUtil.urlEncodeUTF8(button1Name)
				+ "#wechat_redirect";
		button1.put("url", button1Url);
		// 菜单二
		JSONObject button2 = new JSONObject();
		button2.put("name", "一键服务");
		// 菜单二的子菜单
		JSONArray subButton2 = new JSONArray();
		// 子菜单1
		JSONObject button21 = new JSONObject();
		button21.put("type", "view");
		String button21Name = "货拉宝";
		button21.put("name", button21Name);
		String button21Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ WxpublicConfig.APPID
				+ "&redirect_uri="
				+ WxpayUtil.urlEncodeUTF8("http://api.drivingyeepay.com/jyb/wxpublic/check")
				+ "&response_type=code&scope=snsapi_base&state="
				+ WxpayUtil.urlEncodeUTF8(button21Name)
				+ "#wechat_redirect";
		button21.put("url", button21Url);
		subButton2.add(button21);
		// 子菜单2
		JSONObject button22 = new JSONObject();
		button22.put("type", "view");
		String button22Name = "一键挪车";
		button22.put("name", button22Name);
		String button22Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ WxpublicConfig.APPID
				+ "&redirect_uri="
				+ WxpayUtil.urlEncodeUTF8("http://api.drivingyeepay.com/jyb/wxpublic/check")
				+ "&response_type=code&scope=snsapi_base&state="
				+ WxpayUtil.urlEncodeUTF8(button22Name)
				+ "#wechat_redirect";
		button22.put("url", button22Url);
		subButton2.add(button22);
		// 子菜单3
//		JSONObject button23 = new JSONObject();
//		button23.put("type", "view");
//		String button23Name = "违章查询&处理";
//		button23.put("name", button23Name);
//		String button23Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
//				+ WxpublicConfig.APPID
//				+ "&redirect_uri="
//				+ WxpayUtil.urlEncodeUTF8("http://api.drivingyeepay.com/jyb/wxpublic/check")
//				+ "&response_type=code&scope=snsapi_base&state="
//				+ WxpayUtil.urlEncodeUTF8(button23Name)
//				+ "#wechat_redirect";
//		button23.put("url", button23Url);
//		subButton2.add(button23);
		// 子菜单4
		JSONObject button24 = new JSONObject();
		button24.put("type", "view");
		String button24Name = "模拟练习";
		button24.put("name", button24Name);
		String button24Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ WxpublicConfig.APPID
				+ "&redirect_uri="
				+ WxpayUtil.urlEncodeUTF8("http://api.drivingyeepay.com/jyb/wxpublic/check")
				+ "&response_type=code&scope=snsapi_base&state="
				+ WxpayUtil.urlEncodeUTF8(button24Name)
				+ "#wechat_redirect";
		button24.put("url", button24Url);
		subButton2.add(button24);
		button2.put("sub_button", subButton2);
		// 菜单三
		JSONObject button3 = new JSONObject();
		button3.put("name", "用户服务");
		// 菜单二的子菜单
		JSONArray subButton3 = new JSONArray();
		// 子菜单1
//		JSONObject button31 = new JSONObject();
//		button31.put("type", "view");
//		String button31Name = "订单查询";
//		button31.put("name", button31Name);
//		String button31Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
//				+ WxpublicConfig.APPID
//				+ "&redirect_uri="
//				+ WxpayUtil.urlEncodeUTF8("http://api.drivingyeepay.com/jyb/wxpublic/check")
//				+ "&response_type=code&scope=snsapi_base&state="
//				+ WxpayUtil.urlEncodeUTF8(button31Name)
//				+ "#wechat_redirect";
//		button31.put("url", button31Url);
//		subButton3.add(button31);
		// 子菜单2
		JSONObject button32 = new JSONObject();
		button32.put("type", "view");
		String button32Name = "个人信息";
		button32.put("name", button32Name);
		String button32Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ WxpublicConfig.APPID
				+ "&redirect_uri="
				+ WxpayUtil.urlEncodeUTF8("http://api.drivingyeepay.com/jyb/wxpublic/check")
				+ "&response_type=code&scope=snsapi_base&state="
				+ WxpayUtil.urlEncodeUTF8(button32Name)
				+ "#wechat_redirect";
		button32.put("url", button32Url);
		subButton3.add(button32);
		// 子菜单3
		JSONObject button33 = new JSONObject();
		button33.put("type", "view");
		String button33Name = "APP下载";
		button33.put("name", button33Name);
		//APP下载地址
		String button33Url = "http://a.app.qq.com/o/simple.jsp?pkgname=com.edriving";
		button33.put("url", button33Url);
		subButton3.add(button33);
		// 子菜单4
		JSONObject button34 = new JSONObject();
		button34.put("type", "click");
		String button34Name = "联系我们";
		button34.put("name", button34Name);
		button34.put("key", "V34_CONTACT_US");
		subButton3.add(button34);
		button3.put("sub_button", subButton3);
		//打包
		JSONArray button = new JSONArray();
		button.add(button1);
		button.add(button2);
		button.add(button3);
		// 生成菜单
		JSONObject menu = new JSONObject();
		menu.put("button", button);
		System.out.println(menu);
		return menu.toString();
	}
	/**
	 * 发送模板消息
	 * @param openid 微信用户的openid
	 * @param templateId 模板消息id
	 * @param url 点击消息后跳转的网页
	 * @param data 消息中的参数
	 * @return
	 */
	public static JSONObject sendTemplateMsg(String openid,String templateId,String url,Map data){
		String accessToken = getFromCache();
		if(accessToken == null){
			accessToken = getNew();
		}
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("touser", openid);
		param.put("template_id", templateId);
		param.put("url", url);
		param.put("data", data);
		JSONObject json = JSONObject.fromObject(param);
		String result = WxpayUtil.httpsRequest(requestUrl, "POST", json.toString());
		return JSONObject.fromObject(result);
	}
}
