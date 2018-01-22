package cn.jyb.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.UserWalletDetailMapper;
@Service("userWalletDetailService")
public class UserWalletDetailServiceImpl implements UserWalletDetailService {

	@Resource
	private UserWalletDetailMapper userWalletDetailMapper;
	
	@Override
	public Map<String, Object> listDetail(Integer userId, Integer page, Integer pageSize) {
		
		
		
		return null;
	}

}
