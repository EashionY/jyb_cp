package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.DrivingLicense;

public interface DrivingLicenseService {

	public boolean saveDrivingLicense(HttpServletRequest request) throws UnsupportedEncodingException;
	
	public Integer dealDrivingLicense(Integer id,Integer drivingLicenseStatus);
	
	public List<DrivingLicense> listAll(Integer drivingLicenseStatus);
}
