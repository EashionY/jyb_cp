package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jyb.dao.HLBOrderMapper;
import cn.jyb.dao.HLBSureMapper;
import cn.jyb.dao.IdCardMapper;
import cn.jyb.dao.UserDao;
import cn.jyb.dao.VehicleLicenseMapper;
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
			hlbOrderMapper.updateByPrimaryKeySelective(hlbOrder);
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
			
		}
		return false;
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
		String driverName = idcardMapper.findByUserId(receiptId).getIdcardRealname().substring(0, 1) + "师傅";
		result.put("driverName", driverName);//车主称谓：某师傅
		double driverScore = hlbOrderMapper.getDriverScore(receiptId);//车主评分
		result.put("driverScore", driverScore);
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
		if("0".equals(carType)){//小轿车
			if(km <= 5){//小于或等于5公里
				price = "30";
			}else{
				int t = 30 + (km - 5) * 3;
				price = String.valueOf(t);
			}
		}else if("1".equals(carType)){//小面包车
			if(km <= 5){//小于或等于5公里
				price = "30";
			}else{
				int t = 30 + (km - 5) * 3;
				price = String.valueOf(t);
			}
		}else if("2".equals(carType)){//中面包车
			if(km <= 5){//小于或等于5公里
				price = "60";
			}else{
				int t = 60 + (km - 5) * 4;
				price = String.valueOf(t);
			}
		}else if("3".equals(carType)){//小货车
			if(km <= 5){//小于或等于5公里
				price = "70";
			}else{
				int t = 70 + (km - 5) * 4;
				price = String.valueOf(t);
			}
		}else if("4".equals(carType)){//大货车
			if(km <= 5){//小于或等于5公里
				price = "130";
			}else{
				int t = 130 + (km - 5) * 6;
				price = String.valueOf(t);
			}
		}
		return price;
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
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
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
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
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
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
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
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
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
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
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
		
		return null;
	}
	
	
	
	
	
	

}
