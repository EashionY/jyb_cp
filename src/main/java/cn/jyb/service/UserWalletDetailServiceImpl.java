package cn.jyb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.UserWalletDetailMapper;
import cn.jyb.entity.UserWalletDetail;
@Service("userWalletDetailService")
public class UserWalletDetailServiceImpl implements UserWalletDetailService {

	@Resource
	private UserWalletDetailMapper userWalletDetailMapper;
	
	@Override
	public Map<String, Object> listDetail(Integer userId, Integer page, Integer pageSize) {
		int detailNum = userWalletDetailMapper.getDetailNum(userId);
		Integer offset = (page - 1) * pageSize;
		List<UserWalletDetail> list = userWalletDetailMapper.listDetail(userId, offset, pageSize);
		Map<String, Object> result = new HashMap<>();
		result.put("detailNum", detailNum);
		result.put("details", list);
		return result;
	}

	
}
