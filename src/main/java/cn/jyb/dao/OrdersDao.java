package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.Orders;

public interface OrdersDao {

	/**
	 * �����¼
	 * @param orders
	 */
	public void save(Orders orders);
	
	/**
	 * ͨ��������ɾ��������¼
	 * @param out_trade_no
	 * @return
	 */
	public int delete(@Param("out_trade_no")String out_trade_no);
	
	/**
	 * ͨ�������Ų��Ҷ�����¼
	 * @param out_trade_no
	 * @return
	 */
	public Orders findByNo(@Param("out_trade_no")String out_trade_no);
	
	/**
	 * ���½���״̬
	 * @param trade_status
	 * @return
	 */
	public int updateStatus(@Param("trade_status")String trade_status,@Param("out_trade_no")String out_trade_no);
	
	/**
	 * ��ɶ���
	 * @param out_trade_no
	 * @return
	 */
	public int finishOrder(@Param("out_trade_no")String out_trade_no);
	
	/**
	 * �鿴�����б�
	 * @param tradeStatus ����״̬
	 * @param orderType �������ͣ�1-��У������2-����������3-��ά�붩����
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listOrders(@Param("tradeStatus")String tradeStatus,@Param("orderType")String orderType,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
	
	
}
