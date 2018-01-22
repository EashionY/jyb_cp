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
		//根据系统当前时间生成唯一的订单号
		String hlbOrderNo = DateUtil.getOrderNum()+DateUtil.getThree();
		hlbOrder.setHlbOrderNo(hlbOrderNo);
		hlbOrder.setCarType(request.getParameter("carType"));//车辆类型
		hlbOrder.setDeparture(request.getParameter("departure"));//出发地
		hlbOrder.setDestination(request.getParameter("destination"));//目的地
		//设置订单价格（保留两位小数）
		hlbOrder.setFare(new BigDecimal(request.getParameter("fare")).setScale(2, BigDecimal.ROUND_HALF_UP));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date departTime = sdf.parse(request.getParameter("departTime"));
		hlbOrder.setDepartTime(departTime);//出发时间   
		hlbOrder.setRemark(request.getParameter("remark"));//备注
		hlbOrder.setCarry(request.getParameter("carry"));//搬运
		hlbOrder.setBackhaul(request.getParameter("backhaul"));//返程
		hlbOrder.setInvoice(request.getParameter("invoice"));//电子回单
		hlbOrder.setContact(request.getParameter("contact"));//联系人
		hlbOrder.setContactPhone(request.getParameter("contactPhone"));//联系人电话
		hlbOrder.setPublishId(Integer.parseInt(request.getParameter("publishId")));//订单发布人id
		hlbOrder.setOrderType(Integer.parseInt(request.getParameter("orderType")));//订单类型
		hlbOrder.setDriverEval(5);//车主评分初始值为5
		hlbOrder.setPassengerEval(5);//乘客评分初始值为5
		try {
			hlbOrderMapper.insertSelective(hlbOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return hlbOrderMapper.selectByPrimaryKey(hlbOrderNo);
	}

	@Override
	@Transactional
	public synchronized HLBOrder acceptHLBOrder(String hlbOrderNo, Integer receiptId) {
		HLBOrder hlbOrder = hlbOrderMapper.selectByPrimaryKey(hlbOrderNo);
		if(hlbOrder == null){
			throw new HLBOrderException("错误的订单号");
		}
		if(hlbOrder.getOrderStatus() != 0){
			throw new HLBOrderException("啊哦，您手慢了！");
		}
		hlbOrder.setReceiptId(receiptId);
		hlbOrder.setReceiptTime(new Date());//接单时间
		hlbOrder.setOrderStatus(1);//订单状态（1--司机已接单）
		try {
			hlbOrderMapper.updateByPrimaryKeySelective(hlbOrder);//更新订单状态
			hlbInviteMapper.deleteByHlbOrderNo(hlbOrderNo);//有人接单之后，则删除该订单的邀请记录
			changed.put(hlbOrderNo, true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
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
			throw new DataBaseException("数据库异常");
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
			throw new DataBaseException("数据库异常");
		}
		return i==1;
	}

	@Override
	public boolean cancelOrder(String hlbOrderNo, Integer userId) {
		HLBOrder hlbOrder = hlbOrderMapper.selectByPrimaryKey(hlbOrderNo);
		if(hlbOrder == null){
			throw new HLBOrderException("错误的订单号");
		}
		if(hlbOrder.getOrderStatus() != 1){
			throw new HLBOrderException("无法取消订单");
		}
		hlbOrder.setOrderStatus(-1);
		try {
			hlbOrderMapper.updateByPrimaryKeySelective(hlbOrder);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		//检查用户是否在接单之后5分钟取消订单
		long receiptTime = hlbOrder.getReceiptTime().getTime();
		long costTime = System.currentTimeMillis() - receiptTime;
		if(costTime > 5*60*1000){
			//超时，做相应的扣款处理
			//TODO
		}
		changed.put(hlbOrderNo, true);
		return true;
	}

	@Override
	public Map<String, Object> getDriverInfo(Integer receiptId) {
		Map<String, Object> result = new HashMap<String, Object>();//结果集
		User user = userDao.findById(receiptId);
		if(user == null){
			throw new NoUserFoundException("错误的用户id");
		}
		String imgpath = user.getImgpath();//头像
		result.put("imgpath", imgpath);
		String phone = user.getPhone();//车主电话
		result.put("phone", phone);
		result.put("sex", user.getSex());//性别
		String driverName = idcardMapper.findByUserId(receiptId).getIdcardRealname().substring(0, 1) + "师傅";
		result.put("driverName", driverName);//车主称谓：某师傅
		Double driverScore = hlbOrderMapper.getDriverScore(receiptId);//车主评分
		result.put("driverScore", driverScore == null ? 5 : driverScore);
		int driverOrderNum = hlbOrderMapper.getDriverOrderNum(receiptId);//车主订单数
		result.put("driverOrderNum", driverOrderNum);
		VehicleLicense vehicleLic = vehicleLicMapper.findByUserId(receiptId);
		result.put("vehicleNo", vehicleLic.getVehicleNo());//车牌号
		result.put("vehicleBrand", vehicleLic.getVehicleBrand());//车辆品牌
		return result;
	}

	@Override
	public String getPrice(String carType, String mileage) {
		String price = "";
		//对里程数进行处理，小数部分，始终采用进一法
		int km = new BigDecimal(mileage).setScale(0, RoundingMode.CEILING).intValue();
		HLBCharge charge;
		try {
			charge = hlbChargeMapper.findByCarType(carType);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库内部错误");
		}
		if(charge == null){
			throw new HLBOrderException("无效的车辆类型");
		}
		int within = charge.getWithin();
		BigDecimal inPrice = charge.getInPrice();
		BigDecimal outPrice = charge.getOutPrice();
		if(km <= within){//小于或等于起步价里程
			price = inPrice.toString();
		}else{
			BigDecimal t = new BigDecimal(km - within);//超出里程数
			price = inPrice.add(outPrice.multiply(t)).setScale(2).toString();
		}
		return price;
	}
	
	@Override
	public Map<String,Object> priceDetail(String carType, String mileage){
		Map<String,Object> result = new HashMap<String,Object>();
		String totalPrice = getPrice(carType, mileage);//总价
		result.put("totalPrice", totalPrice);
		//对里程数进行处理，小数部分，始终采用进一法
		int km = new BigDecimal(mileage).setScale(0, RoundingMode.CEILING).intValue();
		result.put("mileage", km);//总里程
		HLBCharge charge = hlbChargeMapper.findByCarType(carType);
		result.put("car", charge.getCar());//车辆
		int within = charge.getWithin();
		BigDecimal inPrice = charge.getInPrice();
		if(km <= within){
			result.put("inPrice", inPrice.toString());//起步价
			result.put("outMile", 0);//超过里程
			result.put("outCost", "0.00");//超过里程的价格
		}else{
			result.put("inPrice", inPrice.toString());//起步价
			result.put("outMile", km - within);//超过里程
			result.put("outCost", new BigDecimal(totalPrice).subtract(inPrice).setScale(2).toString());//超过里程的价格
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
			throw new DataBaseException("数据库异常");
		}
		changed.put(hlbOrderNo, true);
		return i == 1;
	}

	@Override
	public boolean aboard(String hlbOrderNo) {
		HLBSure hlbSure = hlbSureMapper.findByOrderNo(hlbOrderNo);
		if(hlbSure == null){
			throw new HLBOrderException("该订单不存在");
		}
		hlbSure.setAboard("1");
		hlbSure.setAboardTime(new Date());
		int i;
		try {
			i = hlbSureMapper.updateByPrimaryKeySelective(hlbSure);
			updateOrderStatus(hlbOrderNo, 4);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		changed.put(hlbOrderNo, true);
		return i == 1;
	}

	@Override
	public boolean tripStart(String hlbOrderNo) {
		HLBSure hlbSure = hlbSureMapper.findByOrderNo(hlbOrderNo);
		if(hlbSure == null){
			throw new HLBOrderException("该订单不存在");
		}
		hlbSure.setStart("1");
		hlbSure.setStartTime(new Date());
		int i;
		try {
			i = hlbSureMapper.updateByPrimaryKeySelective(hlbSure);
			updateOrderStatus(hlbOrderNo, 3);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		changed.put(hlbOrderNo, true);
		return i == 1;
	}

	@Override
	public boolean pArrive(String hlbOrderNo) {
		HLBSure hlbSure = hlbSureMapper.findByOrderNo(hlbOrderNo);
		if(hlbSure == null){
			throw new HLBOrderException("该订单不存在");
		}
		hlbSure.setpArrive("1");
		hlbSure.setpArriveTime(new Date());
		int i;
		try {
			i = hlbSureMapper.updateByPrimaryKeySelective(hlbSure);
			updateOrderStatus(hlbOrderNo, 6);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		changed.put(hlbOrderNo, true);
		return i == 1;
	}

	@Override
	public boolean dArrive(String hlbOrderNo) {
		HLBSure hlbSure = hlbSureMapper.findByOrderNo(hlbOrderNo);
		if(hlbSure == null){
			throw new HLBOrderException("该订单不存在");
		}
		hlbSure.setdArrive("1");
		hlbSure.setdArriveTime(new Date());
		int i;
		try {
			i = hlbSureMapper.updateByPrimaryKeySelective(hlbSure);
			updateOrderStatus(hlbOrderNo, 5);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		changed.put(hlbOrderNo, true);
		return i == 1;
	}

	@Override
	public Map<String, Object> getPassengerInfo(Integer publishId) {
		Map<String, Object> result = new HashMap<String, Object>();//结果集
		User user = userDao.findById(publishId);
		if(user == null){
			throw new NoUserFoundException("错误的用户id");
		}
		result.put("imgpath", user.getImgpath());//头像
		result.put("nickname", user.getNickname());//昵称
		result.put("sex", user.getSex());//性别
		Double passengerScore = hlbOrderMapper.getPassengerScore(publishId);
		int score = (int) (passengerScore == null ? 100 : passengerScore * 20);//信任值
		result.put("passengerScore", score);
		int passengerNum = hlbOrderMapper.getPassengerOrderNum(publishId);//乘客订单数
		result.put("passengerOrderNum", passengerNum);
		return result;
	}

	@Override
	public Map<String, Object> listOrders(String carType, Integer orderType, double lon, double lat, String price, Integer page, Integer pageSize) {
		Map<String,Object> result = new HashMap<String,Object>();
		Integer offset = (page - 1) * pageSize;
		int orderNum = hlbOrderMapper.getOrderNum(carType, orderType);//订单数
		result.put("orderNum", orderNum);
		//符合条件的全部订单
		List<Map<String,Object>> list = hlbOrderMapper.listOrders(carType, orderType, lon, lat, price, offset, pageSize);
		for(Map<String,Object> map : list){
			Integer publishId = (Integer) map.get("publish_id");
			map.putAll(getPassengerInfo(publishId));//将乘客个人信息放入结果集
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
			HLBOrder order = getOrderInfo(hlbOrderNo);//订单详情
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
				throw new DataBaseException("数据库异常");
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
		Integer d = 20;//筛选距离为20km内
		List<Map<String,Object>> list = userPosMapper.listUserByDistance(lon, lat, region, d);
//		System.out.println("20km内的用户："+list);
		//结果集
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Map<String,Object> map : list){
			Integer userId = (Integer) map.get("user_id");
			//查看用户三证认证情况（三证认证通过则为司机）
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
		int orderNum = hlbOrderMapper.getOrderNum(carType, orderType);//订单数
		result.put("orderNum", orderNum);
		//符合条件的全部订单
		List<Map<String,Object>> list = hlbOrderMapper.listOrdersByDistance(carType, orderType, lon, lat, offset, pageSize);
		for(Map<String,Object> map : list){
			Integer publishId = (Integer) map.get("publish_id");
			map.putAll(getPassengerInfo(publishId));//将乘客个人信息放入结果集
		}
		result.put("orders", list);
		return result;
	}
	/**
	 * 更新订单状态
	 * @param hlbOrderNo 订单号
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
