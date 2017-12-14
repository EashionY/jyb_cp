package cn.jyb.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.OrdersDao;
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Resource
	private OrdersDao ordersDao;
	
	public List<Map<String, Object>> listOrders(String tradeStatus, String orderType, Integer page, Integer pageSize) {
		Integer offset = (page - 1) * pageSize;
		if("3".equals(orderType)){//¶þÎ¬Âë¶©µ¥
			return ordersDao.listQrOrders(tradeStatus, offset, pageSize);
		}
		return ordersDao.listOrders(tradeStatus, orderType, offset, pageSize);
	}

	
}
