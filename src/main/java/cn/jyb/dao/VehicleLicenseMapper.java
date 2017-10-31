package cn.jyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.VehicleLicense;

public interface VehicleLicenseMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(VehicleLicense record);

    int insertSelective(VehicleLicense record);

    VehicleLicense selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VehicleLicense record);

    int updateByPrimaryKey(VehicleLicense record);
    
    VehicleLicense findByUserId(Integer userId);
    
    List<VehicleLicense> listAll();
    
    List<VehicleLicense> listAllByStatus(@Param("vehicleLicenseStatus")Integer vehicleLicenseStatus);
}