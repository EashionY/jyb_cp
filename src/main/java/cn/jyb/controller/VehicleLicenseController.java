package cn.jyb.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.VehicleLicenseService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/vehicleLic")
public class VehicleLicenseController extends ExceptionController {

	@Resource
	private VehicleLicenseService vehicleLicenseService;
	
	/**
	 * 保存行驶证信息.
	 *
	 * @param request 参数：userId,vehicleLicPic(行驶证照片),vehicleNo(车牌号),vehicleOwner(车辆所有人),vehicleBrand(品牌),vehicleVin(车辆识别号),engineNo(发动机号)
	 * @return the json result
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	@RequestMapping("/saveVehicleLic")
	@ResponseBody
	public JsonResult saveVehicleLic(HttpServletRequest request) throws UnsupportedEncodingException{
		return new JsonResult(vehicleLicenseService.saveVehicleLicense(request));
	}
	
	/**
	 * 审核行驶证(后台管理系统).
	 *
	 * @param id the id
	 * @param status the status
	 * @param resp the resp 设置跨域请求
	 * @return the json result
	 */
	@RequestMapping("/dealVehicleLic")
	@ResponseBody
	public JsonResult dealVehicleLic(Integer id, Integer status, HttpServletResponse resp){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return new JsonResult(vehicleLicenseService.dealVehicleLicense(id, status));
	}
	
	/**
	 * 查看所有行驶证
	 *
	 * @param resp the resp
	 * @param status 0--审核中,1--认证成功,2--认证失败
	 * @return the json result
	 */
	@RequestMapping("/listAll")
	@ResponseBody
	public JsonResult listAll(HttpServletResponse resp, Integer status, Integer page, Integer pageSize){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return new JsonResult(vehicleLicenseService.listAll(status, page, pageSize));
	}
}
