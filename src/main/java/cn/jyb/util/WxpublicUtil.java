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
 * ΢�Ź��ںŹ�����
 * @author Eashion
 *
 */
@Component
public class WxpublicUtil {

	public static String ACCESS_TOKEN = null;
	
	public static String JSAPI_TICKET = null;
	
	//��־�ļ�
	protected static Logger logger = LoggerFactory.getLogger(WxpublicUtil.class);
	
	/**
	 * ��ȡaccess_token��ÿ��3600s��ȡһ��
	 */
	@Scheduled(fixedRate = 3600000)
	public static void getAccessToken(){
		// ����΢�ŷ�����
		String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" 
		           + WxpublicConfig.APPID + "&secret="+ WxpublicConfig.APPSECRET;
		try {
			String result = HttpUtil.sendGet(url, "UTF-8");
//			System.out.println(result);
			JSONObject json = JSONObject.fromObject(result);
			ACCESS_TOKEN = json.getString("access_token");
			logger.error("��ʱACCESS_TOKEN:"+ACCESS_TOKEN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ӻ����л�ȡtoken
	 * @return
	 */
	public static String getFromCache(){
		return ACCESS_TOKEN;
	}
	/**
	 * ǿ�ƻ�ȡtoken
	 * @return
	 */
	public static synchronized String getNew(){
		WxpublicUtil.getAccessToken();
		return ACCESS_TOKEN;
	}
	/**
	 * ��ȡjsapi_ticket��ÿ��3600s��ȡһ��
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
			logger.error("��ʱJSAPI_TICKET:"+JSAPI_TICKET);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * �ӻ����л�ȡticket
	 * @return
	 */
	public static String getTicketFromCache(){
		return JSAPI_TICKET;
	}
	/**
	 * ǿ�Ƹ���ticket
	 * @return
	 */
	public static synchronized String getTicketNew(){
		WxpublicUtil.getJsapiTicket();
		return JSAPI_TICKET;
	}
	/**
	 * �ֵ������򷽷�
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
	 * ��ϣ�㷨����
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
            // �ֽ�����ת��Ϊ ʮ������ ��
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
	 * ����΢�Ź��ںŵĲ˵�
	 * @return
	 */
	public static String getMenu(){
		// �˵�һ
		JSONObject button1 = new JSONObject();
		button1.put("type", "view");
		String button1Name = "��ҳ";
		button1.put("name", button1Name);
		String button1Url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="
				+ WxpublicConfig.APPID
				+ "&redirect_uri="
				+ WxpayUtil.urlEncodeUTF8("http://api.drivingyeepay.com/jyb/wxpublic/check")
				+ "&response_type=code&scope=snsapi_base&state="
				+ WxpayUtil.urlEncodeUTF8(button1Name)
				+ "#wechat_redirect";
		button1.put("url", button1Url);
		// �˵���
		JSONObject button2 = new JSONObject();
		button2.put("name", "һ������");
		// �˵������Ӳ˵�
		JSONArray subButton2 = new JSONArray();
		// �Ӳ˵�1
		JSONObject button21 = new JSONObject();
		button21.put("type", "view");
		String button21Name = "������";
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
		// �Ӳ˵�2
		JSONObject button22 = new JSONObject();
		button22.put("type", "view");
		String button22Name = "һ��Ų��";
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
		// �Ӳ˵�3
//		JSONObject button23 = new JSONObject();
//		button23.put("type", "view");
//		String button23Name = "Υ�²�ѯ&����";
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
		// �Ӳ˵�4
		JSONObject button24 = new JSONObject();
		button24.put("type", "view");
		String button24Name = "ģ����ϰ";
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
		// �˵���
		JSONObject button3 = new JSONObject();
		button3.put("name", "�û�����");
		// �˵������Ӳ˵�
		JSONArray subButton3 = new JSONArray();
		// �Ӳ˵�1
//		JSONObject button31 = new JSONObject();
//		button31.put("type", "view");
//		String button31Name = "������ѯ";
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
		// �Ӳ˵�2
		JSONObject button32 = new JSONObject();
		button32.put("type", "view");
		String button32Name = "������Ϣ";
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
		// �Ӳ˵�3
		JSONObject button33 = new JSONObject();
		button33.put("type", "view");
		String button33Name = "APP����";
		button33.put("name", button33Name);
		//APP���ص�ַ
		String button33Url = "http://a.app.qq.com/o/simple.jsp?pkgname=com.edriving";
		button33.put("url", button33Url);
		subButton3.add(button33);
		// �Ӳ˵�4
		JSONObject button34 = new JSONObject();
		button34.put("type", "click");
		String button34Name = "��ϵ����";
		button34.put("name", button34Name);
		button34.put("key", "V34_CONTACT_US");
		subButton3.add(button34);
		button3.put("sub_button", subButton3);
		//���
		JSONArray button = new JSONArray();
		button.add(button1);
		button.add(button2);
		button.add(button3);
		// ���ɲ˵�
		JSONObject menu = new JSONObject();
		menu.put("button", button);
		System.out.println(menu);
		return menu.toString();
	}
	/**
	 * ����ģ����Ϣ
	 * @param openid ΢���û���openid
	 * @param templateId ģ����Ϣid
	 * @param url �����Ϣ����ת����ҳ
	 * @param data ��Ϣ�еĲ���
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
