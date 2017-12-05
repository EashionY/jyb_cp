package cn.jyb.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WxpublicService {

	/**
	 * 静默授权，获取用户的openid
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @throws ServletException 
	 */
	public void check(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException;
	/**
	 * 创建微信公众号的菜单
	 * @param menu
	 * @return 微信公众号服务器返回的信息
	 */
	public String createMenu(String menu);
	/**
	 * 从session中获取openid
	 * @param request
	 * @return
	 */
	public String getOpenid(HttpServletRequest request);
}
