package cn.jyb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom.JDOMException;

public interface WxpayService {

	/**
	 * 微信APP支付统一下单接口
	 * @param request 要求传参：out_trade_no(订单号，学员约教练时，约教记录id即为订单号。其余情况可不传此参)，total_fee，body，payer_id，receiver_id，address
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public SortedMap<Object, Object> wxPrePay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;
	/**
	 * 微信WEB支付统一下单接口
	 * @param request
	 * @param response
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public SortedMap<Object, Object> wxWebPrePay(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException;
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
