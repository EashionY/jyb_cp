package cn.jyb.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

public interface IdCardService {

	
	/**
	 * 添加身份证(实名认证).
	 *
	 * @param request 包括参数：userId，idcardNo，realname，sex，address
	 * @return 
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws Exception the exception
	 */
	public boolean addIdCard(HttpServletRequest request) throws IOException, Exception;
	
	
}
