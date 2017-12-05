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
	 * ������ʻ֤��Ϣ.
	 *
	 * @param request ������userId,vehicleLicPic(��ʻ֤��Ƭ),vehicleNo(���ƺ�),vehicleOwner(����������),vehicleBrand(Ʒ��),vehicleVin(����ʶ���),engineNo(��������)
	 * @return the json result
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	@RequestMapping("/saveVehicleLic")
	@ResponseBody
	public JsonResult saveVehicleLic(HttpServletRequest request) throws UnsupportedEncodingException{
		return new JsonResult(vehicleLicenseService.saveVehicleLicense(request));
	}
	
	/**
	 * �����ʻ֤(��̨����ϵͳ).
	 *
	 * @param id the id
	 * @param status the status
	 * @param resp the resp ���ÿ�������
	 * @return the json result
	 */
	@RequestMapping("/dealVehicleLic")
	@ResponseBody
	public JsonResult dealVehicleLic(Integer id, Integer status, HttpServletResponse resp){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return new JsonResult(vehicleLicenseService.dealVehicleLicense(id, status));
	}
	
	/**
	 * �鿴������ʻ֤
	 *
	 * @param resp the resp
	 * @param status 0--�����,1--��֤�ɹ�,2--��֤ʧ��
	 * @return the json result
	 */
	@RequestMapping("/listAll")
	@ResponseBody
	public JsonResult listAll(HttpServletResponse resp, Integer status, Integer page, Integer pageSize){
		resp.setHeader("Access-Control-Allow-Origin", "*");
		return new JsonResult(vehicleLicenseService.listAll(status, page, pageSize));
	}
}
