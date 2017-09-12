package cn.jyb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

public interface AlipayService {

	/**
	 * ��֧����֧����Ϣ����ǩ��
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
	 * ֧���ɹ��󣬻ص�
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public String notify(HttpServletRequest request) throws IOException;
}
