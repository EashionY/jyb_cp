package cn.jyb.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jyb.dao.CloudBuyDao;
import cn.jyb.dao.CloudLotteryDao;
import cn.jyb.dao.GoodsDao;
import cn.jyb.dao.UserDao;
import cn.jyb.entity.CloudBuy;
import cn.jyb.entity.CloudLottery;
import cn.jyb.entity.Goods;
import cn.jyb.entity.User;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.GoodsException;
import cn.jyb.exception.ImgpathException;
import cn.jyb.exception.OutOfRangeException;
import cn.jyb.util.Upload;

@Service("cloudBuyService")
@Transactional
public class CloudBuyServiceImpl implements CloudBuyService {

	@Resource
	private GoodsDao goodsDao;
	
	@Resource
	private CloudBuyDao cloudBuyDao;
	
	@Resource
	private CloudLotteryDao cloudLotteryDao;
	
	@Resource
	private UserDao userDao;
	
	public void addGoods(String goodsName, double price, int total_needs, String goods_info, int period,
			HttpServletRequest request) {
		Goods goods = new Goods();
		goods.setGoods_info(goods_info);
		goods.setName(goodsName);
		goods.setPeriod(period);
		goods.setTotal_needs(total_needs);
		goods.setPrice(price);
		String folder1 = "BackgroundManagement";
		String folder2 = "GoodsCover";
		List<String> paths = new ArrayList<String>();
		try {
			paths = Upload.uploadImg(request, folder1, folder2);
		} catch (IOException e) {
			throw new ImgpathException("ͼƬ�ϴ�ʧ��");
		}
		goods.setCover(paths.isEmpty()?"":paths.get(0));
		try {
			goodsDao.save(goods);
		} catch (Exception e) {
			throw new DataBaseException("��Ʒ���ʧ��");
		}
	}

	public List<Goods> listAllGoods(int page, int pageSize) {
		int offset = (page-1)*pageSize;
		List<Goods> list;
		try {
			list = goodsDao.findGoodsByStatus("1", offset, pageSize);
		} catch (Exception e) {
			throw new DataBaseException("��ȡ��Ʒ��Ϣʧ��");
		}
		return list;
	}

	public List<Integer> buyGoods(int user_id, int goods_id, int period, int buy_amount) {
		CloudBuy cloudBuy = new CloudBuy();
		//Ϊ��ÿ�β���һ���ƹ��룬�趨ÿ�ι������Ϊ 1
		cloudBuy.setBuy_amount(1);
		cloudBuy.setUser_id(user_id);
		//��ǰ��������Ʒ
		Goods goods;
		try {
			goods = goodsDao.findById(goods_id);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ��쳣");
		}
		if(goods == null){
			throw new GoodsException("�޶�Ӧid����Ʒ");
		}
		//����������ֵ
		int max = goods.getTotal_needs();
		//�鿴ʣ���˴�
		int rest_needs = goods.getRest_needs();
		//1.��ʣ���˴δ��ڵ��ڹ������ʱ
		if(rest_needs >= buy_amount){
			//ѭ������buy_amount��
			for(int i=0;i<buy_amount;i++){
				//�Ѵ��ڵ��ƹ���
				List<Integer> codes = cloudBuyDao.listCodes(goods_id, period);
				int buyer_code;
				do{
					//�ƹ������
					buyer_code = 1000000;
					//��������ƹ���
					buyer_code += (int)(Math.random()*max+1);
				}while(codes.contains(buyer_code));//����ƹ����Ѵ��ڣ������ѭ��
				cloudBuy.setGoods_id(goods_id);
				cloudBuy.setPeriod(period);
				cloudBuy.setBuyer_code(buyer_code);
				cloudBuyDao.save(cloudBuy);
			}
			rest_needs -= buy_amount;
			//����ʣ���˴�
			goodsDao.updateNeeds(rest_needs, goods_id);
			//���û��Ѿ�������ƹ���
			List<Integer> buyCodes = cloudBuyDao.listUserCodes(user_id, goods_id, period);
			if(rest_needs == 0){
				//���¸���Ʒ����ƹ�ʱ��
				goodsDao.finishCloudBuy(goods_id);
				//������һ�ڵ��ƹ���Ʒ
				period += 1;
				goods.setPeriod(period);
				goodsDao.autoSave(goods);
			}
			return buyCodes;
		}else{
			//2.��ʣ���˴�С�ڹ������ʱ,��ʾ�û�����������ޣ����¹���
			throw new OutOfRangeException("�����������ʣ���˴�");
		}
	}

	/**
	 * �����н���
	 * �������1���Ը���Ʒ���֧��ʱ��Ϊ�����㣬ȡȫ�����100��֧��ʱ���¼��
	 * 		  2��ÿ��ʱ���¼��ʱ���֡����������г�һ����ֵ��
	 * 		  3����100����ֵ֮�ͳ�����Ʒ����֧�ַ�����ȡ�࣬����1000001Ϊ��������)
	 * @param goods_id
	 * @param period
	 * @return �н���
	 */
	private int generateLotteryCode(int goods_id,int period){
		List<Date> list = cloudBuyDao.listTime(goods_id, period);
		DateFormat df = new SimpleDateFormat("HHmmss");
		long timeSum = 0;
		for(Date time : list){
			int t = Integer.parseInt(df.format(time));
			timeSum += t;
		}
		Goods goods = goodsDao.findById(goods_id);
		int total_needs = goods.getTotal_needs();
		int lotteryCode = (int) (timeSum % total_needs + 1000001);
		return lotteryCode;
	}

	public Map<String, Object> lottery(int goods_id, int period) {
		int lotteryCode = generateLotteryCode(goods_id, period);
		System.out.println(lotteryCode);
		Map<String,Object> result = new HashMap<String, Object>();
		CloudBuy cloudBuy = cloudBuyDao.findWinner(lotteryCode,goods_id,period);
		int user_id = cloudBuy.getUser_id();
		CloudLottery cloudLottery = new CloudLottery();
		cloudLottery.setGoods_id(goods_id);
		cloudLottery.setLottery_code(lotteryCode);
		cloudLottery.setPeriod(period);
		cloudLottery.setUser_id(user_id);
		cloudLotteryDao.save(cloudLottery);
		User user = userDao.findById(user_id);
		result.put("nickname", user.getNickname());
		//�������
		int counts = cloudBuyDao.getCounts(user_id,goods_id,period);
		result.put("buyTimes", counts);
		//�ƹ�ʱ��
		Timestamp buy_time = cloudBuy.getBuy_time();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		result.put("buy_time", df.format(buy_time));
		//�����ƹ���
		result.put("lotteryCode", lotteryCode);
		//����ʱ��
		Timestamp announceTime = cloudLotteryDao.findById(goods_id).getAnnounce_time();
		result.put("announceTime",df.formatToCharacterIterator(announceTime));
		return result;
	}

	public boolean modifyGoodsInfo(int id,String goodsName, double price, int total_needs, String goods_info, int period) {
		Goods goods = new Goods();
		goods.setId(id);
		goods.setName(goodsName);
		goods.setPrice(price);
		goods.setTotal_needs(total_needs);
		goods.setGoods_info(goods_info);
		goods.setPeriod(period);
		int i;
		try {
			i = goodsDao.modifyGoodsInfo(goods);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ��쳣");
		}
		if(i != 1){
			throw new GoodsException("��Ʒ��Ϣ����ʧ��");
		}
		return true;
	}

	public boolean modifyGoodsCover(int id, HttpServletRequest request) throws IOException {
		String folder1 = "BackgroundManagement";
		String folder2 = "GoodsCover";
		String coverPath;
		try {
			coverPath = Upload.uploadImg(request, folder1, folder2).get(0);
		} catch (Exception e) {
			throw new ImgpathException("ͼƬ�ϴ�ʧ��");
		}
		int i;
		try {
			i = goodsDao.modifyGoodsCover(coverPath, id);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ��쳣");
		}
		if(i != 1){
			throw new GoodsException("��Ʒ�����޸�ʧ��");
		}
		return true;
	}

	public Map<String,Object> listGoodsByStatus(String goods_status, int page, int pageSize) {
		int offset = (page-1)*pageSize;
		Map<String,Object> result = new HashMap<String, Object>();
		List<Goods> goods = new ArrayList<Goods>();
		int goodsNum;
		if(goods_status==null || goods_status.trim().isEmpty()){
			try {
				goods = goodsDao.findAll(offset, pageSize);
				goodsNum = goodsDao.getGoodsNum();
			} catch (Exception e) {
				throw new DataBaseException("���ݿ��쳣");
			}
			result.put("goodsNum", goodsNum);
			result.put("goodsList", goods);
			return result;
		}
		try {
			goods = goodsDao.findGoodsByStatus(goods_status, offset, pageSize);
			goodsNum = goodsDao.getStatusGoodsNum(goods_status);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ��쳣");
		}
		result.put("goodsNum", goodsNum);
		result.put("goodsList", goods);
		return result;
	}

	public Map<String,Object> onShelves(int id) {
		int i;
		try {
			i = goodsDao.onShelves(id);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ��쳣");
		}
		if(i != 1){
			throw new GoodsException("��Ʒ�ϼ�ʧ��");
		}
		Map<String,Object> result = new HashMap<String,Object>();
		Goods goods = goodsDao.findById(id);
		String goods_status = goods.getGoods_status();
		result.put("goods_status", goods_status);
		Timestamp sell_time = goods.getSell_time();
		result.put("sell_time", sell_time);
		return result;
	}

	public Map<String, Object> offShelves(int id) {
		int i;
		try {
			i = goodsDao.offShelves(id);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ��쳣");
		}
		if(i != 1){
			throw new GoodsException("��Ʒ�¼�ʧ��");
		}
		Map<String,Object> result = new HashMap<String,Object>();
		Goods goods = goodsDao.findById(id);
		String goods_status = goods.getGoods_status();
		result.put("goods_status", goods_status);
		Timestamp off_time = goods.getOff_time();
		result.put("off_time", off_time);
		return result;
	}

	public List<Map<String, Object>> listLotteryInfo(int page, int pageSize) {
		int offset = (page-1)*pageSize;
		List<Map<String, Object>> result = new ArrayList<Map<String,Object>>();
		int num;
		try {
			result = cloudLotteryDao.listLotteryInfo(offset,pageSize);
			num = cloudLotteryDao.getLotteryNum();
		} catch (Exception e) {
			throw new DataBaseException("���ݿ��쳣");
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("num", num);
		result.add(map);
		return result;
	}
}
