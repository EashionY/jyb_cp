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
	 * �����ʻ֤.
	 *
	 * @param request ������userId,driverName,licensePic(֤����Ƭ),licenseNo(֤��),issueDate(��֤����),drivingClass(׼�ݳ���)
	 * @return the json result
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/saveDrivingLic")
	@ResponseBody
	public JsonResult saveDrivingLic(HttpServletRequest request) throws UnsupportedEncodingException{
		return new JsonResult(drivingLicenseService.saveDrivingLicense(request));
	}
	/**
	 * ��˼�ʻ֤(��̨����ϵͳ).
	 *
	 * @param id ��¼id
	 * @param userId �û�id
	 * @param status the status
	 * @param resp the resp ���ÿ�������
	 * @return the json result
	 */
	@RequestMapping("/dealDrivingLic")
	@ResponseBody
	public JsonResult dealDrivingLic(Integer id, Integer userId, Integer status, HttpServletResponse resp){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return new JsonResult(drivingLicenseService.dealDrivingLicense(id, userId, status));
	}
	/**
	 * �鿴���м�ʻ֤
	 *
	 * @param resp the resp
	 * @param status 0--�����,1--��֤�ɹ�,2--��֤ʧ��
	 * @return the json result
	 */
	@RequestMapping("/listAll")
	@ResponseBody
	public JsonResult listAll(HttpServletResponse resp, Integer status, Integer page, Integer pageSize){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return new JsonResult(drivingLicenseService.listAll(status, page, pageSize));
	}
	
	
}
