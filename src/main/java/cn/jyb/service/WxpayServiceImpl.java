package cn.jyb.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cn.jyb.dao.OrdersDao;
import cn.jyb.dao.StudentDao;
import cn.jyb.dao.TeachRecordDao;
import cn.jyb.dao.UserDao;
import cn.jyb.dao.WxOpenidMapper;
import cn.jyb.entity.Orders;
import cn.jyb.entity.Student;
import cn.jyb.exception.WxpayException;
import cn.jyb.util.DateUtil;
import cn.jyb.util.Message;
import cn.jyb.util.WxpayConfig;
import cn.jyb.util.WxpayUtil;
import cn.jyb.util.WxpublicConfig;
import cn.jyb.util.XMLUtil;
@Service("wxpayService")
public class WxpayServiceImpl implements WxpayService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private OrdersDao ordersDao;
	@Resource
	private StudentDao studentDao;
	@Resource
	private TeachRecordDao teachRecordDao;
	@Resource
	private UserDao userDao;
	@Resource
	private WxOpenidMapper wxOpenidMapper;
	
	public SortedMap<Object, Object> wxPrePay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		//获得商品的价格
		String total_fee = request.getParameter("total_fee");
		//转换为单位为分的价格
		int price100 = new BigDecimal(total_fee).multiply(new BigDecimal(100)).intValue();
		if(price100 <= 0){
			throw new WxpayException("付款金额错误");
		}
		logger.info("price100:"+price100);
		System.out.println("price100:"+price100);
		//商品描述(格式为：驾易宝-xx驾校报名)
		String body = request.getParameter("body");
		logger.info("body:"+body);
		System.out.println("body:"+body);
		//获取订单号
		String out_trade_no = request.getParameter("out_trade_no");
		if(out_trade_no == null || out_trade_no.trim().isEmpty()){
			out_trade_no = DateUtil.getOrderNum()+DateUtil.getThree();
		}
		//下单的终端IP
		String spbill_create_ip = WxpayUtil.getIPAddr(request);
		logger.info("spbill_create_ip:"+spbill_create_ip);
		System.out.println("spbill_create_ip:"+spbill_create_ip);
		//微信服务器异步通知地址
		String notify_url = "http://api.drivingyeepay.com/jyb/wxpay/notify";
		//生成请求参数
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", WxpayConfig.APPID);
		parameters.put("mch_id", WxpayConfig.MCH_ID);
		parameters.put("nonce_str", WxpayUtil.CreateNoncestr());
		parameters.put("body", body);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("fee_type", "CNY");
		parameters.put("total_fee", String.valueOf(price100));
		parameters.put("spbill_create_ip", WxpayUtil.getIPAddr(request));
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", "APP");
		//设置签名
		String sign = WxpayUtil.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		//封装请求参数为xml
		String requestXML = WxpayUtil.getRequestXml(parameters);
		System.out.println("requestXML:"+requestXML);
		//调用统一下单接口
		String result = WxpayUtil.httpsRequest(WxpayConfig.UNIFIED_ORDER_URL, "POST", requestXML);
		System.out.println("result:\n"+result);
		
		SortedMap<Object, Object> parameterMap2 = new TreeMap<Object, Object>();
		try {
			//统一下单接口返回正常的prepay_id,再按签名规范重新生成签名后，将数据传输给APP
			Map<String, String> map = XMLUtil.doXMLParse(result);
			//参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package
			parameterMap2.put("appid", WxpayConfig.APPID);
			parameterMap2.put("partnerid", WxpayConfig.MCH_ID);
			parameterMap2.put("prepayid", map.get("prepay_id"));
			parameterMap2.put("package", "Sign=WXPay");
			parameterMap2.put("noncestr", WxpayUtil.CreateNoncestr());
			//本来获得的时间戳为13位，但是微信要求10位，故截取了最后三位
			parameterMap2.put("timestamp", String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
			String sign2 = WxpayUtil.createSign("UTF-8", parameterMap2);
			parameterMap2.put("sign", sign2);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Orders orders = new Orders();
		orders.setBody(body);
		orders.setOut_trade_no(out_trade_no);
		orders.setPay_method("WxPay");
		orders.setPayer_id(Integer.parseInt(request.getParameter("payer_id")));
		orders.setReceiver_id(Integer.parseInt(request.getParameter("receiver_id")));
		orders.setTotal_amount(total_fee);
		orders.setTrade_status("WAIT_BUYER_PAY");
		orders.setAddress(request.getParameter("address"));
		orders.setOrderType(request.getParameter("orderType"));
		ordersDao.save(orders);
		return parameterMap2;
	}

	public SortedMap<Object, Object> wxWebPrePay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		//获得商品的价格
		String total_fee = request.getParameter("total_fee");
		//转换为单位为分的价格
		int price100 = new BigDecimal(total_fee).multiply(new BigDecimal(100)).intValue();
		if(price100 <= 0){
			throw new WxpayException("付款金额错误");
		}
		logger.info("price100:"+price100);
		System.out.println("price100:"+price100);
		//商品描述(格式为：驾易宝-xx驾校报名)
		String body = request.getParameter("body");
		logger.info("body:"+body);
		System.out.println("body:"+body);
		//获取订单号
		String out_trade_no = request.getParameter("out_trade_no");
		if(out_trade_no == null || out_trade_no.trim().isEmpty()){
			out_trade_no = DateUtil.getOrderNum()+DateUtil.getThree();
		}
		//下单的终端IP
		String spbill_create_ip = WxpayUtil.getIPAddr(request);
		logger.info("spbill_create_ip:"+spbill_create_ip);
		System.out.println("spbill_create_ip:"+spbill_create_ip);
		//微信服务器异步通知地址
		String notify_url = "http://api.drivingyeepay.com/jyb/wxpay/notify";
		//生成请求参数
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", WxpayConfig.APPID);
		parameters.put("mch_id", WxpayConfig.MCH_ID);
		parameters.put("nonce_str", WxpayUtil.CreateNoncestr());
		parameters.put("body", body);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("fee_type", "CNY");
		parameters.put("total_fee", String.valueOf(price100));
		parameters.put("spbill_create_ip", WxpayUtil.getIPAddr(request));
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", "MWEB");
		String scene_info = "{\"h5_info\":{\"type\":\"Wap\",\"wap_url\":\"http://www.drivingyeepay.com\",\"wap_name\":\"驾易宝\"}}";
		parameters.put("scene_info", scene_info);
		//设置签名
		String sign = WxpayUtil.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		//封装请求参数为xml
		String requestXML = WxpayUtil.getRequestXml(parameters);
		System.out.println("requestXML:"+requestXML);
		//调用统一下单接口
		String result = WxpayUtil.httpsRequest(WxpayConfig.UNIFIED_ORDER_URL, "POST", requestXML);
		System.out.println("result:\n"+result);
		
		SortedMap<Object, Object> parameterMap2 = new TreeMap<Object, Object>();
		try {
			//统一下单接口返回正常的prepay_id,再按签名规范重新生成签名后，将数据传输给APP
			Map<String, String> map = XMLUtil.doXMLParse(result);
			//参与签名的字段名为appId，partnerId，prepayId，nonceStr，timeStamp，package
			parameterMap2.put("appid", WxpayConfig.APPID);
			parameterMap2.put("partnerid", WxpayConfig.MCH_ID);
			parameterMap2.put("prepayid", map.get("prepay_id"));
			parameterMap2.put("mweb_url", map.get("mweb_url"));
			parameterMap2.put("noncestr", WxpayUtil.CreateNoncestr());
			//本来获得的时间戳为13位，但是微信要求10位，故截取了最后三位
			parameterMap2.put("timestamp", String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
			String sign2 = WxpayUtil.createSign("UTF-8", parameterMap2);
			parameterMap2.put("sign", sign2);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Orders orders = new Orders();
		orders.setBody(body);
		orders.setOut_trade_no(out_trade_no);
		orders.setPay_method("WxPay");
		orders.setPayer_id(Integer.parseInt(request.getParameter("payer_id")));
		orders.setReceiver_id(Integer.parseInt(request.getParameter("receiver_id")));
		orders.setTotal_amount(total_fee);
		orders.setTrade_status("WAIT_BUYER_PAY");
		orders.setAddress(request.getParameter("address"));
		orders.setOrderType(request.getParameter("orderType"));
		ordersDao.save(orders);
		return parameterMap2;
	}
	
	public SortedMap<Object, Object> wxJSPrePay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		//获得商品的价格
		String total_fee = request.getParameter("total_fee");
		//转换为单位为分的价格
		int price100 = new BigDecimal(total_fee).multiply(new BigDecimal(100)).intValue();
		if(price100 <= 0){
			throw new WxpayException("付款金额错误");
		}
		logger.info("price100:"+price100);
		System.out.println("price100:"+price100);
		//商品描述(格式为：驾易宝-xx驾校报名)
		String body = request.getParameter("body");
		logger.info("body:"+body);
		System.out.println("body:"+body);
		//获取订单号
		String out_trade_no = request.getParameter("out_trade_no");
		if(out_trade_no == null || out_trade_no.trim().isEmpty()){
			out_trade_no = DateUtil.getOrderNum()+DateUtil.getThree();
		}
		// 支付者的用户id
		Integer payer_id = Integer.parseInt(request.getParameter("payer_id"));
		String openid = wxOpenidMapper.findByUserId(payer_id).getOpenid();
		//下单的终端IP
		String spbill_create_ip = WxpayUtil.getIPAddr(request);
		logger.info("spbill_create_ip:"+spbill_create_ip);
		System.out.println("spbill_create_ip:"+spbill_create_ip);
		//微信服务器异步通知地址
		String notify_url = "http://api.drivingyeepay.com/jyb/wxpay/jsNotify";
		//生成请求参数
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", WxpublicConfig.APPID);//微信公众号的appid
		parameters.put("mch_id", WxpublicConfig.MCH_ID);//微信公众号的商户id
		parameters.put("nonce_str", WxpayUtil.CreateNoncestr());
		parameters.put("body", body);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("fee_type", "CNY");
		parameters.put("total_fee", String.valueOf(price100));
		parameters.put("spbill_create_ip", WxpayUtil.getIPAddr(request));
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", "JSAPI");
		parameters.put("openid", openid);
		//设置签名
		String sign = WxpayUtil.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		//封装请求参数为xml
		String requestXML = WxpayUtil.getRequestXml(parameters);
		System.out.println("requestXML:"+requestXML);
		//调用统一下单接口
		String result = WxpayUtil.httpsRequest(WxpayConfig.UNIFIED_ORDER_URL, "POST", requestXML);
		System.out.println("result:\n"+result);
		
		SortedMap<Object, Object> parameterMap2 = new TreeMap<Object, Object>();
		try {
			//统一下单接口返回正常的prepay_id,再按签名规范重新生成签名后，将数据传输给APP
			Map<String, String> map = XMLUtil.doXMLParse(result);
			//参与签名的字段名为appId，nonceStr，timeStamp，package，paySign
			parameterMap2.put("appId", WxpayConfig.APPID);
			parameterMap2.put("package", "prepay_id=" + map.get("prepay_id"));
			parameterMap2.put("nonceStr", WxpayUtil.CreateNoncestr());
			//本来获得的时间戳为13位，但是微信要求10位，故截取了最后三位
			parameterMap2.put("timeStamp", String.valueOf(System.currentTimeMillis()).toString().substring(0, 10));
			parameterMap2.put("signType", "MD5");
			String sign2 = WxpayUtil.createSign("UTF-8", parameterMap2);
			parameterMap2.put("paySign", sign2);
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Orders orders = new Orders();
		orders.setBody(body);
		orders.setOut_trade_no(out_trade_no);
		orders.setPay_method("WxPay");
		orders.setPayer_id(payer_id);
		orders.setReceiver_id(Integer.parseInt(request.getParameter("receiver_id")));
		orders.setTotal_amount(total_fee);
		orders.setTrade_status("WAIT_BUYER_PAY");
		orders.setAddress(request.getParameter("address"));
		orders.setOrderType(request.getParameter("orderType"));
		ordersDao.save(orders);
		return parameterMap2;
	}
	
	public String wxNotify(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
		//读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while((s = in.readLine()) != null){
			sb.append(s);
		}
		in.close();
		inputStream.close();
		//解析xml成为map
		Map<String, String> map = new HashMap<String, String>();
		map = XMLUtil.doXMLParse(sb.toString());
		for(Object keyValue : map.keySet()){
			logger.info(keyValue+"="+map.get(keyValue));
		}
		//过滤空值null，设置TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			String parameter = (String) it.next();
			String parameterValue = map.get(parameter);
			String v = "";
			if(parameterValue != null){
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}

		//要返回的xml
		String resXML = "";
		//判断签名是否正确
		if(WxpayUtil.isTenpaySign("UTF-8", packageParams)){//签名正确
			if("SUCCESS".equals((String)packageParams.get("result_code"))){//支付成功
				String mch_id = (String)packageParams.get("mch_id");
				String out_trade_no = (String)packageParams.get("out_trade_no");
				String total_fee = (String)packageParams.get("total_fee");
				//微信支付订单号
				String transaction_id = (String)packageParams.get("transaction_id");
				Orders orders = ordersDao.findByNo(out_trade_no);
				if(!WxpayConfig.MCH_ID.equals(mch_id) || orders==null || new BigDecimal(total_fee).compareTo(new BigDecimal(orders.getTotal_amount()).multiply(new BigDecimal(100))) != 0){
					logger.info("支付失败，错误信息：" + "参数错误");
					resXML = "<xml>"
							   + "<return_code><![CDATA[FAIL]]></return_code>"
							   + "<return_msg><![CDATA[参数错误]]></return_msg>"
							   + "</xml>";
				}else{
					//更新数据库中订单交易状态
					int i = ordersDao.updateStatus("TRADE_SUCCESS", out_trade_no);
					ordersDao.finishOrder(out_trade_no);
					if(i != 1){
						logger.info("支付失败，错误信息：" + "服务器订单状态更新失败");
						resXML = "<xml>"
								   + "<return_code><![CDATA[FAIL]]></return_code>"
								   + "<return_msg><![CDATA[数据库订单交易状态更新失败]]></return_msg>"
								   + "</xml>";
					}else{
						//获得支付者(学员)的用户id
						int user_id = orders.getPayer_id();
						//获得接收方(教练或者学校)的id
						int receiver_id = orders.getReceiver_id();
						if("1".equals(orders.getOrderType())){//驾校订单
							Student student = studentDao.findStudent(user_id, receiver_id);
							//更新学员的状态为付款成功
							student.setPay_status(1);
							student.setSignup_time(new Date());
							studentDao.updateByPrimaryKeySelective(student);
						}else if("2".equals(orders.getOrderType())){//教练订单
							//更新约教记录状态为付款成功
							teachRecordDao.updatePayStatus(out_trade_no,1);
							//发送短信通知教练有预约
							String phone = userDao.findById(receiver_id).getPhone();//教练的电话号码
							String name = studentDao.findByUserId(user_id).getStudent_name();//学员姓名
							String templateCode = "SMS_110245059";//阿里大于短信模板号
							Message.sendNotifyMsg(phone, name, templateCode);
						}
						resXML = "<xml>"
									+ "<return_code><![CDATA[SUCCESS]]></return_code>"
									+ "<return_msg><![CDATA[OK]]></return_msg>"
									+ "</xml>";
						logger.info("支付成功，订单已处理");
					}
				}
			}else{
				logger.info("支付失败,错误信息：" + packageParams.get("err_code"));  
                resXML = "<xml>" 
                           + "<return_code><![CDATA[FAIL]]></return_code>"  
                           + "<return_msg><![CDATA[报文为空]]></return_msg>" 
                           + "</xml> ";
			}
		}else{
			logger.info("通知签名验证失败");
			resXML = "<xml>" 
						+ "<return_code><![CDATA[FAIL]]></return_code>"  
						+ "<return_msg><![CDATA[报文为空]]></return_msg>" 
						+ "</xml> ";
		}
		return resXML;
	}
	
	public String wxJsNotify(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
		//读取参数
		InputStream inputStream;
		StringBuffer sb = new StringBuffer();
		inputStream = request.getInputStream();
		String s;
		BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
		while((s = in.readLine()) != null){
			sb.append(s);
		}
		in.close();
		inputStream.close();
		//解析xml成为map
		Map<String, String> map = new HashMap<String, String>();
		map = XMLUtil.doXMLParse(sb.toString());
		for(Object keyValue : map.keySet()){
			logger.info(keyValue+"="+map.get(keyValue));
		}
		//过滤空值null，设置TreeMap
		SortedMap<Object, Object> packageParams = new TreeMap<Object, Object>();
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			String parameter = (String) it.next();
			String parameterValue = map.get(parameter);
			String v = "";
			if(parameterValue != null){
				v = parameterValue.trim();
			}
			packageParams.put(parameter, v);
		}

		//要返回的xml
		String resXML = "";
		//判断签名是否正确
		if(WxpayUtil.isTenpaySign("UTF-8", packageParams)){//签名正确
			if("SUCCESS".equals((String)packageParams.get("result_code"))){//支付成功
				String mch_id = (String)packageParams.get("mch_id");
				String out_trade_no = (String)packageParams.get("out_trade_no");
				String total_fee = (String)packageParams.get("total_fee");
				//微信支付订单号
				String transaction_id = (String)packageParams.get("transaction_id");
				Orders orders = ordersDao.findByNo(out_trade_no);
				if(!WxpublicConfig.MCH_ID.equals(mch_id) || orders==null || new BigDecimal(total_fee).compareTo(new BigDecimal(orders.getTotal_amount()).multiply(new BigDecimal(100))) != 0){
					logger.info("支付失败，错误信息：" + "参数错误");
					resXML = "<xml>"
							   + "<return_code><![CDATA[FAIL]]></return_code>"
							   + "<return_msg><![CDATA[参数错误]]></return_msg>"
							   + "</xml>";
				}else{
					//更新数据库中订单交易状态
					int i = ordersDao.updateStatus("TRADE_SUCCESS", out_trade_no);
					ordersDao.finishOrder(out_trade_no);
					if(i != 1){
						logger.info("支付失败，错误信息：" + "服务器订单状态更新失败");
						resXML = "<xml>"
								   + "<return_code><![CDATA[FAIL]]></return_code>"
								   + "<return_msg><![CDATA[数据库订单交易状态更新失败]]></return_msg>"
								   + "</xml>";
					}else{
						//获得支付者(学员)的用户id
						int user_id = orders.getPayer_id();
						//获得接收方(教练或者学校)的id
						int receiver_id = orders.getReceiver_id();
						if("1".equals(orders.getOrderType())){//驾校订单
							Student student = studentDao.findStudent(user_id, receiver_id);
							//更新学员的状态为付款成功
							student.setPay_status(1);
							student.setSignup_time(new Date());
							studentDao.updateByPrimaryKeySelective(student);
						}else if("2".equals(orders.getOrderType())){//教练订单
							//更新约教记录状态为付款成功
							teachRecordDao.updatePayStatus(out_trade_no,1);
							//发送短信通知教练有预约
							String phone = userDao.findById(receiver_id).getPhone();//教练的电话号码
							String name = studentDao.findByUserId(user_id).getStudent_name();//学员姓名
							String templateCode = "SMS_110245059";//阿里大于短信模板号
							Message.sendNotifyMsg(phone, name, templateCode);
						}
						resXML = "<xml>"
									+ "<return_code><![CDATA[SUCCESS]]></return_code>"
									+ "<return_msg><![CDATA[OK]]></return_msg>"
									+ "</xml>";
						logger.info("支付成功，订单已处理");
					}
				}
			}else{
				logger.info("支付失败,错误信息：" + packageParams.get("err_code"));  
                resXML = "<xml>" 
                           + "<return_code><![CDATA[FAIL]]></return_code>"  
                           + "<return_msg><![CDATA[报文为空]]></return_msg>" 
                           + "</xml> ";
			}
		}else{
			logger.info("通知签名验证失败");
			resXML = "<xml>" 
						+ "<return_code><![CDATA[FAIL]]></return_code>"  
						+ "<return_msg><![CDATA[报文为空]]></return_msg>" 
						+ "</xml> ";
		}
		return resXML;
	}

}
