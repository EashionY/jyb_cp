package cn.jyb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;

public interface WxpayService {

	/**
	 * 微信统一下单接口
	 * @param request 要求传参：total_fee，body，payer_id，receiver_id
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public SortedMap<Object, Object> wxPrePay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;
	
	/**
	 * 微信异步通知接口
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 * @throws JDOMException 
	 */
	public String wxNotify(HttpServletRequest request,HttpServletResponse response) throws IOException, JDOMException;
}
