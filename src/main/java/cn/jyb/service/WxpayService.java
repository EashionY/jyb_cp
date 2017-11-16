package cn.jyb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;

public interface WxpayService {

	/**
	 * ΢��APP֧��ͳһ�µ��ӿ�
	 * @param request Ҫ�󴫲Σ�out_trade_no(�����ţ�ѧԱԼ����ʱ��Լ�̼�¼id��Ϊ�����š���������ɲ����˲�)��total_fee��body��payer_id��receiver_id��address
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public SortedMap<Object, Object> wxPrePay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;
	/**
	 * ΢��WEB֧��ͳһ�µ��ӿ�
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public SortedMap<Object, Object> wxWebPrePay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;
	/**
	 * ΢���첽֪ͨ�ӿ�
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public String wxNotify(HttpServletRequest request,HttpServletResponse response) throws IOException, JDOMException;
}
