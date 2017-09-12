package cn.jyb.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import cn.jyb.dao.OrdersDao;
import cn.jyb.entity.Orders;
import cn.jyb.exception.AlipayException;
import cn.jyb.util.DateUtil;
@Service("alipayService")
public class AlipayServiceImpl implements AlipayService {
	
	@Resource
	private OrdersDao ordersDao;
//	//沙箱版支付宝默认网关
//	private static final String URL = "https://openapi.alipaydev.com/gateway.do";
//	//测试沙箱版支付宝应用id
//	private static final String APP_ID = "2016080600179871";
	//支付宝默认网关
	private static final String URL = "https://openapi.alipay.com/gateway.do";
	//驾易宝应用id
	private static final String APP_ID = "2017051107199414";
	//沙箱应用私钥
//	private static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCWww4ENpodaI1nqDoD2M7beTKbOoOluC2z0yQN4z5o0AguMYIeRd4ktBHMi03aokpsfFqpY1xxs9w/7mPrZtoWj2OoRq8ZIYZGdntBuMxJ+Lypr7Tfn0GZa87Y3fMuVv/B7PVki+duPAE2AoEEmVhvQHppfqUPAWm51C5zABQbBcuS+KkD4A/XFSwF3VXOJtXU15b+Bx/Z7tWqbuGyWceBSCD1HV6dq4Z5ObxG59Qk4W5WYwaZ/xLqwA0D+xIDtEp/74kLoUsoWNuyVd9tb0x/JV44+OyPSyd1+lXq0/CO9z8vP+Q1PMKgk1/v+reLcI3r5bknk9HkxVJWyEXnWxTNAgMBAAECggEACI/nWeHn+seXUE3DjbhgdyJ490vpch31qoLRR2CiiL1X2ZfCNgntxdPcNuuqbkJNS7/8PiaAd4ypc5AMaLAcrq9SD5qEEz9OKu5Lrb3bDFBuFoz+Dr0yso3m33Yre3RBbjgQ77bqW95Yi0K1JRe4aAkXaqpgoWuJH56nTKyyd55UI1L2xzRYz68DsDMc5FIRY6HDNX5EPncqvkcqLFzezr3W9PTEITJCjKNCN6LlCoQ7x4uiAAsV8wr0423FMDdtjuMAP4NLXgo7EeKwIuEumeYwKHzBWQ6AUEAfnDk3HISPFt0Elx+6hp8bqtQ9Ng3F/EQRDEnwqYMSKu8CtzQfwQKBgQDffYnsIaXJuAPbLUn4YxJklYXAIbqpuy1a8+PNdP0psA5ETrKiBuei9kz6TlBeQbVG7M5LWBnjOeLljwyE+CzZXGKcNT55qI+p10wr226Xt78Nc1/pZpsf+T3S+8pDO6ELmplJh13J5PpuBwcWmYFx9KIgFJFJoVzioxkphwO69QKBgQCssTZ39E7oKlwN6HpA+3PKrqoYY/gnqQtnWu8xKO5ZnS8D8AfkwlhVfL9xBiI2X/1c8EsHw2cLOn/kk2zJ/DesIXLZIYaGBjXKiitDw28Qu4PjFvh9mcXOn4JVPWP2Y3Q3eNswnD3GXJF/MFobxDI8bWzGlRL5YC0reLojyjR7eQKBgBe+q8+1x3qGHYrE5g4I4KRlKn++VanA5FG8heCHZHpwZSOmxN3oI+Yxiv4xIeLR0mPFtylUI/P7a3r+VtMt1v4FDckQ8yFT4memI0apmrdt41OXhYIzz5l3DrWC4PuBs1ubQlwIkNNpi53zCzLAfntQBYWpCcKr7UV7FKpP2PqdAoGAFXOqDXqWtrZUWxHu5dWWi63oyUDZ2athFESyg8vO1+jzpyyQ/nS53lyxt0uIwnJoGbxrxZobWcS5kF5T3D9tdv9ssdY9TLbGxdMmDrPfPxfcCUCYO5n3fXRJD2eh3EB8dkuYBtDRx6tGE6Og/eQATYtWQNDR25J76fOEQ9/hZxkCgYEAxJ+4hh67m1fjmZ3sH1gvTw55yliiy9jhgl8x2vpTdoAfpHqxzk9QC8eOXERSOsYi34H9MWpCozp0fQ5FDeDUnz2mA+kzVbZAIqKztUYY5tErHIwL/8O9Fe0NKpUPBgZwJU6AH5U2QE0NGcSz1kz/Io5/wvbQDd++YGZTfgYR3VE=";
	//驾易宝应用私钥
	private static final String APP_PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCS32+31JhDI1Bd"
			+ "+XWIBAtaKALFOH+3w251VN7hRg4yiKTNvZERfv6H81XwzNSajTbvnoL605klodGzXHVzmZNnCguhWmD3tFq00fW+1tAIYc4Q"
			+ "iwEOMze+6pT+yhjchiEW+dysIvEDtP6rcB1XG3TcOvZWjvgNCnjLPJnPVnaYeHVufromXK4TSpsDAFfGv5QaoEUXmo9dObzQ"
			+ "4JPFqLphOWvQAOveKYay/o52cq6VzPYQ88krhat/9S9oZnseZ5uX4ZAg9tLoYyKfvqCTWvUKIivhclFm6eWZ+/hI5JOKF11k"
			+ "hnsSYFmnS37I3J4pc/FRrSetLVy5/9X5AA2JVP+RAgMBAAECggEASBxd7KL7sxL2lX0yYHqj3DsCyTQpON9CL2dhGrfbpqIC"
			+ "UGV/gViZvcQScO0QgWVOEK4B3wbfDWP64pZFsGE5xwBen3URYOzzEA56Gp27fDIRC3xEI2WwPBNO3h35wOQ2AOLinr11Gf3W"
			+ "f/PG5Aq+AJcOdYcycKywjBCP8AWiqs7SolMpJxw8pnEOE4DNPFMo8UysN+6iw/Q9sO3bHOlsTWdrkaxRnbUAPBLdPQ3Rwh5V"
			+ "g4f8xMF/Br3UUzYPQXiPRXMsZGImhLhMygXQGAHZYZeSkFBMCPtYcVWZVKfP62CD6US8Bt8w3FnKt52POSuYJ6hlms6ZdbqR"
			+ "5HDBVUCLdQKBgQDUNYV4xy58+Jf/2ba7spN6lorkXULCVG14KKPn3gqvXT53cepUT4D4iODsisMB4F/YzuYBhYpcf18keGEZ"
			+ "O3Bul85kh9upCJoSa1Yw+pTHiHALOehP5rKdW1bkuRJ61BW783H/OKQWgZb2U3pXkILa1qhUKW29QHXIqUrc6RMfgwKBgQCx"
			+ "LlyrblVggjcoIxU0pFTrdH4pgCNZdf5u/VqT50NVxXz4vBC55SasmHj5CA6NJdPonUHhnF5k72DhV+QzwOZBZDSaRwUSEBqF"
			+ "Sl6iGlR5vtJzlrB8eXNIbiRLoCtLZ7ErXE9Kyf8ojdYE70yg+MHZugCHJLhz1WINYgRu9g5EWwKBgELvnuo56bABufA5/Cm4"
			+ "akyAr87Ym/YwK1d7ejfpEfW+s7V5eTCe40+B3XC/VEODQrhgiqxwMvR6OMiIMBiFZzF7MN8E+4SV8gNdWBKa99L4dl8UJPMP"
			+ "EcgQx5fdyLZ1r2VZpNucQiXXOob4Td7Q9nWQwAfMCZFog76Hp9WGr0GBAoGAQbFp5Jn9B9Jf9v7C/wiq/73qhSKgeKv6EVbY"
			+ "fZwyaBvYETVRkks3QqkpuVPWaVkML+QWqDrc+NmUdxiqYNH1zWsE4erRI3hbDO6dFcdjaNxqlTba6tvw9Xr49B1H0CZ7FxOb"
			+ "xqQMehHUt3GuXMS+wyZmybTAuxnzx/h+olGSyp8CgYEAnR+UU3GNPHh4ECqGxl7x+ZmuVkuHoDeTE0dDpJUuCJH8LXgQdleK"
			+ "gyYb2Q33BVbt2OTizGYG7L3S6og8rqqgnJbcECFHxAyciP2EZoUW6+DemI31QWj8g6CrlgJb5q2SrAwAFhaC79KoFdNsRDVA"
			+ "au/Dhb2xBHIfToQJLNRZVc4=";
	//数据格式
	private static final String FORMAT = "json";
	//编码格式
	private static final String CHARSET = "UTF-8";
	//沙箱支付宝公钥
//	private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAv2+deXU3aQP/0WURV+2WSDCPB/0+dMB0sflZ4vQXEghFnpBUBvKxXDFTakX0ItpSdGpeBOJ4S8Z5SID92n/twO1Vb7ZWEqFNUy5pOk3HJyka7WNkziJJCpxaCjZptl9qxuB16LslJqkIA0/yvGfcywlje182nSY9ivRDgf8Vg8aZeSIZHzum8itpsh/5mgqqYwgjQ0OZRDqF/eG7yDl2SRDn8K+nKK6+FxWkELtjzKLyzpIXYu9tBJR+h74691YFYF7ybyHE9CW0nuGyHE+hLLNgtIG6Gg3WBjZSoee9La7D/P5tbng4rReNfYAEognBvo6kPhx5sGwVn/yrKychHQIDAQAB";
	//驾易宝支付宝公钥
	private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjrYMa5ncEomaM8"
			+ "hD+D22jpDWvm6vhhIXhsa1V0ytD5T15nI7UWvTYo3WMBMuR5Dz0lyp+FPUMsPFdN51NAzZZp4rh2k02DRpP9+szKHxKUbELo"
			+ "2vNXkH+0LccVmdbcPZgFZ9H7KV0Bm2yRUpQ66/PN3carrYeEiqe9SAuyHa/wG2n8KWePCCdTOhkjKvse9e3BGMvyi3/tvZhr"
			+ "lk/M5PUXFg6ggyH4Gjc44TTn2kjL8GQKpTYsqqhvHy1lPJHYFKkM9+z7YicbQXN0yH3UvGxAaR7ZNQ01WZm5Fs0w4iwi7WtE"
			+ "jgmgHXtSB+rPOBxZ2Cc+n2hRfzf9E9hIPunfw2xwIDAQAB";
	//签名类型
	private static final String SIGN_TYPE = "RSA2";
	
	public String sign(String subject,String body,String total_amount,String payer_id,String receiver_id) throws UnsupportedEncodingException {
//		subject = new String(subject.getBytes("ISO-8859-1"), "utf-8");
//		body = new String(body.getBytes("ISO-8859-1"),"utf-8");
		System.out.println("subject:"+subject+",body:"+body);
		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(URL,APP_ID,APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		//实例化具体API对应的request类
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。
//			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
//			model.setBody("测试数据111");
//			model.setSubject("APP支付测试java端");
//			model.setTimeoutExpress("30m");
//			String outTradeNo = DateUtil.getOrderNum();
//			model.setOutTradeNo(outTradeNo);
//			model.setTotalAmount("0.01");
//			model.setProductCode("QUICK_MSECURITY_PAY");
//			request.setBizModel(model);
		String out_trade_no = DateUtil.getOrderNum()+DateUtil.getThree();
		//驾易宝商家id
		String seller_id = "2088721004502656";
		//沙箱版支付宝商家id
//		String seller_id = "2088102170192241";
		//signInfo字符串
		String signInfo = "{"+"\"timeout_express\":\"30m\","+
				"\"seller_id\":\""+seller_id+"\","+
				"\"product_code\":\"QUICK_MSECURITY_PAY\","+
				"\"total_amount\":\""+total_amount+"\","+
				"\"subject\":\""+subject+"\","+
				"\"body\":\""+body+"\","+
				"\"out_trade_no\":\""+out_trade_no+"\""+"}";
		request.setBizContent(signInfo);
		request.setNotifyUrl("http://api.drivingyeepay.com/jyb_cp/alipay/notify");
		AlipayTradeAppPayResponse response = null;
		try {
			response = alipayClient.sdkExecute(request);
//				System.out.println(response.getBody());
		} catch (AlipayApiException e) {
			e.printStackTrace();
			throw new AlipayException("签名失败");
		}
		Orders orders = new Orders();
		orders.setBody(body);
		orders.setOut_trade_no(out_trade_no);
		orders.setPay_method("Alipay");
		orders.setPayer_id(Integer.parseInt(payer_id));
		orders.setReceiver_id(Integer.parseInt(receiver_id));
		orders.setSeller_id(seller_id);
		orders.setSubject(subject);
		orders.setTotal_amount(total_amount);
		orders.setTrade_status("WAIT_BUYER_PAY");
		ordersDao.save(orders);
		//就是orderString 可以直接给客户端请求，无需再做处理。
		return response.getBody();
	}

	public String notify(HttpServletRequest request) throws IOException {
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		//分割线
		String line = System.getProperty("line.separator");
		StringBuffer str = new StringBuffer();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i=0; i < values.length; i++) {
		        valueStr = (i == values.length-1) ? valueStr+values[i] : valueStr+values[i]+",";
		    }
			//乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
			str.append(name+":"+valueStr).append(line);
		}
		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("alipay.txt"),"UTF-8");
		osw.write(str.toString());
		osw.flush();
		//调用SDK验证签名
		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
		boolean flag = false;
		try {
			flag = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE);
			osw.write("验签flag:"+flag+line);
			osw.flush();
		} catch (AlipayApiException e) {
			osw.write(e.toString()+line);
			osw.flush();
			e.printStackTrace();
			return "fail";
		}
		if(flag){
			//四步验证
			String trade_status = params.get("trade_status");
			String out_trade_no = params.get("out_trade_no");
			Orders orders = ordersDao.findByNo(out_trade_no);
			osw.write("数据库中的total_amount:"+orders.getTotal_amount()+line);
			osw.write("返回参数total_amount:"+params.get("total_amount")+line);
			osw.write("数据库中的seller_id:"+orders.getSeller_id()+line);
			osw.write("返回参数seller_id:"+params.get("seller_id")+line);
			osw.write("返回参数app_id:"+params.get("app_id")+line);
			osw.write("应用id:"+APP_ID);
			osw.flush();
			if(orders!=null && orders.getTotal_amount().equals(params.get("total_amount"))
					&& orders.getSeller_id().equals(params.get("seller_id"))
					&& params.get("app_id").equals(APP_ID)){
				osw.write("通过四步验证"+line);
				osw.flush();
				int i;
				try {
					i = ordersDao.updateStatus(trade_status, out_trade_no);
					ordersDao.finishOrder(out_trade_no);
				} catch (Exception e) {
					osw.write(e.toString()+line);
					osw.flush();
					e.printStackTrace();
					return "fail";
				}
				if(i!=1){
					osw.write("数据库更新失败"+line);
					osw.flush();
					return "fail";
				}
				return "success";
			}else{
				osw.write("未通过四步验证"+line);
				osw.flush();
				return "fail";
			}
		}else{
			return "fail";
		}
		
	}

}
