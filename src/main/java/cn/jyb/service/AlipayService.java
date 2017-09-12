package cn.jyb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public interface AlipayService {

	/**
	 * 对支付宝支付信息进行签名
	 * @param subject
	 * @param body
	 * @param total_amount
	 * @param payer_id
	 * @param receiver_id
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String sign(String subject,String body,String total_amount,String payer_id,String receiver_id) throws UnsupportedEncodingException;
	
	/**
	 * 支付成功后，回调
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public String notify(HttpServletRequest request) throws IOException;
}
