package cn.jyb.dao;

import java.util.List;
import java.util.Map;

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
    
    List<Map<String,Object>> listAll(@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
    
    List<Map<String,Object>> listAllByStatus(@Param("vehicleLicenseStatus")Integer vehicleLicenseStatus,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);

    List<VehicleLicense> findByVehicleNo(String vehicleNo);
}