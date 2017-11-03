package cn.jyb.util;

import org.springframework.scheduling.annotation.Scheduled;

import net.sf.json.JSONObject;

/**
 * 环信的token管理
 * @author Eashion
 *
 */
public class EasemobUtil {

	//应用标识
	private static final String ORGNAME = "1161170508178256";
	//应用名
	private static final String APPNAME = "jybchat";
	
	private static String ACCESS_TOKEN = "YWMtZkPo3reYEeeJHMdKvRiy2QAAAAAAAAAAAAAAAAAAAAEhtHKQM8QR57EocQ4J_hPeAgMAAAFfRwXOGABPGgBWOo8WA76Kdxb8giaLodpYDG1Sr_1h6V6Rf_mrfIvZMQ";
	
	/**
	 * 获取access_token，一天获取一次
	 * 
	 */
	@Scheduled(fixedRate = 24*3600000)
	public void getAccessToken(){
		// 访问微信服务器
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
		EasemobUtil util = new EasemobUtil();
		util.getAccessToken();
		return ACCESS_TOKEN;
	}
    
    /**
     * 查看单个用户的好友信息
     * @param username
     * @return
     */
    public static JSONObject getContactsUsers(String username){
    	String url = "http://a1.easemob.com/"+ORGNAME+"/"+APPNAME+"/users/"+username+"/contacts/users";
    	JSONObject result = EasemobHttp.get(url);
    	return result;
    }
    
    /**
     * 注册环信用户
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
     * 获取单个用户的信息
     * @param username
     * @return
     */
    public static JSONObject getUserInfo(String username){
    	String url = "http://a1.easemob.com/"+ORGNAME+"/"+APPNAME+"/users/"+username;
    	return EasemobHttp.get(url);
    }
    
    /**
     * 向环信用户添加好友
     * @param username
     * @param friendUsername 被添加人的用户名
     * @return
     */
    public static JSONObject addFriend(String username,String friendUsername){
    	String url = "http://a1.easemob.com/"+ORGNAME+"/"+APPNAME+"/users/"+username+"/contacts/users/"+friendUsername;
    	String data = null;
    	return EasemobHttp.authPost(url, data);
    }
 
    /**
     * 解除环信用户的好友关系
     * @param username 
     * @param friendUsername 被删除人的用户名
     * @return JSONObject
     */
    public static JSONObject deleteFriend(String username,String friendUsername){
    	String url = "http://a1.easemob.com/"+ORGNAME+"/"+APPNAME+"/users/"+username+"/contacts/users/"+friendUsername;
    	return EasemobHttp.delete(url);
    }
    
    /**
     * 修改环信用户的昵称
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
