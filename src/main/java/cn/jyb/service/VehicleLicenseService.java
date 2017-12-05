package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface VehicleLicenseService {

    public boolean saveVehicleLicense(HttpServletRequest request) throws UnsupportedEncodingException;
	
	public Integer dealVehicleLicense(Integer id,Integer vehicleLicenseStatus);
	
	public List<Map<String,Object>> listAll(Integer vehicleLicenseStatus, Integer page, Integer pageSize);
	
}
