package test;

import org.junit.Before;
import org.junit.Test;

import cn.jyb.dao.OrdersDao;
import cn.jyb.entity.Orders;
import cn.jyb.util.DateUtil;

public class TestOrdersDao extends TestBase {

	private OrdersDao ordersDao;
	
	@Before
	public void init(){
		ordersDao = super.getContext().getBean("ordersDao", OrdersDao.class);
	}
	
	@Test
	public void test(){
		Orders orders = new Orders();
		orders.setBody("≤‚ ‘66666");
		orders.setOut_trade_no(DateUtil.getOrderNum());
		orders.setSeller_id("2088721004502656");
		orders.setSubject("≤‚ ‘biaoti");
		orders.setTotal_amount("0.1");
		orders.setTrade_status("WAIT_BUYER_PAY");
		orders.setPayer_id(8);
		orders.setReceiver_id(10);
		orders.setPay_method("Alipay");
		ordersDao.save(orders);
	}
	
	@Test
	public void test2(){
		String out_trade_no = "20170607161159";
		int i = ordersDao.updateStatus("TRADE_SUCCESS", out_trade_no);
		System.out.println(i);
	}
	
	@Test
	public void test3(){
		String out_trade_no = "20171116110033632";
		Orders orders = ordersDao.findByNo(out_trade_no);
		System.out.println(orders);
	}
	
	@Test
	public void test4(){
		String out_trade_no = "20170607161159";
		ordersDao.delete(out_trade_no);
	}
	
	@Test
	public void test5(){
		int i = ordersDao.finishOrder("20170607173448");
		System.out.println(i);
	}
}
