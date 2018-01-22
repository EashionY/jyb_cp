package cn.jyb.service;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.UserWalletMapper;
import cn.jyb.entity.UserWallet;
import cn.jyb.exception.DataBaseException;
@Service("userWalletService")
public class UserWalletServiceImpl implements UserWalletService {

	@Resource
	private UserWalletMapper userWalletMapper;
	
	@Override
	public UserWallet checkBalance(Integer userId) {
		UserWallet wallet = userWalletMapper.selectByPrimaryKey(userId);
		if(wallet == null){
			wallet = new UserWallet();
			wallet.setUserId(userId);
			wallet.setBalance(new BigDecimal("0.00"));
			try {
				userWalletMapper.insert(wallet);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("Êý¾Ý¿âÒì³£");
			}
		}
		return wallet;
	}

	
}
