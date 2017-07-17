package test;

import org.junit.Test;

import com.alipay.api.AlipayApiException;

import cn.jyb.util.AlipayUtil;

public class TestAlipay {

	@Test
	public void test() throws AlipayApiException{
		String signInfo = "{"+"\"timeout_express\":\"30m\","+
				"\"seller_id\":\"\","+
				"\"product_code\":\"QUICK_MSECURITY_PAY\","+
				"\"total_amount\":\"0.01\","+
				"\"subject\":\"1\","+
				"\"body\":\"我是测试数据\","+
				"\"out_trade_no\":\"IQJZSRC1YMQB5HU\""+"}";
		String result = AlipayUtil.sign();
		System.out.println(result);
	}
}
