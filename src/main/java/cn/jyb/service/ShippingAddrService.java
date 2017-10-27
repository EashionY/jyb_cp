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
	 * 列出所有的收货地址.
	 *
	 * @param userId the user id
	 * @return the list
	 */
	public List<ShippingAddr> listAddr(Integer userId);
	
	/**
	 * 保存收货地址.
	 *
	 * @param request 参数：userId，name，phone，addrDetail，asDefault
	 * @return true, if successful
	 * @throws UnsupportedEncodingException 
	 */
	public boolean saveAddr(HttpServletRequest request) throws UnsupportedEncodingException;
	
	/**
	 * 删除收货地址.
	 *
	 * @param addrId the addr id
	 * @return true, if successful
	 */
	public boolean deleteAddr(Integer addrId);
	
	/**
	 * 修改收货地址.
	 *
	 * @param request 参数：addrId，userId，name，phone，addrDetail，asDefault
	 * @return true, if successful
	 * @throws UnsupportedEncodingException 
	 */
	public Boolean updateAddr(HttpServletRequest request) throws UnsupportedEncodingException;
	
}
