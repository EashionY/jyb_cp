package cn.jyb.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.entity.Admin;
import cn.jyb.service.AdminService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/admin")
public class AdminController extends ExceptionController{

	@Resource
	private AdminService adminService;
	
	@RequestMapping("/login")
	@ResponseBody
	public JsonResult login(HttpServletResponse resp,String account,String password){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		Admin admin = adminService.login(account, password);
		return new JsonResult(admin);
	}
	
	@RequestMapping("/modifyPwd")
	@ResponseBody
	public JsonResult modifyPwd(HttpServletResponse resp,String account,String oldPassword,String newPassword){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		Admin admin = adminService.modifyPwd(account, oldPassword, newPassword);
		return new JsonResult(admin);
	}
	
	@RequestMapping("/listAdmins")
	@ResponseBody
	public JsonResult listAdmins(HttpServletResponse resp,Integer privil){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		List<Admin> result = adminService.listAdmins(privil);
		return new JsonResult(result);
	}
	
	@RequestMapping("/dealAdmin")
	@ResponseBody
	public JsonResult dealAdmin(HttpServletResponse resp,Integer adminId,boolean adminStatus){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		adminService.dealAdmin(adminId, adminStatus);
		return new JsonResult("");
	}
}
