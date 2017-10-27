package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.jyb.dao.ShippingAddrMapper;
import cn.jyb.entity.ShippingAddr;
import cn.jyb.exception.DataBaseException;
@Service("shippingAddrService")
public class ShippingAddrServiceImpl implements ShippingAddrService {

	@Resource
	private ShippingAddrMapper shippingAddrMapper;

	public List<ShippingAddr> listAddr(Integer userId) {
		return shippingAddrMapper.listAddr(userId);
	}

	public boolean saveAddr(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		ShippingAddr addr = new ShippingAddr();
		addr.setAddrDetail(request.getParameter("addrDetail"));
		//获取默认地址
		Integer asDefault = Integer.parseInt(request.getParameter("asDefault"));
		//若新增的当前地址为默认，则移除之前的默认地址
		if(asDefault == 1){
			try {
				shippingAddrMapper.removeDefault(userId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("数据库异常");
			}
		}
		addr.setAsDefault(asDefault);
		addr.setReceiverName(request.getParameter("name"));
		addr.setReceiverPhone(request.getParameter("phone"));
		addr.setUserId(userId);
		try {
			shippingAddrMapper.insertSelective(addr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return true;
	}

	public boolean deleteAddr(Integer addrId) {
		try {
			shippingAddrMapper.deleteByPrimaryKey(addrId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return true;
	}

	public Boolean updateAddr(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Integer asDefault = Integer.parseInt(request.getParameter("asDefault"));
		if(asDefault == 1) {
			try {
				shippingAddrMapper.removeDefault(userId);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("数据库异常");
			}
		}
		Integer addrId = Integer.parseInt(request.getParameter("addrId"));
		ShippingAddr addr = new ShippingAddr();
		addr.setAddrId(addrId);
		addr.setUserId(userId);
		addr.setAsDefault(asDefault);
		addr.setAddrDetail(request.getParameter("addrDetail"));
		addr.setReceiverName(request.getParameter("name"));
		addr.setReceiverPhone(request.getParameter("phone"));
		try {
			shippingAddrMapper.updateByPrimaryKeySelective(addr);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return true;
	}

}
