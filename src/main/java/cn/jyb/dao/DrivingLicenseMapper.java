package cn.jyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.DrivingLicense;

public interface DrivingLicenseMapper {
	
    int deleteByPrimaryKey(Integer id);

    int insert(DrivingLicense record);

    int insertSelective(DrivingLicense record);

    DrivingLicense selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DrivingLicense record);

    int updateByPrimaryKey(DrivingLicense record);
    
    DrivingLicense findByUserId(Integer userId);
    
    List<DrivingLicense> listAll();
    
    List<DrivingLicense> listAllByStatus(@Param("drivingLicenseStatus")Integer drivingLicenseStatus);
}