package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.VehicleLicense;

public interface VehicleLicenseService {

    public boolean saveVehicleLicense(HttpServletRequest request) throws UnsupportedEncodingException;
	
	public Integer dealVehicleLicense(Integer id,Integer vehicleLicenseStatus);
	
	public List<VehicleLicense> listAll(Integer vehicleLicenseStatus);
	
}
