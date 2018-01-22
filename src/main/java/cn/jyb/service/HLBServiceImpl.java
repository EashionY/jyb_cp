package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
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

import cn.jyb.dao.HLBChargeMapper;
import cn.jyb.dao.HLBInviteMapper;
import cn.jyb.dao.HLBOrderMapper;
import cn.jyb.dao.HLBSureMapper;
import cn.jyb.dao.IdCardMapper;
import cn.jyb.dao.UserDao;
import cn.jyb.dao.UserPositionMapper;
import cn.jyb.dao.VehicleLicenseMapper;
import cn.jyb.entity.HLBCharge;
import cn.jyb.entity.HLBInvite;
import cn.jyb.entity.HLBOrder;
import cn.jyb.entity.HLBSure;
import cn.jyb.entity.User;
import cn.jyb.entity.VehicleLicense;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.HLBOrderException;
import cn.jyb.exception.NoUserFoundException;
import cn.jyb.util.DateUtil;
@Service("HLBService")
public class HLBServiceImpl implements HLBService {

	@Resource
	private HLBOrderMapper hlbOrderMapper;
	@Resource
	private UserDao userDao;
	@Resource
	private IdCardMapper idcardMapper;
	@Resource
	private VehicleLicenseMapper vehicleLicMapper;
	@Resource
	private HLBSureMapper hlbSureMapper;
	@Resource
	private HLBInviteMapper hlbInviteMapper;
	@Resource
	private HLBChargeMapper hlbChargeMapper;
	
	@Override
	public HLBOrder saveHLBOrder(HttpServletRequest request) throws UnsupportedEncodingException, ParseException {
		request.setCharacterEncoding("utf-8");
		HLBOrder hlbOrder = new HLBOrder();
		//����ϵͳ��ǰʱ������Ψһ�Ķ�����
		String hlbOrderNo = DateUtil.getOrderNum()+DateUtil.getThree();
		hlbOrder.setHlbOrderNo(hlbOrderNo);
		hlbOrder.setCarType(request.getParameter("carType"));//��������
		hlbOrder.setDeparture(request.getParameter("departure"));//������
		hlbOrder.setDestination(request.getParameter("destination"));//Ŀ�ĵ�
		//���ö����۸񣨱�����λС����
		hlbOrder.setFare(new BigDecimal(request.getParameter("fare")).setScale(2, BigDecimal.ROUND_HALF_UP));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date departTime = sdf.parse(request.getParameter("departTime"));
		hlbOrder.setDepartTime(departTime);//����ʱ��   
		hlbOrder.setRemark(request.getParameter("remark"));//��ע
		hlbOrder.setCarry(request.getParameter("carry"));//����
		hlbOrder.setBackhaul(request.getParameter("backhaul"));//����
		hlbOrder.setInvoice(request.getParameter("invoice"));//���ӻص�
		hlbOrder.setContact(request.getParameter("contact"));//��ϵ��
		hlbOrder.setContactPhone(request.getParameter("contactPhone"));//��ϵ�˵绰
		hlbOrder.setPublishId(Integer.parseInt(request.getParameter("publishId")));//����������id
		hlbOrder.setOrderType(Integer.parseInt(request.getParameter("orderType")));//��������
		hlbOrder.setDriverEval(5);//�������ֳ�ʼֵΪ5
		hlbOrder.setPassengerEval(5);//�˿����ֳ�ʼֵΪ5
		try {
			hlbOrderMapper.insertSelective(hlbOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		return hlbOrderMapper.selectByPrimaryKey(hlbOrderNo);
	}

	@Override
	@Transactional
	public synchronized HLBOrder acceptHLBOrder(String hlbOrderNo, Integer receiptId) {
		HLBOrder hlbOrder = hlbOrderMapper.selectByPrimaryKey(hlbOrderNo);
		if(hlbOrder == null){
			throw new HLBOrderException("����Ķ�����");
		}
		if(hlbOrder.getOrderStatus() != 0){
			throw new HLBOrderException("��Ŷ���������ˣ�");
		}
		hlbOrder.setReceiptId(receiptId);
		hlbOrder.setReceiptTime(new Date());//�ӵ�ʱ��
		hlbOrder.setOrderStatus(1);//����״̬��1--˾���ѽӵ���
		try {
			hlbOrderMapper.updateByPrimaryKeySelective(hlbOrder);//���¶���״̬
			hlbInviteMapper.deleteByHlbOrderNo(hlbOrderNo);//���˽ӵ�֮����ɾ���ö����������¼
			changed.put(hlbOrderNo, true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		return hlbOrder;
	}

	@Override
	public List<HLBOrder> listBookOrders(Integer receiptId, Integer page, Integer pageSize) {
		Integer offset = (page - 1) * pageSize;
		return hlbOrderMapper.listBookOrders(receiptId, offset, pageSize);
	}

	@Override
	public boolean evalDriver(String hlbOrderNo, Integer evalStar) {
		HLBOrder hlbOrder = new HLBOrder();
		hlbOrder.setHlbOrderNo(hlbOrderNo);
		hlbOrder.setDriverEval(evalStar);
		hlbOrder.setOrderStatus(6);
		hlbOrder.setFinishTime(new Date());
		int i;
		try {
			i = hlbOrderMapper.updateByPrimaryKeySelective(hlbOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		return i==1;
	}

	@Override
	public boolean evalPassenger(String hlbOrderNo, Integer evalStar) {
		HLBOrder hlbOrder = new HLBOrder();
		hlbOrder.setHlbOrderNo(hlbOrderNo);
		hlbOrder.setPassengerEval(evalStar);
		hlbOrder.setOrderStatus(6);
		hlbOrder.setFinishTime(new Date());
		int i;
		try {
			i = hlbOrderMapper.updateByPrimaryKeySelective(hlbOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		return i==1;
	}

	@Override
	public boolean cancelOrder(String hlbOrderNo, Integer userId) {
		HLBOrder hlbOrder = hlbOrderMapper.selectByPrimaryKey(hlbOrderNo);
		if(hlbOrder == null){
			throw new HLBOrderException("����Ķ�����");
		}
		if(hlbOrder.getOrderStatus() != 1){
			throw new HLBOrderException("�޷�ȡ������");
		}
		hlbOrder.setOrderStatus(-1);
		try {
			hlbOrderMapper.updateByPrimaryKeySelective(hlbOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		//����û��Ƿ��ڽӵ�֮��5����ȡ������
		long receiptTime = hlbOrder.getReceiptTime().getTime();
		long costTime = System.currentTimeMillis() - receiptTime;
		if(costTime > 5*60*1000){
			//��ʱ������Ӧ�Ŀۿ��
			//TODO
		}
		changed.put(hlbOrderNo, true);
		return true;
	}

	@Override
	public Map<String, Object> getDriverInfo(Integer receiptId) {
		Map<String, Object> result = new HashMap<String, Object>();//�����
		User user = userDao.findById(receiptId);
		if(user == null){
			throw new NoUserFoundException("������û�id");
		}
		String imgpath = user.getImgpath();//ͷ��
		result.put("imgpath", imgpath);
		String phone = user.getPhone();//�����绰
		result.put("phone", phone);
		result.put("sex", user.getSex());//�Ա�
		String driverName = idcardMapper.findByUserId(receiptId).getIdcardRealname().substring(0, 1) + "ʦ��";
		result.put("driverName", driverName);//������ν��ĳʦ��
		Double driverScore = hlbOrderMapper.getDriverScore(receiptId);//��������
		result.put("driverScore", driverScore == null ? 5 : driverScore);
		int driverOrderNum = hlbOrderMapper.getDriverOrderNum(receiptId);//����������
		result.put("driverOrderNum", driverOrderNum);
		VehicleLicense vehicleLic = vehicleLicMapper.findByUserId(receiptId);
		result.put("vehicleNo", vehicleLic.getVehicleNo());//���ƺ�
		result.put("vehicleBrand", vehicleLic.getVehicleBrand());//����Ʒ��
		return result;
	}

	@Override
	public String getPrice(String carType, String mileage) {
		String price = "";
		//����������д���С�����֣�ʼ�ղ��ý�һ��
		int km = new BigDecimal(mileage).setScale(0, RoundingMode.CEILING).intValue();
		HLBCharge charge;
		try {
			charge = hlbChargeMapper.findByCarType(carType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��ڲ�����");
		}
		if(charge == null){
			throw new HLBOrderException("��Ч�ĳ�������");
		}
		int within = charge.getWithin();
		BigDecimal inPrice = charge.getInPrice();
		BigDecimal outPrice = charge.getOutPrice();
		if(km <= within){//С�ڻ�����𲽼����
			price = inPrice.toString();
		}else{
			BigDecimal t = new BigDecimal(km - within);//���������
			price = inPrice.add(outPrice.multiply(t)).setScale(2).toString();
		}
		return price;
	}
	
	@Override
	public Map<String,Object> priceDetail(String carType, String mileage){
		Map<String,Object> result = new HashMap<String,Object>();
		String totalPrice = getPrice(carType, mileage);//�ܼ�
		result.put("totalPrice", totalPrice);
		//����������д���С�����֣�ʼ�ղ��ý�һ��
		int km = new BigDecimal(mileage).setScale(0, RoundingMode.CEILING).intValue();
		result.put("mileage", km);//�����
		HLBCharge charge = hlbChargeMapper.findByCarType(carType);
		result.put("car", charge.getCar());//����
		int within = charge.getWithin();
		BigDecimal inPrice = charge.getInPrice();
		if(km <= within){
			result.put("inPrice", inPrice.toString());//�𲽼�
			result.put("outMile", 0);//�������
			result.put("outCost", "0.00");//������̵ļ۸�
		}else{
			result.put("inPrice", inPrice.toString());//�𲽼�
			result.put("outMile", km - within);//�������
			result.put("outCost", new BigDecimal(totalPrice).subtract(inPrice).setScale(2).toString());//������̵ļ۸�
		}
		return result;
	}
	
	@Override
	public HLBOrder getOrderInfo(String hlbOrderNo) {
		return hlbOrderMapper.selectByPrimaryKey(hlbOrderNo);
	}

	@Override
	public boolean getClose(String hlbOrderNo) {
		HLBSure hlbSure = new HLBSure();
		hlbSure.setHlbOrderNo(hlbOrderNo);
		hlbSure.setNearby("1");
		hlbSure.setNearbyTime(new Date());
		int i;
		try {
			i = hlbSureMapper.insertSelective(hlbSure);
			updateOrderStatus(hlbOrderNo, 2);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		changed.put(hlbOrderNo, true);
		return i == 1;
	}

	@Override
	public boolean aboard(String hlbOrderNo) {
		HLBSure hlbSure = hlbSureMapper.findByOrderNo(hlbOrderNo);
		if(hlbSure == null){
			throw new HLBOrderException("�ö���������");
		}
		hlbSure.setAboard("1");
		hlbSure.setAboardTime(new Date());
		int i;
		try {
			i = hlbSureMapper.updateByPrimaryKeySelective(hlbSure);
			updateOrderStatus(hlbOrderNo, 4);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		changed.put(hlbOrderNo, true);
		return i == 1;
	}

	@Override
	public boolean tripStart(String hlbOrderNo) {
		HLBSure hlbSure = hlbSureMapper.findByOrderNo(hlbOrderNo);
		if(hlbSure == null){
			throw new HLBOrderException("�ö���������");
		}
		hlbSure.setStart("1");
		hlbSure.setStartTime(new Date());
		int i;
		try {
			i = hlbSureMapper.updateByPrimaryKeySelective(hlbSure);
			updateOrderStatus(hlbOrderNo, 3);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		changed.put(hlbOrderNo, true);
		return i == 1;
	}

	@Override
	public boolean pArrive(String hlbOrderNo) {
		HLBSure hlbSure = hlbSureMapper.findByOrderNo(hlbOrderNo);
		if(hlbSure == null){
			throw new HLBOrderException("�ö���������");
		}
		hlbSure.setpArrive("1");
		hlbSure.setpArriveTime(new Date());
		int i;
		try {
			i = hlbSureMapper.updateByPrimaryKeySelective(hlbSure);
			updateOrderStatus(hlbOrderNo, 6);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		changed.put(hlbOrderNo, true);
		return i == 1;
	}

	@Override
	public boolean dArrive(String hlbOrderNo) {
		HLBSure hlbSure = hlbSureMapper.findByOrderNo(hlbOrderNo);
		if(hlbSure == null){
			throw new HLBOrderException("�ö���������");
		}
		hlbSure.setdArrive("1");
		hlbSure.setdArriveTime(new Date());
		int i;
		try {
			i = hlbSureMapper.updateByPrimaryKeySelective(hlbSure);
			updateOrderStatus(hlbOrderNo, 5);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		changed.put(hlbOrderNo, true);
		return i == 1;
	}

	@Override
	public Map<String, Object> getPassengerInfo(Integer publishId) {
		Map<String, Object> result = new HashMap<String, Object>();//�����
		User user = userDao.findById(publishId);
		if(user == null){
			throw new NoUserFoundException("������û�id");
		}
		result.put("imgpath", user.getImgpath());//ͷ��
		result.put("nickname", user.getNickname());//�ǳ�
		result.put("sex", user.getSex());//�Ա�
		Double passengerScore = hlbOrderMapper.getPassengerScore(publishId);
		int score = (int) (passengerScore == null ? 100 : passengerScore * 20);//����ֵ
		result.put("passengerScore", score);
		int passengerNum = hlbOrderMapper.getPassengerOrderNum(publishId);//�˿Ͷ�����
		result.put("passengerOrderNum", passengerNum);
		return result;
	}

	@Override
	public Map<String, Object> listOrders(String carType, Integer orderType, double lon, double lat, String price, Integer page, Integer pageSize) {
		Map<String,Object> result = new HashMap<String,Object>();
		Integer offset = (page - 1) * pageSize;
		int orderNum = hlbOrderMapper.getOrderNum(carType, orderType);//������
		result.put("orderNum", orderNum);
		//����������ȫ������
		List<Map<String,Object>> list = hlbOrderMapper.listOrders(carType, orderType, lon, lat, price, offset, pageSize);
		for(Map<String,Object> map : list){
			Integer publishId = (Integer) map.get("publish_id");
			map.putAll(getPassengerInfo(publishId));//���˿͸�����Ϣ��������
		}
		result.put("orders", list);
		return result;
	}

	@Override
	public List<Map<String, Object>> getInvites(Integer invited, Integer page, Integer pageSize) {
		Integer offset = (page - 1) * pageSize;
		List<Map<String, Object>> list = hlbInviteMapper.getInvites(invited, offset, pageSize);
		for(Map<String,Object> map : list){
			String hlbOrderNo = (String) map.get("hlb_order_no");
			HLBOrder order = getOrderInfo(hlbOrderNo);//��������
			map.put("detail", order);
		}
		return list;
	}

	@Override
	public boolean orderInvite(String hlbOrderNo, Integer invited) {
		HLBInvite invite = hlbInviteMapper.findInvite(hlbOrderNo, invited);
		if(invite == null){
			invite = new HLBInvite();
			invite.setHlbOrderNo(hlbOrderNo);
			invite.setInvited(invited);
			try {
				hlbInviteMapper.insertSelective(invite);
			} catch (Exception e) {
				e.printStackTrace();
				throw new DataBaseException("���ݿ��쳣");
			}
		}
		return true;
	}

	@Resource
	private UserPositionMapper userPosMapper;
	@Resource
	private UserService userService;
	
	@Override
	public List<Map<String, Object>> recomendDriver(String userLon, String userLat, String region) {
		double lon = Double.parseDouble(userLon);
		double lat = Double.parseDouble(userLat);
		Integer d = 20;//ɸѡ����Ϊ20km��
		List<Map<String,Object>> list = userPosMapper.listUserByDistance(lon, lat, region, d);
//		System.out.println("20km�ڵ��û���"+list);
		//�����
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> map : list){
			Integer userId = (Integer) map.get("user_id");
			//�鿴�û���֤��֤�������֤��֤ͨ����Ϊ˾����
			List<Map<String,Object>> cert = userService.checkCertStatus(userId);
			if((Integer)cert.get(0).get("idcardStatus") == 1
					&& (Integer)cert.get(1).get("drvingLicStatus") == 1
					&& (Integer)cert.get(2).get("vehicleLicStatus") == 1){
				Map<String,Object> m = getDriverInfo(userId);
				m.put("userId", userId);
				m.put("distance", map.get("d"));
				result.add(m);
			}
		}
		return result;
	}

	@Override
	public Map<String, Object> listOrdersByDistance(String carType, Integer orderType, double lon,
			double lat, Integer page, Integer pageSize) {
		Map<String,Object> result = new HashMap<String,Object>();
		Integer offset = (page - 1) * pageSize;
		int orderNum = hlbOrderMapper.getOrderNum(carType, orderType);//������
		result.put("orderNum", orderNum);
		//����������ȫ������
		List<Map<String,Object>> list = hlbOrderMapper.listOrdersByDistance(carType, orderType, lon, lat, offset, pageSize);
		for(Map<String,Object> map : list){
			Integer publishId = (Integer) map.get("publish_id");
			map.putAll(getPassengerInfo(publishId));//���˿͸�����Ϣ��������
		}
		result.put("orders", list);
		return result;
	}
	/**
	 * ���¶���״̬
	 * @param hlbOrderNo ������
	 * @param orderStatus 
	 */
	public void updateOrderStatus(String hlbOrderNo, Integer orderStatus){
		HLBOrder hlbOrder = new HLBOrder();
		hlbOrder.setHlbOrderNo(hlbOrderNo);
		hlbOrder.setOrderStatus(orderStatus);
		if(orderStatus == 5){
			hlbOrder.setFinishTime(new Date());
		}
		hlbOrderMapper.updateByPrimaryKeySelective(hlbOrder);
	}
	
	@Override
	public List<Map<String, Object>> listMyOrders(Integer userId, Integer orderStatus, Integer page, Integer pageSize) {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	
	
	
	
	
	
	

}
