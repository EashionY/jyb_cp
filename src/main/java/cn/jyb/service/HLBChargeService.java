package cn.jyb.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.HLBCharge;

public interface HLBChargeService {

	/**
	 * 保存一条收费标准
	 * @param request 参数：car,carIcon,carType,length,width,height,volume,capacity,within,inPrice,outPrice
	 * @return
	 * @throws IOException 
	 */
	public boolean saveCharge(HttpServletRequest request) throws IOException;
	/**
	 * 修改一条收费标准
	 * @param request 参数：id,car,carType,length,width,height,volume,capacity,within,inPrice,outPrice
	 * @return
	 */
	public HLBCharge updateCharge(HttpServletRequest request);
	/**
	 * 修改车辆图标
	 * @param request 参数：id，carIcon
	 * @return
	 * @throws IOException 
	 */
	public boolean updateCarIcon(HttpServletRequest request) throws IOException;
	/**
	 * 列出所有已启用的收费标准
	 * @return
	 */
	public List<HLBCharge> listCharge();
}
