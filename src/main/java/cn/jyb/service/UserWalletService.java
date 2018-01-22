package cn.jyb.service;

import cn.jyb.entity.UserWallet;

public interface UserWalletService {

	/**
	 * ²é¿´Ç®°üÓà¶î
	 * @param userId
	 * @return
	 */
	public UserWallet checkBalance(Integer userId);
	
	
}
