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

import cn.jyb.service.UserService;
import cn.jyb.util.JsonResult;
import cn.jyb.util.Upload;

@Controller
@RequestMapping("/account")
public class UserController extends ExceptionController{

	//用于生成推荐码的加密数字
	private final static Integer ENCRYPTCODE = 10323;
	
	@Resource
	private UserService userService;
	
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(String phone,String password){
		Map<String, Object> result = userService.login(phone, password);
		return new JsonResult(result);
	}
	
	@RequestMapping("/regist")
	@ResponseBody
	public JsonResult regist(String phone,String password,String role,String verCode){
		System.out.println(phone+","+password+","+verCode);
		boolean tf = userService.regist(phone, password, role, verCode);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/resetPwd")
	@ResponseBody
	public JsonResult resetPwd(String phone,String newPassword,String verCode){
		System.out.println(phone+","+newPassword+","+verCode);
		boolean tf = userService.resetPassword(phone, newPassword, verCode);
		return new JsonResult(tf);
	}
	
	@RequestMapping("/modifyUserinfo")
	@ResponseBody
	public JsonResult modifyUserinfo(String phone, String nickname, String sex, String address,
			String birthday, String signature, String xingzuo, String height, String weight,
			String job, String salary, String interest, String region,HttpServletRequest req) throws UnsupportedEncodingException{
		boolean tf = userService.modifyUserinfo(phone, nickname, sex, address, birthday, 
				signature, xingzuo, height, weight, job, salary, interest, region, req);
		return new JsonResult(tf);
	}

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
	 * 获得推荐码
	 * @param user_id
	 * @return
	 */
	@RequestMapping("/getRecomdCode")
	@ResponseBody
	public JsonResult getRecomdCode(Integer user_id){
		Integer recomdCode = user_id + ENCRYPTCODE;
		return new JsonResult(recomdCode);
	}
}
