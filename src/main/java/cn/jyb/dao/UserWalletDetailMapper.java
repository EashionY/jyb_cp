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
     * ��ȡ���û�����ϸ������
     * @param userId
     * @return
     */
    int getDetailNum(Integer userId);
    /**
     * �鿴���û���Ǯ����ϸ
     * @param userId
     * @param offset
     * @param pageSize
     * @return
     */
    List<UserWalletDetail> listDetail(@Param("userId")Integer userId,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
    
    
}