package cn.jyb.controller;

import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.ShippingAddrService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/addr")
public class ShippingAddrController extends ExceptionController {

	@Resource
	private ShippingAddrService addrService;
	
	/**
	 * �����ջ���ַ.
	 *
	 * @param request the request
	 * @return the json result
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	@RequestMapping(value = "/saveAddr",method = RequestMethod.POST)
	@ResponseBody
	public JsonResult saveAddr(HttpServletRequest request) throws UnsupportedEncodingException{
		return new JsonResult(addrService.saveAddr(request));
	}
	
	/**
	 * �鿴���û������ջ���ַ.
	 *
	 * @param userId the user id
	 * @return the json result
	 */
	@RequestMapping(value = "/listAddr", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult listAddr(Integer userId){
		return new JsonResult(addrService.listAddr(userId));
	}
	
	/**
	 * ɾ���ջ���ַ.
	 *
	 * @param addrId the addr id
	 * @return the json result
	 */
	@RequestMapping(value = "/deleteAddr", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult deleteAddr(Integer addrId){
		return new JsonResult(addrService.deleteAddr(addrId));
	}
	
	/**
	 * �޸��ջ���ַ.
	 *
	 * @param request the request
	 * @return the json result
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	@RequestMapping(value = "/updateAddr", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult updateAddr(HttpServletRequest request) throws UnsupportedEncodingException{
		return new JsonResult(addrService.updateAddr(request));
	}
	
	
}
