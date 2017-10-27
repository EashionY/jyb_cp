package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.ShippingAddr;

/**
 * The Interface ShippingAddrService.
 */
public interface ShippingAddrService {

	/**
	 * �г����е��ջ���ַ.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	public List<ShippingAddr> listAddr(Integer userId);
	
	/**
	 * �����ջ���ַ.
	 *
	 * @param request ������userId��name��phone��addrDetail��asDefault
	 * @return true, if successful
	 * @throws UnsupportedEncodingException 
	 */
	public boolean saveAddr(HttpServletRequest request) throws UnsupportedEncodingException;
	
	/**
	 * ɾ���ջ���ַ.
	 *
	 * @param addrId the addr id
	 * @return true, if successful
	 */
	public boolean deleteAddr(Integer addrId);
	
	/**
	 * �޸��ջ���ַ.
	 *
	 * @param request ������addrId��userId��name��phone��addrDetail��asDefault
	 * @return true, if successful
	 * @throws UnsupportedEncodingException 
	 */
	public Boolean updateAddr(HttpServletRequest request) throws UnsupportedEncodingException;
	
}
