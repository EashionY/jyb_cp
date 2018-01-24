package cn.jyb.service;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.UserWalletDetailMapper;
import cn.jyb.dao.UserWalletMapper;
import cn.jyb.entity.UserWallet;
import cn.jyb.entity.UserWalletDetail;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.WalletException;
@Service("userWalletService")
public class UserWalletServiceImpl implements UserWalletService {

	@Resource
	private UserWalletMapper userWalletMapper;
	@Resource
	private UserWalletDetailMapper userWalletDetailMapper;
	
	
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
				throw new DataBaseException("数据库异常");
			}
		}
		return wallet;
	}

	@Override
	public boolean add(Integer userId, BigDecimal amount) {
		UserWallet wallet = userWalletMapper.selectByPrimaryKey(userId);
		if(wallet == null){
			wallet = new UserWallet();
			wallet.setUserId(userId);
			wallet.setBalance(amount);
			try {
				userWalletMapper.insert(wallet);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("数据库异常");
			}
		}else{
			BigDecimal balance = wallet.getBalance();
			wallet.setBalance(balance.add(amount));
			try {
				userWalletMapper.updateByPrimaryKey(wallet);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("数据库异常");
			}
		}
		//增加钱包明细条目
		UserWalletDetail walletDetail = new UserWalletDetail();
		walletDetail.setUserId(userId);
		walletDetail.setAmount(amount);
		walletDetail.setTradeTime(new Date());
		walletDetail.setType(1);
		try {
			userWalletDetailMapper.insert(walletDetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return true;
	}

	@Override
	public boolean subtract(Integer userId, BigDecimal amount, Integer type) {
		UserWallet wallet = userWalletMapper.selectByPrimaryKey(userId);
		if(wallet == null){
			throw new WalletException("余额不足");
		}else{
			BigDecimal balance = wallet.getBalance();
			if(balance.compareTo(amount) == -1){//余额小于支出金额
				throw new WalletException("余额不足");
			}else{
				balance = balance.subtract(amount);
				wallet.setBalance(balance);
				try {
					userWalletMapper.updateByPrimaryKey(wallet);
				} catch (Exception e) {
					e.printStackTrace();
					throw new DataBaseException("数据库异常");
				}
			}
		}
		//增加钱包明细条目
		UserWalletDetail walletDetail = new UserWalletDetail();
		walletDetail.setUserId(userId);
		walletDetail.setAmount(amount);
		walletDetail.setTradeTime(new Date());
		walletDetail.setType(type);
		try {
			userWalletDetailMapper.insert(walletDetail);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return true;
	}

	
}
