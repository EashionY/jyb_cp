package cn.jyb.service;

import java.util.List;
import java.util.Map;

/**
 * 订单管理（驾校订单，教练订单，购买二维码订单）
 * @author Eashion
 *
 */
public interface OrderService {
	
	/**
	 * 查看订单
	 * @param tradeStatus
	 * @param orderType 订单类型（1-驾校订单，2-教练订单，3-二维码订单）
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listOrders(String tradeStatus,String orderType,Integer page,Integer pageSize);

	
}
