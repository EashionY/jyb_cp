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
		//�����Ʒ�ļ۸�
		String total_fee = request.getParameter("total_fee");
		//ת��Ϊ��λΪ�ֵļ۸�
		int price100 = new BigDecimal(total_fee).multiply(new BigDecimal(100)).intValue();
		if(price100 <= 0){
			throw new WxpayException("���������");
		}
		logger.info("price100:"+price100);
		System.out.println("price100:"+price100);
		//��Ʒ����(��ʽΪ�����ױ�-xx��У����)
		String body = request.getParameter("body");
		logger.info("body:"+body);
		System.out.println("body:"+body);
		//��ȡ������
		String out_trade_no = request.getParameter("out_trade_no");
		if(out_trade_no == null || out_trade_no.trim().isEmpty()){
			out_trade_no = DateUtil.getOrderNum()+DateUtil.getThree();
		}
		//�µ����ն�IP
		String spbill_create_ip = WxpayUtil.getIPAddr(request);
		logger.info("spbill_create_ip:"+spbill_create_ip);
		System.out.println("spbill_create_ip:"+spbill_create_ip);
		//΢�ŷ������첽֪ͨ��ַ
		String notify_url = "http://api.drivingyeepay.com/jyb/wxpay/notify";
		//�����������
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
		//����ǩ��
		String sign = WxpayUtil.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		//��װ�������Ϊxml
		String requestXML = WxpayUtil.getRequestXml(parameters);
		System.out.println("requestXML:"+requestXML);
		//����ͳһ�µ��ӿ�
		String result = WxpayUtil.httpsRequest(WxpayConfig.UNIFIED_ORDER_URL, "POST", requestXML);
		System.out.println("result:\n"+result);
		
		SortedMap<Object, Object> parameterMap2 = new TreeMap<Object, Object>();
		try {
			//ͳһ�µ��ӿڷ���������prepay_id,�ٰ�ǩ���淶��������ǩ���󣬽����ݴ����APP
			Map<String, String> map = XMLUtil.doXMLParse(result);
			//����ǩ�����ֶ���ΪappId��partnerId��prepayId��nonceStr��timeStamp��package
			parameterMap2.put("appid", WxpayConfig.APPID);
			parameterMap2.put("partnerid", WxpayConfig.MCH_ID);
			parameterMap2.put("prepayid", map.get("prepay_id"));
			parameterMap2.put("package", "Sign=WXPay");
			parameterMap2.put("noncestr", WxpayUtil.CreateNoncestr());
			//������õ�ʱ���Ϊ13λ������΢��Ҫ��10λ���ʽ�ȡ�������λ
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
		//�����Ʒ�ļ۸�
		String total_fee = request.getParameter("total_fee");
		//ת��Ϊ��λΪ�ֵļ۸�
		int price100 = new BigDecimal(total_fee).multiply(new BigDecimal(100)).intValue();
		if(price100 <= 0){
			throw new WxpayException("���������");
		}
		logger.info("price100:"+price100);
		System.out.println("price100:"+price100);
		//��Ʒ����(��ʽΪ�����ױ�-xx��У����)
		String body = request.getParameter("body");
		logger.info("body:"+body);
		System.out.println("body:"+body);
		//��ȡ������
		String out_trade_no = request.getParameter("out_trade_no");
		if(out_trade_no == null || out_trade_no.trim().isEmpty()){
			out_trade_no = DateUtil.getOrderNum()+DateUtil.getThree();
		}
		//�µ����ն�IP
		String spbill_create_ip = WxpayUtil.getIPAddr(request);
		logger.info("spbill_create_ip:"+spbill_create_ip);
		System.out.println("spbill_create_ip:"+spbill_create_ip);
		//΢�ŷ������첽֪ͨ��ַ
		String notify_url = "http://api.drivingyeepay.com/jyb/wxpay/notify";
		//�����������
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
		String scene_info = "{\"h5_info\":{\"type\":\"Wap\",\"wap_url\":\"http://www.drivingyeepay.com\",\"wap_name\":\"���ױ�\"}}";
		parameters.put("scene_info", scene_info);
		//����ǩ��
		String sign = WxpayUtil.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		//��װ�������Ϊxml
		String requestXML = WxpayUtil.getRequestXml(parameters);
		System.out.println("requestXML:"+requestXML);
		//����ͳһ�µ��ӿ�
		String result = WxpayUtil.httpsRequest(WxpayConfig.UNIFIED_ORDER_URL, "POST", requestXML);
		System.out.println("result:\n"+result);
		
		SortedMap<Object, Object> parameterMap2 = new TreeMap<Object, Object>();
		try {
			//ͳһ�µ��ӿڷ���������prepay_id,�ٰ�ǩ���淶��������ǩ���󣬽����ݴ����APP
			Map<String, String> map = XMLUtil.doXMLParse(result);
			//����ǩ�����ֶ���ΪappId��partnerId��prepayId��nonceStr��timeStamp��package
			parameterMap2.put("appid", WxpayConfig.APPID);
			parameterMap2.put("partnerid", WxpayConfig.MCH_ID);
			parameterMap2.put("prepayid", map.get("prepay_id"));
			parameterMap2.put("mweb_url", map.get("mweb_url"));
			parameterMap2.put("noncestr", WxpayUtil.CreateNoncestr());
			//������õ�ʱ���Ϊ13λ������΢��Ҫ��10λ���ʽ�ȡ�������λ
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
		//�����Ʒ�ļ۸�
		String total_fee = request.getParameter("total_fee");
		//ת��Ϊ��λΪ�ֵļ۸�
		int price100 = new BigDecimal(total_fee).multiply(new BigDecimal(100)).intValue();
		if(price100 <= 0){
			throw new WxpayException("���������");
		}
		logger.info("price100:"+price100);
		System.out.println("price100:"+price100);
		//��Ʒ����(��ʽΪ�����ױ�-xx��У����)
		String body = request.getParameter("body");
		logger.info("body:"+body);
		System.out.println("body:"+body);
		//��ȡ������
		String out_trade_no = request.getParameter("out_trade_no");
		if(out_trade_no == null || out_trade_no.trim().isEmpty()){
			out_trade_no = DateUtil.getOrderNum()+DateUtil.getThree();
		}
		// ֧���ߵ��û�id
		Integer payer_id = Integer.parseInt(request.getParameter("payer_id"));
		String openid = wxOpenidMapper.findByUserId(payer_id).getOpenid();
		//�µ����ն�IP
		String spbill_create_ip = WxpayUtil.getIPAddr(request);
		logger.info("spbill_create_ip:"+spbill_create_ip);
		System.out.println("spbill_create_ip:"+spbill_create_ip);
		//΢�ŷ������첽֪ͨ��ַ
		String notify_url = "http://api.drivingyeepay.com/jyb/wxpay/jsNotify";
		//�����������
		SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
		parameters.put("appid", WxpublicConfig.APPID);//΢�Ź��ںŵ�appid
		parameters.put("mch_id", WxpublicConfig.MCH_ID);//΢�Ź��ںŵ��̻�id
		parameters.put("nonce_str", WxpayUtil.CreateNoncestr());
		parameters.put("body", body);
		parameters.put("out_trade_no", out_trade_no);
		parameters.put("fee_type", "CNY");
		parameters.put("total_fee", String.valueOf(price100));
		parameters.put("spbill_create_ip", WxpayUtil.getIPAddr(request));
		parameters.put("notify_url", notify_url);
		parameters.put("trade_type", "JSAPI");
		parameters.put("openid", openid);
		//����ǩ��
		String sign = WxpayUtil.createSign("UTF-8", parameters);
		parameters.put("sign", sign);
		//��װ�������Ϊxml
		String requestXML = WxpayUtil.getRequestXml(parameters);
		System.out.println("requestXML:"+requestXML);
		//����ͳһ�µ��ӿ�
		String result = WxpayUtil.httpsRequest(WxpayConfig.UNIFIED_ORDER_URL, "POST", requestXML);
		System.out.println("result:\n"+result);
		
		SortedMap<Object, Object> parameterMap2 = new TreeMap<Object, Object>();
		try {
			//ͳһ�µ��ӿڷ���������prepay_id,�ٰ�ǩ���淶��������ǩ���󣬽����ݴ����APP
			Map<String, String> map = XMLUtil.doXMLParse(result);
			//����ǩ�����ֶ���ΪappId��nonceStr��timeStamp��package��paySign
			parameterMap2.put("appId", WxpayConfig.APPID);
			parameterMap2.put("package", "prepay_id=" + map.get("prepay_id"));
			parameterMap2.put("nonceStr", WxpayUtil.CreateNoncestr());
			//������õ�ʱ���Ϊ13λ������΢��Ҫ��10λ���ʽ�ȡ�������λ
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
		//��ȡ����
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
		//����xml��Ϊmap
		Map<String, String> map = new HashMap<String, String>();
		map = XMLUtil.doXMLParse(sb.toString());
		for(Object keyValue : map.keySet()){
			logger.info(keyValue+"="+map.get(keyValue));
		}
		//���˿�ֵnull������TreeMap
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

		//Ҫ���ص�xml
		String resXML = "";
		//�ж�ǩ���Ƿ���ȷ
		if(WxpayUtil.isTenpaySign("UTF-8", packageParams)){//ǩ����ȷ
			if("SUCCESS".equals((String)packageParams.get("result_code"))){//֧���ɹ�
				String mch_id = (String)packageParams.get("mch_id");
				String out_trade_no = (String)packageParams.get("out_trade_no");
				String total_fee = (String)packageParams.get("total_fee");
				//΢��֧��������
				String transaction_id = (String)packageParams.get("transaction_id");
				Orders orders = ordersDao.findByNo(out_trade_no);
				if(!WxpayConfig.MCH_ID.equals(mch_id) || orders==null || new BigDecimal(total_fee).compareTo(new BigDecimal(orders.getTotal_amount()).multiply(new BigDecimal(100))) != 0){
					logger.info("֧��ʧ�ܣ�������Ϣ��" + "��������");
					resXML = "<xml>"
							   + "<return_code><![CDATA[FAIL]]></return_code>"
							   + "<return_msg><![CDATA[��������]]></return_msg>"
							   + "</xml>";
				}else{
					//�������ݿ��ж�������״̬
					int i = ordersDao.updateStatus("TRADE_SUCCESS", out_trade_no);
					ordersDao.finishOrder(out_trade_no);
					if(i != 1){
						logger.info("֧��ʧ�ܣ�������Ϣ��" + "����������״̬����ʧ��");
						resXML = "<xml>"
								   + "<return_code><![CDATA[FAIL]]></return_code>"
								   + "<return_msg><![CDATA[���ݿⶩ������״̬����ʧ��]]></return_msg>"
								   + "</xml>";
					}else{
						//���֧����(ѧԱ)���û�id
						int user_id = orders.getPayer_id();
						//��ý��շ�(��������ѧУ)��id
						int receiver_id = orders.getReceiver_id();
						if("1".equals(orders.getOrderType())){//��У����
							Student student = studentDao.findStudent(user_id, receiver_id);
							//����ѧԱ��״̬Ϊ����ɹ�
							student.setPay_status(1);
							student.setSignup_time(new Date());
							studentDao.updateByPrimaryKeySelective(student);
						}else if("2".equals(orders.getOrderType())){//��������
							//����Լ�̼�¼״̬Ϊ����ɹ�
							teachRecordDao.updatePayStatus(out_trade_no,1);
							//���Ͷ���֪ͨ������ԤԼ
							String phone = userDao.findById(receiver_id).getPhone();//�����ĵ绰����
							String name = studentDao.findByUserId(user_id).getStudent_name();//ѧԱ����
							String templateCode = "SMS_110245059";//������ڶ���ģ���
							Message.sendNotifyMsg(phone, name, templateCode);
						}
						resXML = "<xml>"
									+ "<return_code><![CDATA[SUCCESS]]></return_code>"
									+ "<return_msg><![CDATA[OK]]></return_msg>"
									+ "</xml>";
						logger.info("֧���ɹ��������Ѵ���");
					}
				}
			}else{
				logger.info("֧��ʧ��,������Ϣ��" + packageParams.get("err_code"));  
                resXML = "<xml>" 
                           + "<return_code><![CDATA[FAIL]]></return_code>"  
                           + "<return_msg><![CDATA[����Ϊ��]]></return_msg>" 
                           + "</xml> ";
			}
		}else{
			logger.info("֪ͨǩ����֤ʧ��");
			resXML = "<xml>" 
						+ "<return_code><![CDATA[FAIL]]></return_code>"  
						+ "<return_msg><![CDATA[����Ϊ��]]></return_msg>" 
						+ "</xml> ";
		}
		return resXML;
	}
	
	public String wxJsNotify(HttpServletRequest request, HttpServletResponse response) throws IOException, JDOMException {
		//��ȡ����
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
		//����xml��Ϊmap
		Map<String, String> map = new HashMap<String, String>();
		map = XMLUtil.doXMLParse(sb.toString());
		for(Object keyValue : map.keySet()){
			logger.info(keyValue+"="+map.get(keyValue));
		}
		//���˿�ֵnull������TreeMap
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

		//Ҫ���ص�xml
		String resXML = "";
		//�ж�ǩ���Ƿ���ȷ
		if(WxpayUtil.isTenpaySign("UTF-8", packageParams)){//ǩ����ȷ
			if("SUCCESS".equals((String)packageParams.get("result_code"))){//֧���ɹ�
				String mch_id = (String)packageParams.get("mch_id");
				String out_trade_no = (String)packageParams.get("out_trade_no");
				String total_fee = (String)packageParams.get("total_fee");
				//΢��֧��������
				String transaction_id = (String)packageParams.get("transaction_id");
				Orders orders = ordersDao.findByNo(out_trade_no);
				if(!WxpublicConfig.MCH_ID.equals(mch_id) || orders==null || new BigDecimal(total_fee).compareTo(new BigDecimal(orders.getTotal_amount()).multiply(new BigDecimal(100))) != 0){
					logger.info("֧��ʧ�ܣ�������Ϣ��" + "��������");
					resXML = "<xml>"
							   + "<return_code><![CDATA[FAIL]]></return_code>"
							   + "<return_msg><![CDATA[��������]]></return_msg>"
							   + "</xml>";
				}else{
					//�������ݿ��ж�������״̬
					int i = ordersDao.updateStatus("TRADE_SUCCESS", out_trade_no);
					ordersDao.finishOrder(out_trade_no);
					if(i != 1){
						logger.info("֧��ʧ�ܣ�������Ϣ��" + "����������״̬����ʧ��");
						resXML = "<xml>"
								   + "<return_code><![CDATA[FAIL]]></return_code>"
								   + "<return_msg><![CDATA[���ݿⶩ������״̬����ʧ��]]></return_msg>"
								   + "</xml>";
					}else{
						//���֧����(ѧԱ)���û�id
						int user_id = orders.getPayer_id();
						//��ý��շ�(��������ѧУ)��id
						int receiver_id = orders.getReceiver_id();
						if("1".equals(orders.getOrderType())){//��У����
							Student student = studentDao.findStudent(user_id, receiver_id);
							//����ѧԱ��״̬Ϊ����ɹ�
							student.setPay_status(1);
							student.setSignup_time(new Date());
							studentDao.updateByPrimaryKeySelective(student);
						}else if("2".equals(orders.getOrderType())){//��������
							//����Լ�̼�¼״̬Ϊ����ɹ�
							teachRecordDao.updatePayStatus(out_trade_no,1);
							//���Ͷ���֪ͨ������ԤԼ
							String phone = userDao.findById(receiver_id).getPhone();//�����ĵ绰����
							String name = studentDao.findByUserId(user_id).getStudent_name();//ѧԱ����
							String templateCode = "SMS_110245059";//������ڶ���ģ���
							Message.sendNotifyMsg(phone, name, templateCode);
						}
						resXML = "<xml>"
									+ "<return_code><![CDATA[SUCCESS]]></return_code>"
									+ "<return_msg><![CDATA[OK]]></return_msg>"
									+ "</xml>";
						logger.info("֧���ɹ��������Ѵ���");
					}
				}
			}else{
				logger.info("֧��ʧ��,������Ϣ��" + packageParams.get("err_code"));  
                resXML = "<xml>" 
                           + "<return_code><![CDATA[FAIL]]></return_code>"  
                           + "<return_msg><![CDATA[����Ϊ��]]></return_msg>" 
                           + "</xml> ";
			}
		}else{
			logger.info("֪ͨǩ����֤ʧ��");
			resXML = "<xml>" 
						+ "<return_code><![CDATA[FAIL]]></return_code>"  
						+ "<return_msg><![CDATA[����Ϊ��]]></return_msg>" 
						+ "</xml> ";
		}
		return resXML;
	}

}
