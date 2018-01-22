package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.HLBOrder;

public interface HLBService {

	/**
	 * 存储订单状态变化的map，订单状态变化时为true
	 */
	public static Map<String,Boolean> changed = new HashMap<String, Boolean>();
	
	/**
	 * 保存货拉宝订单
	 * @param request
	 * @return 订单详情
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	public HLBOrder saveHLBOrder(HttpServletRequest request) throws UnsupportedEncodingException, ParseException;
	/**
	 * 接受订单
	 * @param hlbOrderNo 货拉宝订单号
	 * @param receiptId 车主的用户id
	 * @return
	 */
	public HLBOrder acceptHLBOrder(String hlbOrderNo, Integer receiptId);
	/**
	 * 查看车主的预约列表
	 * @param receiptId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<HLBOrder> listBookOrders(Integer receiptId, Integer page, Integer pageSize);
	/**
	 * 评价车主
	 * @param hlbOrderNo
	 * @param evalStar
	 * @return
	 */
	public boolean evalDriver(String hlbOrderNo, Integer evalStar);
	/**
	 * 评价乘客
	 * @param hlbOrderNo
	 * @param evalStar
	 * @return
	 */  
	public boolean evalPassenger(String hlbOrderNo, Integer evalStar);
	/**
	 * 取消订单
	 * @param hlbOrderNo
	 * @param userId 取消订单的用户id
	 * @return
	 */
	public boolean cancelOrder(String hlbOrderNo,Integer userId);
	/**
	 * 查看车主的详细信息
	 * @param receiptId 车主的用户id
	 * @return
	 */
	public Map<String,Object> getDriverInfo(Integer receiptId);
	/**
	 * 获取价格
	 * @param carType 车型
	 * @param mileage 里程
	 * @return
	 */
	public String getPrice(String carType,String mileage);
	/**
	 * 查看价格明细
	 * @param carType
	 * @param mileage
	 * @return
	 */
	public Map<String,Object> priceDetail(String carType,String mileage);
	/**
	 * 获取订单详情
	 * @param hlbOrderNo
	 * @return
	 */
	public HLBOrder getOrderInfo(String hlbOrderNo);
	/**
	 * 车主确认到达乘客附近
	 * @param hlbOrderNo
	 * @return
	 */
	public boolean getClose(String hlbOrderNo);
	/**
	 * 乘客确认上车  
	 * @param hlbOrderNo
	 * @return
	 */
	public boolean aboard(String hlbOrderNo);
	/**
	 * 开始行程
	 * @param hlbOrderNo
	 * @return
	 */
	public boolean tripStart(String hlbOrderNo);
	/**
	 * 乘客确认到达
	 * @param hlbOrderNo
	 * @return
	 */
	public boolean pArrive(String hlbOrderNo);
	/**
	 * 车主确认到达
	 * @param hlbOrderNo
	 * @return
	 */
	public boolean dArrive(String hlbOrderNo);
	/**
	 * 获取乘客信息
	 * @param publishId
	 * @return
	 */
	public Map<String,Object> getPassengerInfo(Integer publishId);
	/**
	 * 查看所有订单（抢单大厅）
	 * @param carType
	 * @param orderType
	 * @param lon
	 * @param lat
	 * @param price 是否按价格排序（1/0）
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String,Object> listOrders(String carType,Integer orderType,double lon,double lat,String price,Integer page,Integer pageSize);
	/**
	 * 查看车主的被邀请订单
	 * @param invited
	 * @return
	 */
	public List<Map<String,Object>> getInvites(Integer invited,Integer page,Integer pageSize);
	/**
	 * 邀请车主接单
	 * @param hlbOrderNo
	 * @param invited
	 * @return
	 */
	public boolean orderInvite(String hlbOrderNo,Integer invited);
	/**
	 * 为乘客推荐用车
	 * @param userLon 经度
	 * @param userLat 纬度
	 * @param region 地区
	 * @return
	 */
	public List<Map<String,Object>> recomendDriver(String userLon,String userLat,String region);
	/**
	 * 抢单大厅，按照距离排序查看订单
	 * @param carType
	 * @param orderType
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> listOrdersByDistance(String carType,Integer orderType,double lon,double lat,Integer page,Integer pageSize);
	/**
	 * 查看我的订单
	 * @param userId 
	 * @param orderStatus 订单状态
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listMyOrders(Integer userId,Integer orderStatus,Integer page,Integer pageSize);
	
	
}
