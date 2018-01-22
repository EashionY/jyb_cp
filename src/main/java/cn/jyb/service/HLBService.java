package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.HLBOrder;

public interface HLBService {

	/**
	 * �洢����״̬�仯��map������״̬�仯ʱΪtrue
	 */
	public static Map<String,Boolean> changed = new HashMap<String, Boolean>();
	
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
	 * �鿴�۸���ϸ
	 * @param carType
	 * @param mileage
	 * @return
	 */
	public Map<String,Object> priceDetail(String carType,String mileage);
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
	/**
	 * �鿴���ж���������������
	 * @param carType
	 * @param orderType
	 * @param lon
	 * @param lat
	 * @param price �Ƿ񰴼۸�����1/0��
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String,Object> listOrders(String carType,Integer orderType,double lon,double lat,String price,Integer page,Integer pageSize);
	/**
	 * �鿴�����ı����붩��
	 * @param invited
	 * @return
	 */
	public List<Map<String,Object>> getInvites(Integer invited,Integer page,Integer pageSize);
	/**
	 * ���복���ӵ�
	 * @param hlbOrderNo
	 * @param invited
	 * @return
	 */
	public boolean orderInvite(String hlbOrderNo,Integer invited);
	/**
	 * Ϊ�˿��Ƽ��ó�
	 * @param userLon ����
	 * @param userLat γ��
	 * @param region ����
	 * @return
	 */
	public List<Map<String,Object>> recomendDriver(String userLon,String userLat,String region);
	/**
	 * �������������վ�������鿴����
	 * @param carType
	 * @param orderType
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> listOrdersByDistance(String carType,Integer orderType,double lon,double lat,Integer page,Integer pageSize);
	/**
	 * �鿴�ҵĶ���
	 * @param userId 
	 * @param orderStatus ����״̬
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listMyOrders(Integer userId,Integer orderStatus,Integer page,Integer pageSize);
	
	
}
