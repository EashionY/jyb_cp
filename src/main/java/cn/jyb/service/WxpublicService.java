package cn.jyb.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WxpublicService {

	/**
	 * ��Ĭ��Ȩ����ȡ�û���openid
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void check(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException;
	/**
	 * ����΢�Ź��ںŵĲ˵�
	 * @param menu
	 * @return ΢�Ź��ںŷ��������ص���Ϣ
	 */
	public String createMenu(String menu);
	/**
	 * ��session�л�ȡopenid
	 * @param request
	 * @return
	 */
	public String getOpenid(HttpServletRequest request);
}
