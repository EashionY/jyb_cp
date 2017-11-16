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

	// 验证码的长度
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
			throw new PhoneException("手机号码格式不正确");
		}
		if (userDao.findByPhone(phone) != null) {
			throw new PhoneException("该手机号已注册");
		}
		// 随机生成验证码
		String code = "";
		Random r = new Random(new Date().getTime());
		for (int i = 0; i < NUM; i++) {
			code = code + r.nextInt(10);
		}
		// 注册验证码短信模板id
		String templateCode = "SMS_69000459";
		boolean tf = Message.sendMessage(phone, code, templateCode);
		if (!tf) {
			throw new VerCodeException("验证码发送失败");
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
			throw new PhoneException("手机号码格式不正确");
		}
		User user = userDao.findByPhone(phone);
		if (user == null) {
			throw new NoUserFoundException("账号不存在");
		}
		// 随机生成验证码
		String code = "";
		Random r = new Random(new Date().getTime());
		for (int i = 0; i < NUM; i++) {
			code = code + r.nextInt(10);
		}
		// 改密验证码短信模板id
		String templateCode = "SMS_69180465";
		boolean tf = Message.sendMessage(phone, code, templateCode);
		if (!tf) {
			throw new VerCodeException("验证码发送失败");
		}
		try {
			verCodeDao.updatePwdcode(phone, code);
		} catch (Exception e) {
			throw new DataBaseException("数据库连接失败");
		}
		return code;
	}

	public String sendPayCode(String phone) {
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		if (!phone.matches(phoneRegex)) {
			throw new PhoneException("手机号码格式不正确");
		}
		// 随机生成验证码
		String code = "";
		Random r = new Random(new Date().getTime());
		for (int i = 0; i < NUM; i++) {
			code = code + r.nextInt(10);
		}
		// 提现验证码短信模板id
		String templateCode = "SMS_69095427";
		boolean tf = Message.sendMessage(phone, code, templateCode);
		if (!tf) {
			throw new VerCodeException("验证码发送失败");
		}
		try {
			verCodeDao.updatePaycode(phone, code);
		} catch (Exception e) {
			throw new DataBaseException("数据库连接失败");
		}
		return code;
	}

	public String sendPhoneCode(String phone) {
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		if (!phone.matches(phoneRegex)) {
			throw new PhoneException("手机号码格式不正确");
		}
		if (userDao.findByPhone(phone) != null) {
			throw new PhoneException("该手机号已注册");
		}
		// 随机生成验证码
		String code = "";
		Random r = new Random(new Date().getTime());
		for (int i = 0; i < NUM; i++) {
			code = code + r.nextInt(10);
		}
		// 变更手机号验证码短信模板id
		String templateCode = "SMS_105745146";
		boolean tf = Message.sendMessage(phone, code, templateCode);
		if (!tf) {
			throw new VerCodeException("验证码发送失败");
		}
		VerCode verCode = verCodeDao.findByPhone(phone);
		// 验证码存入数据库中
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
			throw new VehicleLicenseException("该车尚未在本平台认证注册");
		}
		User user = userDao.findById(moveUserId);
		if(user == null){
			throw new NoUserFoundException("用户不存在");
		}
		String phone = user.getPhone();
		//获取要求挪车用户的身份证信息
		IdCard idcard = idCardMapper.findByUserId(userId);
		//要求挪车用户的称呼
		String username = "";  
		if(idcard == null || idcard.getRealnameStatus() == 0){
			throw new IdCardException("请先进行实名认证");
		}else{
			//获得姓氏
			String firstName = idcard.getIdcardRealname().substring(0, 1);
			String gender = idcard.getIdcardSex().equals("男") ? "先生" : "女士";
		   	username = firstName + gender;
			//一键挪车短信模板
			String templateCode = "SMS_107770048";
			return Message.sendMoveCarMsg(phone, username, templateCode);
		}
	}

}
