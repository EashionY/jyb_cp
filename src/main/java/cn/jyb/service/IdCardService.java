package cn.jyb.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public interface IdCardService {

	
	/**
	 * ������֤(ʵ����֤).
	 *
	 * @param request ����������userId��idcardNo��realname��sex��address
	 * @return 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws Exception the exception
	 */
	public boolean addIdCard(HttpServletRequest request) throws IOException, Exception;
	
	
}
