package cn.jyb.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.Goods;

public interface GoodsDao {

	public void save(Goods goods);
	
	public int delete(int id);
	
	public int updateNeeds(@Param("rest_needs")int rest_needs,@Param("id")int id);
	
	public Goods findById(int id);
	
	public List<Goods> findAll(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	public List<Goods> findGoodsByStatus(@Param("goods_status")String goods_status,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	public int modifyGoodsInfo(Goods goods);
	
	public int modifyGoodsCover(@Param("cover")String cover,@Param("id")int id);
	
	/**
	 * 上架商品
	 * @param id
	 * @return
	 */
	public int onShelves(int id);
	
	/**
	 * 下架商品
	 * @param id
	 * @return
	 */
	public int offShelves(int id);
	
	/**
	 * 商品完成云购之后更新状态和时间
	 * @param id
	 * @return
	 */
	public int finishCloudBuy(int id);
	
	/**
	 * 获取全部商品的数量
	 * @return
	 */
	public int getGoodsNum();
	
	/**
	 * 获取对应状态的商品数量
	 * @param goods_status
	 * @return
	 */
	public int getStatusGoodsNum(String goods_status);

	/**
	 * 自动新增并上架商品
	 * @param goods
	 */
	public void autoSave(Goods goods);
}
