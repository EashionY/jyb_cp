package cn.jyb.dao;

import cn.jyb.entity.DriverOrder;

public interface DriverOrderMapper {
	
    int deleteByPrimaryKey(Integer driverOrderId);

    int insert(DriverOrder record);

    int insertSelective(DriverOrder record);

    DriverOrder selectByPrimaryKey(Integer driverOrderId);

    int updateByPrimaryKeySelective(DriverOrder record);

    int updateByPrimaryKey(DriverOrder record);
    
    
}