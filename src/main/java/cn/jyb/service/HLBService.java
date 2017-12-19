package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.HLBOrder;

public interface HLBService {

	/**
	 * �������������
	 * @param request
	 * @return ��������
	 * @throws UnsupportedEncodingException 
	 * @throws ParseException 
	 */
	public HLBOrder saveHLBOrder(HttpServletRequest request) throws UnsupportedEncodingException, ParseException;
	/**
	 * ���ܶ���
	 * @param hlbOrderNo ������������
	 * @param receiptId �������û�id
	 * @return
	 */
	public HLBOrder acceptHLBOrder(String hlbOrderNo, Integer receiptId);
	/**
	 * �鿴������ԤԼ�б�
	 * @param receiptId
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<HLBOrder> listBookOrders(Integer receiptId, Integer page, Integer pageSize);
	/**
	 * ���۳���
	 * @param hlbOrderNo
	 * @param evalStar
	 * @return
	 */
	public boolean evalDriver(String hlbOrderNo, Integer evalStar);
	/**
	 * ���۳˿�
	 * @param hlbOrderNo
	 * @param evalStar
	 * @return
	 */  
	public boolean evalPassenger(String hlbOrderNo, Integer evalStar);
	/**
	 * ȡ������
	 * @param hlbOrderNo
	 * @param userId ȡ���������û�id
	 * @return
	 */
	public boolean cancelOrder(String hlbOrderNo,Integer userId);
	/**
	 * �鿴��������ϸ��Ϣ
	 * @param receiptId �������û�id
	 * @return
	 */
	public Map<String,Object> getDriverInfo(Integer receiptId);
	/**
	 * ��ȡ�۸�
	 * @param carType ����
	 * @param mileage ���
	 * @return
	 */
	public String getPrice(String carType,String mileage);
	/**
	 * ��ȡ��������
	 * @param hlbOrderNo
	 * @return
	 */
	public HLBOrder getOrderInfo(String hlbOrderNo);
	/**
	 * ����ȷ�ϵ���˿͸���
	 * @param hlbOrderNo
	 * @return
	 */
	public boolean getClose(String hlbOrderNo);
	/**
	 * �˿�ȷ���ϳ�  
	 * @param hlbOrderNo
	 * @return
	 */
	public boolean aboard(String hlbOrderNo);
	/**
	 * ��ʼ�г�
	 * @param hlbOrderNo
	 * @return
	 */
	public boolean tripStart(String hlbOrderNo);
	/**
	 * �˿�ȷ�ϵ���
	 * @param hlbOrderNo
	 * @return
	 */
	public boolean pArrive(String hlbOrderNo);
	/**
	 * ����ȷ�ϵ���
	 * @param hlbOrderNo
	 * @return
	 */
	public boolean dArrive(String hlbOrderNo);
	/**
	 * ��ȡ�˿���Ϣ
	 * @param publishId
	 * @return
	 */
	public Map<String,Object> getPassengerInfo(Integer publishId);
}
