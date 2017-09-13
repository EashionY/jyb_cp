package cn.jyb.dao;

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
	
	
}
