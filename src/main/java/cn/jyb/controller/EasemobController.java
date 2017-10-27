package cn.jyb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.util.EasemobUtil;
import cn.jyb.util.JsonResult;
import net.sf.json.JSONObject;

/**
 * ���ſ��Ʋ�
 * @author Eashion
 */
@Controller
@RequestMapping("/easemob")
public class EasemobController extends ExceptionController {

	/**
	 * ��ȡ���ŵ�token.
	 * @return the token
	 */
	@RequestMapping("/getToken")
	@ResponseBody
	public JsonResult getToken(){
		String token = EasemobUtil.getFromCache();
		if(token == null){
			token = EasemobUtil.getNew();
		}
		return new JsonResult(token);
	}
	
	/**
	 * �鿴�û��ĺ�����Ϣ.
	 * @param username the username
	 * @return the contacts users
	 */
	@RequestMapping("/contacts/users")
	@ResponseBody
	public JsonResult getContactsUsers(String username){
		JSONObject result = EasemobUtil.getContactsUsers(username);
		return new JsonResult(result);
	}
	
	/**
	 * ע�ỷ���û�.
	 * @param username the username
	 * @param password the password
	 * @return the json result
	 */
	@RequestMapping("/registUsers")
	@ResponseBody
	public JsonResult registUsers(String username,String password){
		return new JsonResult(EasemobUtil.registUsers(username, password));
	}
	
	/**
	 * �鿴�����û���Ϣ.
	 * @param username the username
	 * @return the user info
	 */
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public JsonResult getUserInfo(String username){
		return new JsonResult(EasemobUtil.getUserInfo(username));
	}
	
	/**
	 * �����û���Ӻ���.
	 * @param username the username
	 * @param friendUsername the friend username
	 * @return the json result
	 */
	@RequestMapping("/addFriend")
	@ResponseBody
	public JsonResult addFriend(String username,String friendUsername){
		return new JsonResult(EasemobUtil.addFriend(username, friendUsername));
	}
	
	/**
	 * ��������û��ĺ��ѹ�ϵ.
	 * @param username the username
	 * @param friendUsername the friend username
	 * @return the json result
	 */
	@RequestMapping("/deleteFriend")
	@ResponseBody
	public JsonResult deleteFriend(String username,String friendUsername){
		return new JsonResult(EasemobUtil.deleteFriend(username, friendUsername));
	}
	
	/**
	 * �޸Ļ����û����ǳ�.
	 * @param username the username
	 * @param nickname the nickname
	 * @return the json result
	 */
	@RequestMapping("/updateNickname")
	@ResponseBody
	public JsonResult updateNickname(String username,String nickname){
		return new JsonResult(EasemobUtil.updateNickname(username, nickname));
	}
	
	
}
