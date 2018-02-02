package cn.jyb.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.DrivingLicenseService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/drivingLic")
public class DrivingLicenseController extends ExceptionController {

	@Resource
	private DrivingLicenseService drivingLicenseService;
	
	/**
	 * 保存驾驶证.
	 *
	 * @param request 参数：userId,driverName,licensePic(证件照片),licenseNo(证号),issueDate(领证日期),drivingClass(准驾车型)
	 * @return the json result
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/saveDrivingLic")
	@ResponseBody
	public JsonResult saveDrivingLic(HttpServletRequest request) throws UnsupportedEncodingException{
		return new JsonResult(drivingLicenseService.saveDrivingLicense(request));
	}
	/**
	 * 审核驾驶证(后台管理系统).
	 *
	 * @param id 记录id
	 * @param userId 用户id
	 * @param status the status
	 * @param resp the resp 设置跨域请求
	 * @return the json result
	 */
	@RequestMapping("/dealDrivingLic")
	@ResponseBody
	public JsonResult dealDrivingLic(Integer id, Integer userId, Integer status, HttpServletResponse resp){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return new JsonResult(drivingLicenseService.dealDrivingLicense(id, userId, status));
	}
	/**
	 * 查看所有驾驶证
	 *
	 * @param resp the resp
	 * @param status 0--审核中,1--认证成功,2--认证失败
	 * @return the json result
	 */
	@RequestMapping("/listAll")
	@ResponseBody
	public JsonResult listAll(HttpServletResponse resp, Integer status, Integer page, Integer pageSize){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return new JsonResult(drivingLicenseService.listAll(status, page, pageSize));
	}
	
	
}
