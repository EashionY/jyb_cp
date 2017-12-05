package cn.jyb.dao;

import java.util.List;
import java.util.Map;

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
    
    List<Map<String,Object>> listAll(@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
    
    List<Map<String,Object>> listAllByStatus(@Param("drivingLicenseStatus")Integer drivingLicenseStatus,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
    
    DrivingLicense findByLicNo(String licenseNo);
}