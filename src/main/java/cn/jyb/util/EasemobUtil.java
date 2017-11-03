package cn.jyb.util;

import org.springframework.scheduling.annotation.Scheduled;

import net.sf.json.JSONObject;

/**
 * ���ŵ�token����
 * @author Eashion
 *
 */
public class EasemobUtil {

	//Ӧ�ñ�ʶ
	private static final String ORGNAME = "1161170508178256";
	//Ӧ����
	private static final String APPNAME = "jybchat";
	
	private static String ACCESS_TOKEN = "YWMtZkPo3reYEeeJHMdKvRiy2QAAAAAAAAAAAAAAAAAAAAEhtHKQM8QR57EocQ4J_hPeAgMAAAFfRwXOGABPGgBWOo8WA76Kdxb8giaLodpYDG1Sr_1h6V6Rf_mrfIvZMQ";
	
	/**
	 * ��ȡaccess_token��һ���ȡһ��
	 * 
	 */
	@Scheduled(fixedRate = 24*3600000)
	public void getAccessToken(){
		// ����΢�ŷ�����
		String url = "http://a1.easemob.com/"+ORGNAME+"/"+APPNAME+"/token";
		try {
			String data = "{\"grant_type\":\"client_credentials\"," 
					    + "\"client_id\":\"YXA6IbRykDPEEeexKHEOCf4T3g\"," 
					    + "\"client_secret\":\"YXA6MwUo49a-WHmUcxrhamuVnESlm8c\"}";
			
			JSONObject json = EasemobHttp.post(url, data);
			ACCESS_TOKEN = json.getString("access_token");
			System.out.println(ACCESS_TOKEN);
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
		EasemobUtil util = new EasemobUtil();
		util.getAccessToken();
		return ACCESS_TOKEN;
	}
    
    /**
     * �鿴�����û��ĺ�����Ϣ
     * @param username
     * @return
     */
    public static JSONObject getContactsUsers(String username){
    	String url = "http://a1.easemob.com/"+ORGNAME+"/"+APPNAME+"/users/"+username+"/contacts/users";
    	JSONObject result = EasemobHttp.get(url);
    	return result;
    }
    
    /**
     * ע�ỷ���û�
     * @param username
     * @param password
     * @return
     */
    public static JSONObject registUsers(String username,String password){
    	String url = "http://a1.easemob.com/"+ORGNAME+"/"+APPNAME+"/users";
    	String data = "{\"username\":"+"\""+username+"\","
    			    + "\"password\":"+"\""+password+"\"}";
    	return EasemobHttp.authPost(url, data);
    }
    
    /**
     * ��ȡ�����û�����Ϣ
     * @param username
     * @return
     */
    public static JSONObject getUserInfo(String username){
    	String url = "http://a1.easemob.com/"+ORGNAME+"/"+APPNAME+"/users/"+username;
    	return EasemobHttp.get(url);
    }
    
    /**
     * �����û���Ӻ���
     * @param username
     * @param friendUsername ������˵��û���
     * @return
     */
    public static JSONObject addFriend(String username,String friendUsername){
    	String url = "http://a1.easemob.com/"+ORGNAME+"/"+APPNAME+"/users/"+username+"/contacts/users/"+friendUsername;
    	String data = null;
    	return EasemobHttp.authPost(url, data);
    }
 
    /**
     * ��������û��ĺ��ѹ�ϵ
     * @param username 
     * @param friendUsername ��ɾ���˵��û���
     * @return JSONObject
     */
    public static JSONObject deleteFriend(String username,String friendUsername){
    	String url = "http://a1.easemob.com/"+ORGNAME+"/"+APPNAME+"/users/"+username+"/contacts/users/"+friendUsername;
    	return EasemobHttp.delete(url);
    }
    
    /**
     * �޸Ļ����û����ǳ�
     * @param username
     * @param nickname
     * @return JSONObject
     */
    public static JSONObject updateNickname(String username,String nickname){
    	String url = "http://a1.easemob.com/"+ORGNAME+"/"+APPNAME+"/users/"+username;
    	String data = "{\"nickname\":\""+nickname+"\"}";
    	return EasemobHttp.put(url, data);
    }
    
    public static JSONObject resetPassword(String username,String newPassword){
    	String url = "http://a1.easemob.com/"+ORGNAME+"/"+APPNAME+"/users/"+username+"/password";
    	String data = "{\"newpassword\":\""+newPassword+"\"}";
    	return EasemobHttp.put(url, data);
    }
}
