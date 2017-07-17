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
	 * �ϼ���Ʒ
	 * @param id
	 * @return
	 */
	public int onShelves(int id);
	
	/**
	 * �¼���Ʒ
	 * @param id
	 * @return
	 */
	public int offShelves(int id);
	
	/**
	 * ��Ʒ����ƹ�֮�����״̬��ʱ��
	 * @param id
	 * @return
	 */
	public int finishCloudBuy(int id);
	
	/**
	 * ��ȡȫ����Ʒ������
	 * @return
	 */
	public int getGoodsNum();
	
	/**
	 * ��ȡ��Ӧ״̬����Ʒ����
	 * @param goods_status
	 * @return
	 */
	public int getStatusGoodsNum(String goods_status);

	/**
	 * �Զ��������ϼ���Ʒ
	 * @param goods
	 */
	public void autoSave(Goods goods);
}
