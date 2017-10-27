package cn.jyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.ShippingAddr;

public interface ShippingAddrMapper {
    int deleteByPrimaryKey(Integer addrId);

    int insert(ShippingAddr record);

    int insertSelective(ShippingAddr record);

    ShippingAddr selectByPrimaryKey(Integer addrId);

    int updateByPrimaryKeySelective(ShippingAddr record);

    int updateByPrimaryKey(ShippingAddr record);
    
    List<ShippingAddr> listAddr(Integer userId);
    
    int removeDefault(@Param("userId")Integer userId);
}