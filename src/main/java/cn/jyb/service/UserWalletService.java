package cn.jyb.service;

import cn.jyb.entity.UserWallet;

public interface UserWalletService {

	/**
	 * �鿴Ǯ�����
	 * @param userId
	 * @return
	 */
	public UserWallet checkBalance(Integer userId);
	
	
}
