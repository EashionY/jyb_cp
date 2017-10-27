package cn.jyb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.util.EasemobUtil;
import cn.jyb.util.JsonResult;
import net.sf.json.JSONObject;

/**
 * 环信控制层
 * @author Eashion
 */
@Controller
@RequestMapping("/easemob")
public class EasemobController extends ExceptionController {

	/**
	 * 获取环信的token.
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
	 * 查看用户的好友信息.
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
	 * 注册环信用户.
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
	 * 查看环信用户信息.
	 * @param username the username
	 * @return the user info
	 */
	@RequestMapping("/getUserInfo")
	@ResponseBody
	public JsonResult getUserInfo(String username){
		return new JsonResult(EasemobUtil.getUserInfo(username));
	}
	
	/**
	 * 环信用户添加好友.
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
	 * 解除环信用户的好友关系.
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
	 * 修改环信用户的昵称.
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
