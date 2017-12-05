package cn.jyb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.jyb.dao.VehicleLicenseMapper;
import cn.jyb.entity.VehicleLicense;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.ImgpathException;
import cn.jyb.exception.VehicleLicenseException;
import cn.jyb.util.Upload;
@Service("vehicleLicenseService")
public class VehicleLicenseServiceImpl implements VehicleLicenseService {

	@Resource
	private VehicleLicenseMapper vehicleLicenseMapper;
	
	public boolean saveVehicleLicense(HttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		Integer userId = Integer.parseInt(request.getParameter("userId"));
		//���ƺ�
		String vehicleNo = request.getParameter("vehicleNo");
		//��ֹͬһ��ʻ֤�ظ���֤
		List<VehicleLicense> list = vehicleLicenseMapper.findByVehicleNo(vehicleNo);
		for(VehicleLicense lic : list){
			if(lic.getVehicleLicenseStatus() == 1){
				throw new VehicleLicenseException("����ʻ֤���ڱ�ƽ̨��֤");
			}
		}
		List<String> path;
		try {
			path = Upload.uploadImg(request, ""+userId, "vehicleLicense");
		} catch (IOException e1) {
			e1.printStackTrace();
			throw new ImgpathException("��ʻ֤��Ƭ�ϴ�ʧ��");
		}
		//��ʻ֤ͼƬ·��
		String pic = null;
		if(path != null && path.size() > 0){
			pic = path.get(0);
		}
		VehicleLicense license = vehicleLicenseMapper.findByUserId(userId);
		if(license == null){//������֤
			license = new VehicleLicense();
			license.setUserId(userId);
			license.setVehicleLicensePic(pic);
			license.setVehicleNo(vehicleNo);
			license.setVehicleOwner(request.getParameter("vehicleOwner"));
			license.setVehicleBrand(request.getParameter("vehicleBrand"));
			license.setVehicleVin(request.getParameter("vehicleVin"));
			license.setEngineNo(request.getParameter("engineNo"));
			try {
				vehicleLicenseMapper.insertSelective(license);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("���ݿ��쳣");
			}
		}else{
			license.setVehicleLicensePic(pic);
			license.setVehicleNo(vehicleNo);
			license.setVehicleOwner(request.getParameter("vehicleOwner"));
			license.setVehicleBrand(request.getParameter("vehicleBrand"));
			license.setVehicleVin(request.getParameter("vehicleVin"));
			license.setEngineNo(request.getParameter("engineNo"));
			try {
				vehicleLicenseMapper.updateByPrimaryKeySelective(license);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("���ݿ��쳣");
			}
		}
	}

	public Integer dealVehicleLicense(Integer id, Integer vehicleLicenseStatus) {
		VehicleLicense license = vehicleLicenseMapper.selectByPrimaryKey(id);
		//ȡ�ó��ƺ�
		String vehicleNo = license.getVehicleNo();
		license.setVehicleLicenseStatus(vehicleLicenseStatus);
		if(vehicleLicenseStatus == 1){
			license.setPasstime(new Date());
			List<VehicleLicense> list = vehicleLicenseMapper.findByVehicleNo(vehicleNo);
			for(VehicleLicense lic : list){
				if(lic.getVehicleLicenseStatus() == 1){
					throw new VehicleLicenseException("����ʻ֤���ڱ�ƽ̨��֤");
				}
			}
		}
		try {
			vehicleLicenseMapper.updateByPrimaryKeySelective(license);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		return vehicleLicenseStatus;
	}

	public List<Map<String,Object>> listAll(Integer vehicleLicenseStatus, Integer page, Integer pageSize) {
		Integer offset = (page - 1) * pageSize;
		if(vehicleLicenseStatus == null) {
			return vehicleLicenseMapper.listAll(offset, pageSize);
		}else {
			return vehicleLicenseMapper.listAllByStatus(vehicleLicenseStatus, offset, pageSize);
		}
	}

}
