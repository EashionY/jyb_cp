package cn.jyb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.jyb.dao.DrivingLicenseMapper;
import cn.jyb.entity.DrivingLicense;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.ImgpathException;
import cn.jyb.util.Upload;
@Service("drivingLicenseService")
public class DrivingLicenseServiceImpl implements DrivingLicenseService {

	@Resource
	private DrivingLicenseMapper drivingLicenseMapper;
	
	public boolean saveDrivingLicense(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		DrivingLicense license = drivingLicenseMapper.findByUserId(userId);
		List<String> path;
		try {
			path = Upload.uploadImg(request, ""+userId, "drivingLicense");
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new ImgpathException("驾驶证照片上传失败");
		}
		//驾驶证图片路径
		String pic = null;
		if(path != null && path.size() > 0){
			pic = path.get(0);
		}
		if(license == null){//初次认证
			license = new DrivingLicense();
			license.setUserId(userId);
			license.setDrivingLicensePic(pic);
			license.setDriverName(request.getParameter("driverName"));
			license.setLicenseNo(request.getParameter("licenseNo"));
			license.setIssueDate(request.getParameter("issueDate"));
			license.setDrivingClass(request.getParameter("drivingClass"));
			try {
				drivingLicenseMapper.insertSelective(license);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("数据库异常");
			}
		}else {//二次认证
			license.setDrivingLicensePic(pic);
			license.setDriverName(request.getParameter("driverName"));
			license.setLicenseNo(request.getParameter("licenseNo"));
			license.setIssueDate(request.getParameter("issueDate"));
			license.setDrivingClass(request.getParameter("drivingClass"));
			try {
				drivingLicenseMapper.updateByPrimaryKeySelective(license);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("数据库异常");
			}
		}
	}

	public Integer dealDrivingLicense(Integer id, Integer drivingLicenseStatus) {
		DrivingLicense license = new DrivingLicense();
		license.setId(id);
		license.setDrivingLicenseStatus(drivingLicenseStatus);
		if(drivingLicenseStatus == 1){
			license.setPasstime(new Date());
		}
		try {
			drivingLicenseMapper.updateByPrimaryKeySelective(license);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return drivingLicenseStatus;
	}

	public List<DrivingLicense> listAll(Integer drivingLicenseStatus) {
		if(drivingLicenseStatus == null){
			return drivingLicenseMapper.listAll();
		}else{
			return drivingLicenseMapper.listAllByStatus(drivingLicenseStatus);
		}
	}

}
