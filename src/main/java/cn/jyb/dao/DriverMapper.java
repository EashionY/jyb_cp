package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.Driver;

public interface DriverMapper {
    
	boolean deleteByPrimaryKey(Integer driverId);

    boolean insert(Driver record);

    boolean insertSelective(Driver record);

    Driver selectByPrimaryKey(Integer driverId);

    boolean updateByPrimaryKeySelective(Driver record);

    boolean updateByPrimaryKey(Driver record);
    
    Driver findByUserId(Integer userId);

	List<Map<String, Object>> findAllDriver(@Param("driverStatus")Integer driverStatus,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
}