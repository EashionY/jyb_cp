package cn.jyb.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.entity.User;
import cn.jyb.service.UserService;
import cn.jyb.util.JsonResult;
import cn.jyb.util.Upload;

/**
 * The Class UserController.
 */
@Controller
@RequestMapping("/account")
public class UserController extends ExceptionController{

	//用于生成推荐码的加密数字
	private final static Integer ENCRYPTCODE = 10323;
	
	@Resource
	private UserService userService;
	
	/**
	 * 用户登录.
	 * @param phone the phone
	 * @param password the password
	 * @return the json result
	 */
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(HttpServletRequest request,String phone,String password,String openid){
		logger.error("openid="+openid);
		Map<String, Object> result = userService.login(request, phone, password, openid);
		return new JsonResult(result);
	}
	
	/**
	 * 注册.
	 * @param phone the phone
	 * @param password the password
	 * @param role the role
	 * @param verCode the ver code
	 * @return the json result
	 */
	@RequestMapping("/regist")
	@ResponseBody
	public JsonResult regist(String phone,String password,String role,String verCode){
//		System.out.println(phone+","+password+","+verCode);
		boolean tf = userService.regist(phone, password, role, verCode);
		return new JsonResult(tf);
	}
	
	/**
	 * 重置密码.
	 * @param phone the phone
	 * @param newPassword the new password
	 * @param verCode the ver code
	 * @return the json result
	 */
	@RequestMapping("/resetPwd")
	@ResponseBody
	public JsonResult resetPwd(String phone,String newPassword,String verCode){
		System.out.println(phone+","+newPassword+","+verCode);
		boolean tf = userService.resetPassword(phone, newPassword, verCode);
		return new JsonResult(tf);
	}
	
	/**
	 * 修改用户信息.
	 * @param phone the phone
	 * @param nickname the nickname
	 * @param sex the sex
	 * @param address the address
	 * @param birthday the birthday
	 * @param signature the signature
	 * @param xingzuo the xingzuo
	 * @param height the height
	 * @param weight the weight
	 * @param job the job
	 * @param salary the salary
	 * @param interest the interest
	 * @param region the region
	 * @param req the req
	 * @return the json result
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	@RequestMapping("/modifyUserinfo")
	@ResponseBody
	public JsonResult modifyUserinfo(String phone, String nickname, String sex, String address,
			String birthday, String signature, String xingzuo, String height, String weight,
			String job, String salary, String interest, String region,HttpServletRequest req) throws UnsupportedEncodingException{
		User user = userService.modifyUserinfo(phone, nickname, sex, address, birthday, 
				signature, xingzuo, height, weight, job, salary, interest, region, req);
		return new JsonResult(user);
	}

	/**
	 * Upload head img.
	 * @param phone the phone
	 * @param request the request
	 * @return the json result
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@RequestMapping("/uploadHeadImg")
	@ResponseBody
    public JsonResult uploadHeadImg(@RequestParam("phone")String phone,HttpServletRequest request) throws IOException{ 
		//图片保存的文件夹
		String folder = "headimg";
		List<String> paths = Upload.uploadImg(request, phone, folder);
        boolean tf = userService.updateHeadImg(paths.get(0),phone);
        if(!tf){
        	return new JsonResult(0,"","头像路径存储失败");
        }
		return new JsonResult(paths.get(0));
	}
	
	/**
	 * 获得推荐码.
	 * @param user_id the user id
	 * @return the recomd code
	 */
	@RequestMapping("/getRecomdCode")
	@ResponseBody
	public JsonResult getRecomdCode(Integer user_id){
		Integer recomdCode = user_id + ENCRYPTCODE;
		return new JsonResult(recomdCode);
	}
	
	/**
	 * 用户切换角色端口.
	 * @param user_id the user id
	 * @param role the role
	 * @return the json result
	 */
	@RequestMapping("/changeRole")
	@ResponseBody
	public JsonResult changeRole(Integer user_id, String role){
		return new JsonResult(userService.changeRole(user_id, role));
	}
	
	/**
	 * 用户修改密码.
	 * @param phone the phone
	 * @param oldPwd the old pwd
	 * @param newPwd the new pwd
	 * @return the json result
	 */
	@RequestMapping("/modifyPwd")
	@ResponseBody
	public JsonResult modifyPwd(String phone, String oldPwd, String newPwd){
		return new JsonResult(userService.modifyPassword(phone, oldPwd, newPwd));
	}
	
	/**
	 * 用户变更登录手机号.
	 * @param user_id the user id
	 * @param newPhone the new phone
	 * @param phoneCode the phone code
	 * @return the json result
	 */
	@RequestMapping("/changePhone")
	@ResponseBody
	public JsonResult changePhone(Integer user_id, String newPhone, String phoneCode){
		return new JsonResult(userService.changePhone(user_id, newPhone, phoneCode));
	}
	
	/**
	 * 集成用户到环信
	 * @return
	 */
	@RequestMapping("/regist2Easemob")
	@ResponseBody
	public JsonResult regist2Easemob(){
		return new JsonResult(userService.regist2Easemob());
	}
	/**
	 * 查看用户的三证认证状态
	 * @param userId
	 * @return
	 */
	@RequestMapping("/checkCertStatus")
	@ResponseBody
	public JsonResult checkCertStatus(Integer userId){
		return new JsonResult(userService.checkCertStatus(userId));
	}
	/**
	 * 从session中获取userId
	 * @param request
	 * @return
	 */
	@RequestMapping("/getUserId")
	@ResponseBody
	public JsonResult getUserId(HttpServletRequest request){
		return new JsonResult(userService.getUserId(request));
	}
}
