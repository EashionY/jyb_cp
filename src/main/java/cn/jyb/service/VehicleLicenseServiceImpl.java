package cn.jyb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.jyb.dao.VehicleLicenseMapper;
import cn.jyb.entity.VehicleLicense;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.ImgpathException;
import cn.jyb.util.Upload;
@Service("vehicleLicenseService")
public class VehicleLicenseServiceImpl implements VehicleLicenseService {

	@Resource
	private VehicleLicenseMapper vehicleLicenseMapper;
	
	public boolean saveVehicleLicense(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		VehicleLicense license = vehicleLicenseMapper.findByUserId(userId);
		List<String> path;
		try {
			path = Upload.uploadImg(request, ""+userId, "vehicleLicense");
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new ImgpathException("行驶证照片上传失败");
		}
		//驾驶证图片路径
		String pic = null;
		if(path != null && path.size() > 0){
			pic = path.get(0);
		}
		if(license == null){//初次认证
			license = new VehicleLicense();
			license.setUserId(userId);
			license.setVehicleLicensePic(pic);
			license.setVehicleNo(request.getParameter("vehicleNo"));
			license.setVehicleOwner(request.getParameter("vehicleOwner"));
			license.setVehicleBrand(request.getParameter("vehicleBrand"));
			license.setVehicleVin(request.getParameter("vehicleVin"));
			license.setEngineNo(request.getParameter("engineNo"));
			try {
				vehicleLicenseMapper.insertSelective(license);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("数据库异常");
			}
		}else {//二次认证
			license.setVehicleLicensePic(pic);
			license.setVehicleNo(request.getParameter("vehicleNo"));
			license.setVehicleOwner(request.getParameter("vehicleOwner"));
			license.setVehicleBrand(request.getParameter("vehicleBrand"));
			license.setVehicleVin(request.getParameter("vehicleVin"));
			license.setEngineNo(request.getParameter("engineNo"));
			try {
				vehicleLicenseMapper.updateByPrimaryKeySelective(license);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("数据库异常");
			}
		}
	}

	public Integer dealVehicleLicense(Integer id, Integer vehicleLicenseStatus) {
		VehicleLicense license = new VehicleLicense();
		license.setId(id);
		license.setVehicleLicenseStatus(vehicleLicenseStatus);
		if(vehicleLicenseStatus == 1){
			license.setPasstime(new Date());
		}
		try {
			vehicleLicenseMapper.updateByPrimaryKeySelective(license);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return vehicleLicenseStatus;
	}

	public List<VehicleLicense> listAll(Integer vehicleLicenseStatus) {
		if(vehicleLicenseStatus == null) {
			return vehicleLicenseMapper.listAll();
		}else {
			return vehicleLicenseMapper.listAllByStatus(vehicleLicenseStatus);
		}
	}

}
