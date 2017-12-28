package cn.jyb.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.HLBChargeService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/hlb/charge")
public class HLBChargeController extends ExceptionController {

	@Resource
	private HLBChargeService hlbChargeService;
	
	/**
	 * ����һ���շѱ�׼(��̨����ϵͳ)
	 * @param request
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult saveCharge(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		return new JsonResult(hlbChargeService.saveCharge(request));
	}
	/**
	 * �޸�һ���շѱ�׼(��̨����ϵͳ)
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateCharge(HttpServletRequest request, HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return new JsonResult(hlbChargeService.updateCharge(request));
	}
	/**
	 * �޸ĳ���ͼ��(��̨����ϵͳ)
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/updateCarIcon", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateCarIcon(HttpServletRequest request, HttpServletResponse response) throws IOException{
		response.setHeader("Access-Control-Allow-Origin", "*");
		return new JsonResult(hlbChargeService.updateCarIcon(request));
	}
	/**
	 * �г��������õ��շѱ�׼
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JsonResult listCharge(){
		return new JsonResult(hlbChargeService.listCharge());
	}
}
