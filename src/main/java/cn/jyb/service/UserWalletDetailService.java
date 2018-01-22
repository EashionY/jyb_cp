package cn.jyb.service;

import java.util.Map;

public interface UserWalletDetailService {

	/**
	 * 查看钱包明细
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String,Object> listDetail(Integer userId,Integer page,Integer pageSize);
	
	
}
