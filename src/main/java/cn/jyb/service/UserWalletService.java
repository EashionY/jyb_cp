package cn.jyb.service;

import java.math.BigDecimal;

import cn.jyb.entity.UserWallet;

public interface UserWalletService {

	/**
	 * �鿴Ǯ�����
	 * @param userId
	 * @return
	 */
	public UserWallet checkBalance(Integer userId);
	/**
	 * Ǯ����Ǯ
	 * @param userId
	 * @param amount
	 * @return
	 */
	public boolean add(Integer userId,BigDecimal amount);
	/**
	 * Ǯ����Ǯ
	 * @param userId
	 * @param amount
	 * @param type ֧������
	 * @return
	 */
	public boolean subtract(Integer userId,BigDecimal amount,Integer type);
	
}
