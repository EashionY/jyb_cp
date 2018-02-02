package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface DrivingLicenseService {

	/**
	 * �����ʻ֤
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public boolean saveDrivingLicense(HttpServletRequest request) throws UnsupportedEncodingException;
	/**
	 * ��˴����ʻ֤
	 * @param id
	 * @param userId
	 * @param drivingLicenseStatus
	 * @return
	 */
	public Integer dealDrivingLicense(Integer id,Integer userId,Integer drivingLicenseStatus);
	/**
	 * �鿴���еļ�ʻ֤
	 * @param drivingLicenseStatus
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> listAll(Integer drivingLicenseStatus, Integer page, Integer pageSize);
}
