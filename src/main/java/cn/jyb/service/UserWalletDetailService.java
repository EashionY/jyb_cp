package cn.jyb.service;

import java.util.Map;

public interface UserWalletDetailService {

	/**
	 * �鿴Ǯ����֧��ϸ
	 * @param userId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String,Object> listDetail(Integer userId,Integer page,Integer pageSize);
	
	
}
