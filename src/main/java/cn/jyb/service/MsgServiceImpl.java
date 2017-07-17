package cn.jyb.service;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jyb.dao.UserDao;
import cn.jyb.dao.VerCodeDao;
import cn.jyb.entity.User;
import cn.jyb.entity.VerCode;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.NoUserFoundException;
import cn.jyb.exception.PhoneException;
import cn.jyb.exception.VerCodeException;
import cn.jyb.util.Message;
@Service("msgService")
@Transactional
public class MsgServiceImpl implements MsgService {

	//验证码的长度
	private final static Integer NUM = 6;

	@Resource
	private VerCodeDao verCodeDao;
	
	@Resource
	private UserDao userDao;

	public String sendRegCode(String phone) {
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		if(!phone.matches(phoneRegex)){
			throw new PhoneException("手机号码格式不正确");
		}
		if(userDao.findByPhone(phone)!=null){
			throw new PhoneException("该手机已注册");
		}
		//随机生成验证码
		String code = "";
		Random r = new Random(new Date().getTime());
        for(int i=0;i<NUM;i++){
            code = code+r.nextInt(10);
        }
        //注册验证码短信模板id
        String templateCode = "SMS_69000459";
		boolean tf = Message.sendMessage(phone, code, templateCode);
        if(!tf){
        	throw new VerCodeException("验证码发送失败");
        }
    	VerCode verCode = verCodeDao.findByPhone(phone);
		if(verCode==null){
			verCodeDao.saveRegcode(phone, code);
		}else{
			verCodeDao.updateRegcode(phone, code);
		}
        return code;
	}
	
	public String sendPwdCode(String phone) {
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		if(!phone.matches(phoneRegex)){
			throw new PhoneException("手机号码格式不正确");
		}
		User user = userDao.findByPhone(phone);
		if(user==null){
			throw new NoUserFoundException("账号不存在");
		}
		//随机生成验证码
		String code = "";
		Random r = new Random(new Date().getTime());
        for(int i=0;i<NUM;i++){
            code = code+r.nextInt(10);
        }
        //改密验证码短信模板id
        String templateCode = "SMS_69180465";
		boolean tf = Message.sendMessage(phone, code, templateCode);
        if(!tf){
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
		if(!phone.matches(phoneRegex)){
			throw new PhoneException("手机号码格式不正确");
		}
		//随机生成验证码
		String code = "";
		Random r = new Random(new Date().getTime());
        for(int i=0;i<NUM;i++){
            code = code+r.nextInt(10);
        }
        //提现验证码短信模板id
        String templateCode = "SMS_69095427";
		boolean tf = Message.sendMessage(phone, code, templateCode);
        if(!tf){
        	throw new VerCodeException("验证码发送失败");
        }
        try {
			verCodeDao.updatePaycode(phone, code);
		} catch (Exception e) {
			throw new DataBaseException("数据库连接失败");
		}
        return code;
	}
	
}
