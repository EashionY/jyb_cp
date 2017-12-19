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
	 * �������������
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
	 * �������ܶ���
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
	 * �鿴������ԤԼ����
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
	 * ���۳���
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
	 * ���۳˿�
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
	 * ��ȡ��������ϸ��Ϣ
	 * @param receiptId
	 * @return
	 */
	@RequestMapping("/getDriverInfo")
	@ResponseBody
	public JsonResult getDriverInfo(Integer receiptId){
		return new JsonResult(hlbService.getDriverInfo(receiptId));
	}
	/**
	 * ��ȡ�۸�
	 * @param carType ����(0-С�γ���1-С�������2-���������3-С������4-�����)
	 * @param mileage ���
	 * @return
	 */
	@RequestMapping("/getPrice")
	@ResponseBody
	public JsonResult getPrice(String carType,String mileage){
		return new JsonResult(hlbService.getPrice(carType, mileage));
	}
	/**
	 * ��ȡ��������
	 * @param hlbOrderNo
	 * @return
	 */
	@RequestMapping("/getOrderInfo")
	@ResponseBody
	public JsonResult getOrderInfo(String hlbOrderNo){
		return new JsonResult(hlbService.getOrderInfo(hlbOrderNo));
	}
	/**
	 * ����ȷ�ϵ���˿͸���
	 * @param hlbOrderNo
	 * @return  
	 */
	@RequestMapping("/getClose")
	@ResponseBody
	public JsonResult getClose(String hlbOrderNo){
		return new JsonResult(hlbService.getClose(hlbOrderNo));
	}
	/**
	 * �˿�ȷ���ϳ�
	 * @param hlbOrderNo
	 * @return
	 */
	@RequestMapping("/aboard")
	@ResponseBody
	public JsonResult aboard(String hlbOrderNo){
		return new JsonResult(hlbService.aboard(hlbOrderNo));
	}
	/**
	 * �˿�ȷ�ϵ���
	 * @param hlbOrderNo
	 * @return
	 */
	@RequestMapping("/pArrive")
	@ResponseBody
	public JsonResult pArrive(String hlbOrderNo){
		return new JsonResult(hlbService.pArrive(hlbOrderNo));
	}
	/**
	 * ����ȷ�ϵ���
	 * @param hlbOrderNo
	 * @return
	 */
	@RequestMapping("/dArrive")
	@ResponseBody
	public JsonResult dArrive(String hlbOrderNo){
		return new JsonResult(hlbService.dArrive(hlbOrderNo));
	}
	
	
}
