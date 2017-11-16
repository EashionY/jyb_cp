package cn.jyb.service;

import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jyb.dao.IdCardMapper;
import cn.jyb.dao.UserDao;
import cn.jyb.dao.VehicleLicenseMapper;
import cn.jyb.dao.VerCodeDao;
import cn.jyb.entity.IdCard;
import cn.jyb.entity.User;
import cn.jyb.entity.VehicleLicense;
import cn.jyb.entity.VerCode;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.IdCardException;
import cn.jyb.exception.NoUserFoundException;
import cn.jyb.exception.PhoneException;
import cn.jyb.exception.VehicleLicenseException;
import cn.jyb.exception.VerCodeException;
import cn.jyb.util.Message;

@Service("msgService")
@Transactional
public class MsgServiceImpl implements MsgService {

	// ��֤��ĳ���
	private final static Integer NUM = 6;

	@Resource
	private VerCodeDao verCodeDao;

	@Resource
	private UserDao userDao;
	
	@Resource
	private IdCardMapper idCardMapper;
	
	@Resource
	private VehicleLicenseMapper veLicenseMapper;

	public String sendRegCode(String phone) {
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		if (!phone.matches(phoneRegex)) {
			throw new PhoneException("�ֻ������ʽ����ȷ");
		}
		if (userDao.findByPhone(phone) != null) {
			throw new PhoneException("���ֻ�����ע��");
		}
		// ���������֤��
		String code = "";
		Random r = new Random(new Date().getTime());
		for (int i = 0; i < NUM; i++) {
			code = code + r.nextInt(10);
		}
		// ע����֤�����ģ��id
		String templateCode = "SMS_69000459";
		boolean tf = Message.sendMessage(phone, code, templateCode);
		if (!tf) {
			throw new VerCodeException("��֤�뷢��ʧ��");
		}
		VerCode verCode = verCodeDao.findByPhone(phone);
		if (verCode == null) {
			verCodeDao.saveRegcode(phone, code);
		} else {
			verCodeDao.updateRegcode(phone, code);
		}
		return code;
	}

	public String sendPwdCode(String phone) {
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		if (!phone.matches(phoneRegex)) {
			throw new PhoneException("�ֻ������ʽ����ȷ");
		}
		User user = userDao.findByPhone(phone);
		if (user == null) {
			throw new NoUserFoundException("�˺Ų�����");
		}
		// ���������֤��
		String code = "";
		Random r = new Random(new Date().getTime());
		for (int i = 0; i < NUM; i++) {
			code = code + r.nextInt(10);
		}
		// ������֤�����ģ��id
		String templateCode = "SMS_69180465";
		boolean tf = Message.sendMessage(phone, code, templateCode);
		if (!tf) {
			throw new VerCodeException("��֤�뷢��ʧ��");
		}
		try {
			verCodeDao.updatePwdcode(phone, code);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ�����ʧ��");
		}
		return code;
	}

	public String sendPayCode(String phone) {
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		if (!phone.matches(phoneRegex)) {
			throw new PhoneException("�ֻ������ʽ����ȷ");
		}
		// ���������֤��
		String code = "";
		Random r = new Random(new Date().getTime());
		for (int i = 0; i < NUM; i++) {
			code = code + r.nextInt(10);
		}
		// ������֤�����ģ��id
		String templateCode = "SMS_69095427";
		boolean tf = Message.sendMessage(phone, code, templateCode);
		if (!tf) {
			throw new VerCodeException("��֤�뷢��ʧ��");
		}
		try {
			verCodeDao.updatePaycode(phone, code);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ�����ʧ��");
		}
		return code;
	}

	public String sendPhoneCode(String phone) {
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		if (!phone.matches(phoneRegex)) {
			throw new PhoneException("�ֻ������ʽ����ȷ");
		}
		if (userDao.findByPhone(phone) != null) {
			throw new PhoneException("���ֻ�����ע��");
		}
		// ���������֤��
		String code = "";
		Random r = new Random(new Date().getTime());
		for (int i = 0; i < NUM; i++) {
			code = code + r.nextInt(10);
		}
		// ����ֻ�����֤�����ģ��id
		String templateCode = "SMS_105745146";
		boolean tf = Message.sendMessage(phone, code, templateCode);
		if (!tf) {
			throw new VerCodeException("��֤�뷢��ʧ��");
		}
		VerCode verCode = verCodeDao.findByPhone(phone);
		// ��֤��������ݿ���
		if (verCode == null) {
			verCodeDao.saveRegcode(phone, code);
		} else {
			verCodeDao.updateRegcode(phone, code);
		}
		return code;
	}

	public boolean sendMoveCarMsg(Integer userId, Integer moveUserId, String vehicleNo) {
		if(moveUserId == null){
			List<VehicleLicense> list = veLicenseMapper.findByVehicleNo(vehicleNo);
			for(VehicleLicense lic : list){
				if(lic.getVehicleLicenseStatus() == 1){
					moveUserId = lic.getUserId();
					break;
				}
			}
		}
		if(moveUserId == null){
			throw new VehicleLicenseException("�ó���δ�ڱ�ƽ̨��֤ע��");
		}
		User user = userDao.findById(moveUserId);
		if(user == null){
			throw new NoUserFoundException("�û�������");
		}
		String phone = user.getPhone();
		//��ȡҪ��Ų���û������֤��Ϣ
		IdCard idcard = idCardMapper.findByUserId(userId);
		//Ҫ��Ų���û��ĳƺ�
		String username = "";  
		if(idcard == null || idcard.getRealnameStatus() == 0){
			throw new IdCardException("���Ƚ���ʵ����֤");
		}else{
			//�������
			String firstName = idcard.getIdcardRealname().substring(0, 1);
			String gender = idcard.getIdcardSex().equals("��") ? "����" : "Ůʿ";
		   	username = firstName + gender;
			//һ��Ų������ģ��
			String templateCode = "SMS_107770048";
			return Message.sendMoveCarMsg(phone, username, templateCode);
		}
	}

}
