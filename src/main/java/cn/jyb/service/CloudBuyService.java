package cn.jyb.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.Goods;

/**
 * �ƹ�ƽ̨
 * @author Eashion
 *
 */
public interface CloudBuyService {

	/**
	 * �����Ʒ(��̨)
	 * @param goodsName
	 * @param price
	 * @param total_needs
	 * @param goods_info
	 * @param period
	 * @param request
	 */
	public void addGoods(String goodsName,double price,int total_needs,String goods_info,int period,HttpServletRequest request);
	
	/**
	 * �г��������ϼ���Ʒ(goods_status=1)
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Goods> listAllGoods(int page,int pageSize);
	
	/**
	 * �û��ƹ���Ʒ
	 * @param user_id
	 * @param goods_id
	 * @param period
	 * @param buy_amount
	 * @return ���û�������Ʒ֮����ƹ���
	 */
	public List<Integer> buyGoods(int user_id,int goods_id,int period,int buy_amount);
	
	/**
	 * �����ƹ����
	 * @param goods_id
	 * @param period
	 * @return �н���ϸ��Ϣ
	 */
	public Map<String,Object> lottery(int goods_id,int period);
	
	/**
	 * �޸���Ʒ��Ϣ(��̨����ϵͳ)
	 * @param goodsName
	 * @param price
	 * @param total_needs
	 * @param goods_info
	 * @param period
	 * @return
	 */
	public boolean modifyGoodsInfo(int id,String goodsName,double price,int total_needs,String goods_info,int period);
	
	/**
	 * �޸���Ʒ����ͼƬ(��̨����ϵͳ)
	 * @param id ��Ʒid
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public boolean modifyGoodsCover(int id,HttpServletRequest request) throws IOException;
	
	/**
	 * �г�������Ʒ(��̨)
	 * @param goods_status
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> listGoodsByStatus(String goods_status,int page,int pageSize);
	
	/**
	 * �ϼ���Ʒ(��̨)
	 * @param id ��Ʒid
	 * @return �ϼ���Ʒ֮���״̬����ϼ�ʱ��
	 */
	public Map<String, Object> onShelves(int id);
	
	/**
	 * �¼���Ʒ(��̨)
	 * @param id ��Ʒid
	 * @return �¼���Ʒ֮���״̬����¼�ʱ��
	 */
	public Map<String, Object> offShelves(int id);
	
	/**
	 * �г������н���Ϣ(��̨)
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listLotteryInfo(int page,int pageSize);
	
	
}
