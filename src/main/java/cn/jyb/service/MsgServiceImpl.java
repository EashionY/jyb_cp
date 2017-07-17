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

	//��֤��ĳ���
	private final static Integer NUM = 6;

	@Resource
	private VerCodeDao verCodeDao;
	
	@Resource
	private UserDao userDao;

	public String sendRegCode(String phone) {
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		if(!phone.matches(phoneRegex)){
			throw new PhoneException("�ֻ������ʽ����ȷ");
		}
		if(userDao.findByPhone(phone)!=null){
			throw new PhoneException("���ֻ���ע��");
		}
		//���������֤��
		String code = "";
		Random r = new Random(new Date().getTime());
        for(int i=0;i<NUM;i++){
            code = code+r.nextInt(10);
        }
        //ע����֤�����ģ��id
        String templateCode = "SMS_69000459";
		boolean tf = Message.sendMessage(phone, code, templateCode);
        if(!tf){
        	throw new VerCodeException("��֤�뷢��ʧ��");
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
			throw new PhoneException("�ֻ������ʽ����ȷ");
		}
		User user = userDao.findByPhone(phone);
		if(user==null){
			throw new NoUserFoundException("�˺Ų�����");
		}
		//���������֤��
		String code = "";
		Random r = new Random(new Date().getTime());
        for(int i=0;i<NUM;i++){
            code = code+r.nextInt(10);
        }
        //������֤�����ģ��id
        String templateCode = "SMS_69180465";
		boolean tf = Message.sendMessage(phone, code, templateCode);
        if(!tf){
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
		if(!phone.matches(phoneRegex)){
			throw new PhoneException("�ֻ������ʽ����ȷ");
		}
		//���������֤��
		String code = "";
		Random r = new Random(new Date().getTime());
        for(int i=0;i<NUM;i++){
            code = code+r.nextInt(10);
        }
        //������֤�����ģ��id
        String templateCode = "SMS_69095427";
		boolean tf = Message.sendMessage(phone, code, templateCode);
        if(!tf){
        	throw new VerCodeException("��֤�뷢��ʧ��");
        }
        try {
			verCodeDao.updatePaycode(phone, code);
		} catch (Exception e) {
			throw new DataBaseException("���ݿ�����ʧ��");
		}
        return code;
	}
	
}
