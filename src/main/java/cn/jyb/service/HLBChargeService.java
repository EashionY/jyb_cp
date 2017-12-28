package cn.jyb.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.HLBCharge;

public interface HLBChargeService {

	/**
	 * ����һ���շѱ�׼
	 * @param request ������car,carIcon,carType,length,width,height,volume,capacity,within,inPrice,outPrice
	 * @return
	 * @throws IOException 
	 */
	public boolean saveCharge(HttpServletRequest request) throws IOException;
	/**
	 * �޸�һ���շѱ�׼
	 * @param request ������id,car,carType,length,width,height,volume,capacity,within,inPrice,outPrice
	 * @return
	 */
	public HLBCharge updateCharge(HttpServletRequest request);
	/**
	 * �޸ĳ���ͼ��
	 * @param request ������id��carIcon
	 * @return
	 * @throws IOException 
	 */
	public boolean updateCarIcon(HttpServletRequest request) throws IOException;
	/**
	 * �г����������õ��շѱ�׼
	 * @return
	 */
	public List<HLBCharge> listCharge();
}
