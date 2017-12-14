package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
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
import cn.jyb.dao.IdCardMapper;
import cn.jyb.dao.UserDao;
import cn.jyb.dao.VehicleLicenseMapper;
import cn.jyb.entity.HLBOrder;
import cn.jyb.entity.VehicleLicense;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.HLBOrderException;
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
	public HLBOrder acceptHLBOrder(String hlbOrderNo, Integer receiptId) {
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
			hlbOrderMapper.updateByPrimaryKeySelective(hlbOrder);
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
			
		}
		return false;
	}

	@Override
	public Map<String, Object> getDriverInfo(Integer receiptId) {
		Map<String, Object> result = new HashMap<String, Object>();//�����
		String imgpath = userDao.findById(receiptId).getImgpath();//ͷ��
		result.put("imgpath", imgpath);
		String driverName = idcardMapper.findByUserId(receiptId).getIdcardRealname().substring(0, 1) + "ʦ��";
		result.put("driverName", driverName);//������ν��ĳʦ��
		double driverScore = hlbOrderMapper.getDriverScore(receiptId);//��������
		result.put("driverScore", driverScore);
		int driverOrderNum = hlbOrderMapper.getDriverOrderNum(receiptId);//����������
		result.put("driverOrderNum", driverOrderNum);
		VehicleLicense vehicleLic = vehicleLicMapper.findByUserId(receiptId);
		result.put("vehicleNo", vehicleLic.getVehicleNo());//���ƺ�
		result.put("vehicleBrand", vehicleLic.getVehicleBrand());//����Ʒ��
		return result;
	}
	
	
	
	

}
