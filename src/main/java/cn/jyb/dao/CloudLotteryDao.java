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
	 * ��ѯ�н���Ϣ(��̨)
	 * @return
	 */
	public List<Map<String,Object>> listLotteryInfo(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * ����н���Ŀ��
	 * @return
	 */
	public int getLotteryNum();
	
	
}
