package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface DrivingLicenseService {

	public boolean saveDrivingLicense(HttpServletRequest request) throws UnsupportedEncodingException;
	
	public Integer dealDrivingLicense(Integer id,Integer drivingLicenseStatus);
	
	List<Map<String, Object>> listAll(Integer drivingLicenseStatus, Integer page, Integer pageSize);
}
