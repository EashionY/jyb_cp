package cn.jyb.service;

import java.io.IOException;
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
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;

import cn.jyb.dao.OrdersDao;
import cn.jyb.dao.StudentDao;
import cn.jyb.dao.TeachRecordDao;
import cn.jyb.dao.UserDao;
import cn.jyb.entity.Orders;
import cn.jyb.entity.Student;
import cn.jyb.exception.AlipayException;
import cn.jyb.util.DateUtil;
import cn.jyb.util.Message;
@Service("alipayService")
public class AlipayServiceImpl implements AlipayService {
	
	@Resource
	private OrdersDao ordersDao;
	@Resource
	private StudentDao studentDao;
	@Resource
	private TeachRecordDao teachRecordDao;
	@Resource
	private UserDao userDao;
	//支付宝默认网关
	private static final String URL = "https://openapi.alipay.com/gateway.do";
	//驾易宝应用id
	private static final String APP_ID = "2017051107199414";
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
	//驾易宝支付宝公钥
	private static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjrYMa5ncEomaM8"
			+ "hD+D22jpDWvm6vhhIXhsa1V0ytD5T15nI7UWvTYo3WMBMuR5Dz0lyp+FPUMsPFdN51NAzZZp4rh2k02DRpP9+szKHxKUbELo"
			+ "2vNXkH+0LccVmdbcPZgFZ9H7KV0Bm2yRUpQ66/PN3carrYeEiqe9SAuyHa/wG2n8KWePCCdTOhkjKvse9e3BGMvyi3/tvZhr"
			+ "lk/M5PUXFg6ggyH4Gjc44TTn2kjL8GQKpTYsqqhvHy1lPJHYFKkM9+z7YicbQXN0yH3UvGxAaR7ZNQ01WZm5Fs0w4iwi7WtE"
			+ "jgmgHXtSB+rPOBxZ2Cc+n2hRfzf9E9hIPunfw2xwIDAQAB";
	//签名类型
	private static final String SIGN_TYPE = "RSA2";
	
	public String sign(String out_trade_no,String subject,String body,String total_amount,String payer_id,String receiver_id,String address,String orderType) {
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
		if(out_trade_no == null || out_trade_no.trim().isEmpty()){
			out_trade_no = DateUtil.getOrderNum()+DateUtil.getThree();
		}
		//驾易宝商家id
		String seller_id = "2088721004502656";
		//signInfo字符串
		String signInfo = "{"+"\"timeout_express\":\"30m\","+
				"\"seller_id\":\""+seller_id+"\","+
				"\"product_code\":\"QUICK_MSECURITY_PAY\","+
				"\"total_amount\":\""+total_amount+"\","+
				"\"subject\":\""+subject+"\","+
				"\"body\":\""+body+"\","+
				"\"out_trade_no\":\""+out_trade_no+"\""+"}";
		request.setBizContent(signInfo);
		//服务器回调地址
		request.setNotifyUrl("http://api.drivingyeepay.com/jyb_cp/alipay/notify");
		AlipayTradeAppPayResponse response = null;
		try {
			response = alipayClient.sdkExecute(request);
//			System.out.println(response.getBody());
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
		orders.setAddress(address);
		orders.setOrderType(orderType);
		ordersDao.save(orders);
		//就是orderString 可以直接给客户端请求，无需再做处理。
		return response.getBody();
	}
	
	public String webSign(String out_trade_no,String subject,String body,String total_amount,String payer_id,String receiver_id,String address,String orderType) {
		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(URL,APP_ID,APP_PRIVATE_KEY, FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
		//实例化具体API对应的request类
		AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();
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
		if(out_trade_no == null || out_trade_no.trim().isEmpty()){
			out_trade_no = DateUtil.getOrderNum()+DateUtil.getThree();
		}
		//驾易宝商家id
		String seller_id = "2088721004502656";
		//signInfo字符串
		String signInfo = "{"+"\"timeout_express\":\"30m\","+
				"\"seller_id\":\""+seller_id+"\","+
				"\"product_code\":\"QUICK_WAP_PAY\","+
				"\"total_amount\":\""+total_amount+"\","+
				"\"subject\":\""+subject+"\","+
				"\"body\":\""+body+"\","+
				"\"out_trade_no\":\""+out_trade_no+"\""+"}";
		alipayRequest.setBizContent(signInfo);
		//服务器回调地址
		alipayRequest.setNotifyUrl("http://api.drivingyeepay.com/jyb_cp/alipay/notify");
		String form = "";
		try {
			form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
		} catch (AlipayApiException e) {
			e.printStackTrace();
			throw new AlipayException("表单生成失败");
		}
		System.out.println("form:"+form);
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
		orders.setAddress(address);
		orders.setOrderType(orderType);
		ordersDao.save(orders);
		return form;
	}

	public String notify(HttpServletRequest request) throws IOException {
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<String,String>();
		Map<String, String[]> requestParams = request.getParameterMap();
		//分割线
//		String line = System.getProperty("line.separator");
//		StringBuffer str = new StringBuffer();
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
//			str.append(name+":"+valueStr).append(line);
		}
		//调试，打印回调参数
//		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("alipay.txt"),"UTF-8");
//		osw.write(str.toString());
//		osw.flush();
		//调用SDK验证签名
		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
		boolean flag = false;
		try {
			flag = AlipaySignature.rsaCheckV1(params, ALIPAY_PUBLIC_KEY, CHARSET, SIGN_TYPE);
//			osw.write("验签flag:"+flag+line);
//			osw.flush();
		} catch (AlipayApiException e) {
//			osw.write(e.toString()+line);
//			osw.flush();
			e.printStackTrace();
			return "fail";
		}
		if(flag){
			//四步验证
			String trade_status = params.get("trade_status");
			String out_trade_no = params.get("out_trade_no");
			Orders orders = ordersDao.findByNo(out_trade_no);
//			osw.write("数据库中的total_amount:"+orders.getTotal_amount()+line);
//			osw.write("返回参数total_amount:"+params.get("total_amount")+line);
//			osw.write("数据库中的seller_id:"+orders.getSeller_id()+line);
//			osw.write("返回参数seller_id:"+params.get("seller_id")+line);
//			osw.write("返回参数app_id:"+params.get("app_id")+line);
//			osw.write("应用id:"+APP_ID+line);
//			osw.flush();
			//四步验证通过
			if(orders!=null && orders.getTotal_amount().equals(params.get("total_amount"))
					&& orders.getSeller_id().equals(params.get("seller_id"))
					&& params.get("app_id").equals(APP_ID)){
//				osw.write("通过四步验证"+line);
//				osw.flush();
				int i;
				try {
					//更新数据库中交易状态
					i = ordersDao.updateStatus(trade_status, out_trade_no);
					ordersDao.finishOrder(out_trade_no);
				} catch (Exception e) {
//					osw.write(e.toString()+line);
//					osw.flush();
					e.printStackTrace();
					return "fail";
				}
				if(i!=1){
//					osw.write("数据库更新失败"+line);
//					osw.flush();
					return "fail";
				}
				//交易成功
				if("TRADE_SUCCESS".equals(trade_status)){
					//获得支付者(学员)的用户id
					int user_id = orders.getPayer_id();
					//获得接收方(教练或者学校)的id
					int receiver_id = orders.getReceiver_id();
					//收款方为驾校id，则为学员报名驾校
					if(receiver_id > 0 && receiver_id < 1000000){
						Student student = studentDao.findStudent(user_id, receiver_id);
						//付款成功
						student.setPay_status(1);
						studentDao.updateByPrimaryKeySelective(student);
					}else if(receiver_id > 1000000){//收款方为教练用户id，则为学员预约教练
						//更新约教记录状态为付款成功
						teachRecordDao.updatePayStatus(out_trade_no,1);
						//发送短信通知教练有预约
						String phone = userDao.findById(receiver_id).getPhone();//教练的电话号码
						String name = studentDao.findByUserId(user_id).getStudent_name();//学员姓名
						String templateCode = "SMS_110245059";//阿里大于短信模板号
						Message.sendNotifyMsg(phone, name, templateCode);
					}
				}
				return "success";
			}else{
//				osw.write("未通过四步验证"+line);
//				osw.flush();
				return "fail";
			}
		}else{
			return "fail";
		}
		
	}

}
