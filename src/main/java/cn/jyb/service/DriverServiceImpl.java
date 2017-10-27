package cn.jyb.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.jyb.dao.DriverMapper;
import cn.jyb.entity.Driver;
import cn.jyb.exception.DriverException;
import cn.jyb.util.Upload;

@Service("driverService")
public class DriverServiceImpl implements DriverService {
	
	@Resource
	private DriverMapper driverMapper;

	public boolean addDriver(HttpServletRequest request) throws IOException {
		request.setCharacterEncoding("UTF-8");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		Driver driver = driverMapper.findByUserId(userId);
		if(driver != null){
			throw new DriverException("该车主已存在");
		}
		List<String> paths = Upload.uploadImg(request, "Driver", ""+userId);
		if(paths.size() < 2){
			throw new DriverException("证件不齐全");
		}
		//驾驶证
		String driverLicensePic = paths.get(0);
		//行驶证		
		String drivingLicensePic = paths.get(1);
		driver = new Driver();
		driver.setUserId(userId);
		driver.setCarBrand(request.getParameter("carBrand"));
		driver.setDriverLicensePic(driverLicensePic);
		driver.setDrivingLicensePic(drivingLicensePic);
		driver.setDriverName(request.getParameter("driverName"));
		driver.setCarNo(request.getParameter("carNo"));
		driver.setDriverIdcard(request.getParameter("driverIdcard"));
		driver.setDriverLicenseDate(request.getParameter("driverLicenseDate"));
		driver.setDrivingLicenseDate(request.getParameter("drivingLicenseDate"));
		driver.setCarOwner(request.getParameter("carOwner"));
		return driverMapper.insertSelective(driver);
	}

	public Integer dealDriver(Integer driverId, Integer driverStatus) {
		Driver driver = new Driver();
		driver.setDriverId(driverId);
		driver.setDriverStatus(driverStatus);
		driverMapper.updateByPrimaryKeySelective(driver);
		return driverStatus;
	}

	public List<Map<String,Object>> findAllDriver(Integer driverStatus, Integer page, Integer pageSize) {
		Integer offset = (page-1)*pageSize;
		return driverMapper.findAllDriver(driverStatus, offset, pageSize);
	}
	
	
	
	

}
