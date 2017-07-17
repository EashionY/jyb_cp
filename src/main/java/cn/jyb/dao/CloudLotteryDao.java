package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.CloudLottery;

public interface CloudLotteryDao {

	public void save(CloudLottery cloudLottery);
	
	public int delete(int id);
	
	public CloudLottery findById(int goods_id);
	
	/**
	 * 查询中奖信息(后台)
	 * @return
	 */
	public List<Map<String,Object>> listLotteryInfo(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * 获得中奖条目数
	 * @return
	 */
	public int getLotteryNum();
	
	
}
