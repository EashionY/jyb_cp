package cn.jyb.controller;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.jyb.service.HLBService;
import cn.jyb.util.HttpUtil;
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
	 * ȡ������
	 * @param hlbOrderNo
	 * @param userId
	 * @return
	 */
	@RequestMapping("/cancelOrder")
	@ResponseBody
	public JsonResult cancelOrder(String hlbOrderNo, Integer userId){
		return new JsonResult(hlbService.cancelOrder(hlbOrderNo, userId));
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
	 * �۸���ϸ
	 * @param carType ����(0-С�γ���1-С�������2-���������3-С������4-�����)
	 * @param mileage ���
	 * @return
	 */
	@RequestMapping("/priceDetail")
	@ResponseBody
	public JsonResult priceDetail(String carType,String mileage){
		return new JsonResult(hlbService.priceDetail(carType, mileage));
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
	/**
	 * ��ȡ�˿���Ϣ
	 * @param publishId
	 * @return
	 */
	@RequestMapping("/getPassengerInfo")
	@ResponseBody
	public JsonResult getPassengerInfo(Integer publishId){
		return new JsonResult(hlbService.getPassengerInfo(publishId));
	}
	/**
	 * �鿴���ж���(δ�ӵ�״̬��)(��������)
	 * @param carType
	 * @param orderType
	 * @param lon
	 * @param lat
	 * @param price
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/listOrders")
	@ResponseBody
	public JsonResult listOrders(String carType, Integer orderType, String lon, String lat, String price, Integer page, Integer pageSize){
		return new JsonResult(hlbService.listOrders(carType, orderType, Double.parseDouble(lon), Double.parseDouble(lat), price, page, pageSize));
	}
	/**
	 * �鿴�����ı����붩��
	 * @param invited
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/getInvites")
	@ResponseBody
	public JsonResult getInvites(Integer invited, Integer page, Integer pageSize){
		return new JsonResult(hlbService.getInvites(invited, page, pageSize));
	}
	/**
	 * ���복���ӵ�
	 * @param hlbOrderNo
	 * @param invited
	 * @return
	 */
	@RequestMapping("/orderInvite")
	@ResponseBody
	public JsonResult orderInvite(String hlbOrderNo, Integer invited){
		return new JsonResult(hlbService.orderInvite(hlbOrderNo, invited));
	}
	/**
	 * ���������յ�ľ�γ�Ȼ�ȡ����
	 * @param origin
	 * @param destination
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getDist")
	@ResponseBody
	public String getDist(String origin,String destination) throws Exception{
		String url = "http://api.map.baidu.com/direction/v1?mode=driving&origin="+origin+"&destination="+destination+"&origin_region=�ɶ�&destination_region=�ɶ�&output=json&ak=uTGwpgzvEGickjdjWeIIIIuKhgp7UmbX";
		String result = HttpUtil.sendGet(url, "UTF-8");
		JSONObject retJson = JSONObject.parseObject(result).getJSONObject("result");
		System.out.println("1:"+retJson.toString());
		JSONArray retArray = retJson.getJSONArray("routes");
		System.out.println("2:"+retArray.toString());
		JSONObject obj = (JSONObject) retArray.get(0);
		System.out.println("3:"+obj.toString());
		System.out.println("4:"+obj.getString("distance"));
		return result;
	}
	/**
	 * ��˿��Ƽ�����
	 * @param userLon ����
	 * @param userLat γ��
	 * @param region ����
	 * @return
	 */
	@RequestMapping("/recomendDriver")
	@ResponseBody
	public JsonResult recomendDriver(String userLon, String userLat, String region){
		return new JsonResult(hlbService.recomendDriver(userLon, userLat, region));
	}
	/**
	 * �������������վ���鿴����
	 * @param carType
	 * @param orderType
	 * @param lon
	 * @param lat
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/listOrdersByDistance")
	@ResponseBody
	public JsonResult listOrdersByDistance(String carType, Integer orderType, String lon,
			String lat, Integer page, Integer pageSize){
		return new JsonResult(hlbService.listOrdersByDistance(carType, orderType, Double.parseDouble(lon), Double.parseDouble(lat), page, pageSize));
	}
	
	
}
