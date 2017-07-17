package cn.jyb.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.CloudBuy;

public interface CloudBuyDao {

	public void save(CloudBuy cloudBuy);
	
	public int delete(int id);
	
	public CloudBuy findById(int id);
	
	public List<Integer> listCodes(@Param("goods_id")int goods_id,@Param("period")int period);
	
	public List<Integer> listUserCodes(@Param("user_id")int user_id,@Param("goods_id")int goods_id,@Param("period")int period);
	
	public List<Date> listTime(@Param("goods_id")int goods_id,@Param("period")int period);

	public CloudBuy findWinner(@Param("lotteryCode")int lotteryCode, @Param("goods_id")int goods_id, @Param("period")int period);

	public int getCounts(@Param("user_id")int user_id, @Param("goods_id")int goods_id, @Param("period")int period);
	
	
}
