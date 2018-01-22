package cn.jyb.dao;

import cn.jyb.entity.UserWallet;

public interface UserWalletMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(UserWallet record);

    int insertSelective(UserWallet record);

    UserWallet selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(UserWallet record);

    int updateByPrimaryKey(UserWallet record);
    
    
}