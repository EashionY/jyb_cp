package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.HLBOrder;

public interface HLBService {

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
}
