package cn.jyb.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.jyb.dao.HLBChargeMapper;
import cn.jyb.entity.HLBCharge;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.HLBOrderException;
import cn.jyb.util.Upload;

@Service("hlbChargeService")
public class HLBChargeServiceImpl implements HLBChargeService {

	@Resource
	private HLBChargeMapper hlbChargeMapper;
	
	@Override
	public boolean saveCharge(HttpServletRequest request) throws IOException {
		String carIcon = "";
		/*
		 * 上传车辆图标
		 */
		List<String> paths = Upload.uploadImg(request, "HLB", "chargeStandard");
		if(paths != null && !paths.isEmpty()){
			carIcon = paths.get(0);
		}
		HLBCharge charge = new HLBCharge();
		charge.setCar(request.getParameter("car"));
		charge.setCarType(request.getParameter("carType"));
		charge.setCarIcon(carIcon);
		charge.setLength(Double.parseDouble(request.getParameter("length")));
		charge.setWidth(Double.parseDouble(request.getParameter("width")));
		charge.setHeight(Double.parseDouble(request.getParameter("height")));
		charge.setVolume(Double.parseDouble(request.getParameter("volume")));
		charge.setCapacity(Double.parseDouble(request.getParameter("capacity")));
		charge.setWithin(Integer.parseInt(request.getParameter("within")));
		charge.setInPrice(new BigDecimal(request.getParameter("inPrice")).setScale(2));
		charge.setOutPrice(new BigDecimal(request.getParameter("outPrice")).setScale(2));
		charge.setState("1");
		try {
			hlbChargeMapper.insert(charge);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return true;
	}

	@Override
	public HLBCharge updateCharge(HttpServletRequest request) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		HLBCharge charge = hlbChargeMapper.selectByPrimaryKey(id);
		if(charge == null){
			throw new HLBOrderException("错误的id");
		}
		String car = request.getParameter("car");
		if(car != null && !car.trim().isEmpty()){
			charge.setCar(car);
		}
		String carType = request.getParameter("carType");
		if(carType != null && !carType.trim().isEmpty()){
			charge.setCarType(carType);
		}
		charge.setLength(Double.parseDouble(request.getParameter("length")));
		charge.setWidth(Double.parseDouble(request.getParameter("width")));
		charge.setHeight(Double.parseDouble(request.getParameter("height")));
		charge.setVolume(Double.parseDouble(request.getParameter("volume")));
		charge.setCapacity(Double.parseDouble(request.getParameter("capacity")));
		charge.setWithin(Integer.parseInt(request.getParameter("within")));
		charge.setInPrice(new BigDecimal(request.getParameter("inPrice")).setScale(2));
		charge.setOutPrice(new BigDecimal(request.getParameter("outPrice")).setScale(2));
		String state = request.getParameter("state");
		if (state != null && !state.trim().isEmpty()) {
			charge.setState(state);
		}
		try {
			hlbChargeMapper.updateByPrimaryKeySelective(charge);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return hlbChargeMapper.selectByPrimaryKey(id);
	}

	@Override
	public boolean updateCarIcon(HttpServletRequest request) throws IOException {
		String carIcon = null;
		/*
		 * 上传车辆图标
		 */
		List<String> paths = Upload.uploadImg(request, "HLB", "chargeStandard");
		if(paths != null && !paths.isEmpty()){
			carIcon = paths.get(0);
		}
		HLBCharge charge = new HLBCharge();
		charge.setId(Integer.parseInt(request.getParameter("id")));
		charge.setCarIcon(carIcon);
		try {
			hlbChargeMapper.updateByPrimaryKeySelective(charge);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return true;
	}

	@Override
	public List<HLBCharge> listCharge() {
		return hlbChargeMapper.listCharge();
	}

	
}
