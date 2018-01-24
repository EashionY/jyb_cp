package cn.jyb.service;

import java.math.BigDecimal;

import cn.jyb.entity.UserWallet;

public interface UserWalletService {

	/**
	 * 查看钱包余额
	 * @param userId
	 * @return
	 */
	public UserWallet checkBalance(Integer userId);
	/**
	 * 钱包加钱
	 * @param userId
	 * @param amount
	 * @return
	 */
	public boolean add(Integer userId,BigDecimal amount);
	/**
	 * 钱包减钱
	 * @param userId
	 * @param amount
	 * @param type 支出类型
	 * @return
	 */
	public boolean subtract(Integer userId,BigDecimal amount,Integer type);
	
}
