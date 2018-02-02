package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface DrivingLicenseService {

	/**
	 * 保存驾驶证
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public boolean saveDrivingLicense(HttpServletRequest request) throws UnsupportedEncodingException;
	/**
	 * 审核处理驾驶证
	 * @param id
	 * @param userId
	 * @param drivingLicenseStatus
	 * @return
	 */
	public Integer dealDrivingLicense(Integer id,Integer userId,Integer drivingLicenseStatus);
	/**
	 * 查看所有的驾驶证
	 * @param drivingLicenseStatus
	 * @param page
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> listAll(Integer drivingLicenseStatus, Integer page, Integer pageSize);
}
