package cn.jyb.service;

import java.util.List;
import java.util.Map;

/**
 * ����������У���������������������ά�붩����
 * @author Eashion
 *
 */
public interface OrderService {
	
	/**
	 * �鿴����
	 * @param tradeStatus
	 * @param orderType �������ͣ�1-��У������2-����������3-��ά�붩����
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listOrders(String tradeStatus,String orderType,Integer page,Integer pageSize);

	
}
