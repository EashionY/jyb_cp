package cn.jyb.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.jyb.service.HLBService;
import cn.jyb.util.JsonResult;

@Controller
@RequestMapping("/hlb")
public class HLBController extends ExceptionController {

	@Resource
	private HLBService hlbService;
	/**
	 * 保存货拉宝订单
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws ParseException 
	 */
	@RequestMapping("/saveOrder")
	@ResponseBody
	public JsonResult saveOrder(HttpServletRequest request) throws UnsupportedEncodingException, ParseException{
		return new JsonResult(hlbService.saveHLBOrder(request));
	}
	/**
	 * 车主接受订单
	 * @param hlbOrderNo
	 * @param receiptId
	 * @return
	 */
	@RequestMapping("/acceptOrder")
	@ResponseBody
	public JsonResult acceptOrder(String hlbOrderNo, Integer receiptId){
		return new JsonResult(hlbService.acceptHLBOrder(hlbOrderNo, receiptId));
	}
	/**
	 * 查看车主的预约订单
	 * @param receiptId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/listBookOrders")
	@ResponseBody
	public JsonResult listBookOrders(Integer receiptId, Integer page, Integer pageSize){
		return new JsonResult(hlbService.listBookOrders(receiptId, page, pageSize));
	}
	/**
	 * 评价车主
	 * @param hlbOrderNo
	 * @param evalStar
	 * @return
	 */
	@RequestMapping("/evalDriver")
	@ResponseBody
	public JsonResult evalDriver(String hlbOrderNo, Integer evalStar){
		return new JsonResult(hlbService.evalDriver(hlbOrderNo, evalStar));
	}
	/**
	 * 评价乘客
	 * @param hlbOrderNo
	 * @param evalStar
	 * @return
	 */
	@RequestMapping("/evalPassenger")
	@ResponseBody
	public JsonResult evalPassenger(String hlbOrderNo, Integer evalStar){
		return new JsonResult(hlbService.evalPassenger(hlbOrderNo, evalStar));
	}
	/**
	 * 获取车主的详细信息
	 * @param receiptId
	 * @return
	 */
	@RequestMapping("/getDriverInfo")
	@ResponseBody
	public JsonResult getDriverInfo(Integer receiptId){
		return new JsonResult(hlbService.getDriverInfo(receiptId));
	}
	
}
