package cn.jyb.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface DriverService {
	/**
	 * ��ӳ�����������֤��
	 * @param request 
	 * ���Σ�userId,carBrand,��ʻ֤����ʻ֤����ͼƬ,driverName,carNo,driverIdcard,driverLicenseDate,carOwner,drivingLicenseDate
	 * @return 
	 * @throws IOException 
	 * @return 
	 */
	public boolean addDriver(HttpServletRequest request) throws IOException;
	
	/**
	 * ��˳�����֤��Ϣ(��̨����ϵͳ)
	 * @param driverId
	 * @param driverStatus
	 * @return
	 */
	public Integer dealDriver(Integer driverId, Integer driverStatus);
	
	/**
	 * ��״̬���ҳ�������̨����ϵͳ��
	 * @param driverStatus
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> findAllDriver(Integer driverStatus,Integer page,Integer pageSize);
	
}
