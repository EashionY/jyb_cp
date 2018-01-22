package cn.jyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.UserWalletDetail;

public interface UserWalletDetailMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(UserWalletDetail record);

    int insertSelective(UserWalletDetail record);

    UserWalletDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserWalletDetail record);

    int updateByPrimaryKey(UserWalletDetail record);
    /**
     * 获取该用户的明细总条数
     * @param userId
     * @return
     */
    int getDetailNum(Integer userId);
    /**
     * 查看该用户的钱包明细
     * @param userId
     * @param offset
     * @param pageSize
     * @return
     */
    List<UserWalletDetail> listDetail(@Param("userId")Integer userId,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
    
    
}