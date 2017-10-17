package cn.jyb.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.Driver;

public interface DriverService {
	/**
	 * 添加车主（成为车主）
	 * @param request 
	 * 传参：userId,carBrand,驾驶证与行驶证两张图片,driverName,carNo,driverIdcard,driverLicenseDate,carOwner,drivingLicenseDate
	 * @return 
	 * @throws IOException 
	 * @return 
	 */
	public boolean addDriver(HttpServletRequest request) throws IOException;
	
	/**
	 * 审核车主认证信息(后台管理系统)
	 * @param driverId
	 * @param driverStatus
	 * @return
	 */
	public Integer dealDriver(Integer driverId, Integer driverStatus);
	
	/**
	 * 补全车主信息（后台管理系统）
	 * @param driverId
	 * @param driverName
	 * @param carNo
	 * @param carBrand
	 * @return
	 */
	public boolean completeDriverInfo(Integer driverId,String driverName,String carNo,String carBrand);
	
	/**
	 * 分状态查找车主（后台管理系统）
	 * @param driverStatus
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> findAllDriver(Integer driverStatus,Integer page,Integer pageSize);
	
}
