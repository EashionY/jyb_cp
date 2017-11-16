package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.Orders;

public interface OrdersDao {

	/**
	 * 保存记录
	 * @param orders
	 */
	public void save(Orders orders);
	
	/**
	 * 通过订单号删除订单记录
	 * @param out_trade_no
	 * @return
	 */
	public int delete(@Param("out_trade_no")String out_trade_no);
	
	/**
	 * 通过订单号查找订单记录
	 * @param out_trade_no
	 * @return
	 */
	public Orders findByNo(@Param("out_trade_no")String out_trade_no);
	
	/**
	 * 更新交易状态
	 * @param trade_status
	 * @return
	 */
	public int updateStatus(@Param("trade_status")String trade_status,@Param("out_trade_no")String out_trade_no);
	
	/**
	 * 完成订单
	 * @param out_trade_no
	 * @return
	 */
	public int finishOrder(@Param("out_trade_no")String out_trade_no);
	
	/**
	 * 查看订单列表
	 * @param tradeStatus 订单状态
	 * @param orderType 订单类型（1-驾校订单，2-教练订单，3-二维码订单）
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listOrders(@Param("tradeStatus")String tradeStatus,@Param("orderType")String orderType,@Param("offset")Integer offset,@Param("pageSize")Integer pageSize);
	
	
}
