package cn.jyb.dao;

import cn.jyb.entity.UserWalletDraw;

public interface UserWalletDrawMapper {
    int deleteByPrimaryKey(Integer drawId);

    int insert(UserWalletDraw record);

    int insertSelective(UserWalletDraw record);

    UserWalletDraw selectByPrimaryKey(Integer drawId);

    int updateByPrimaryKeySelective(UserWalletDraw record);

    int updateByPrimaryKey(UserWalletDraw record);
}