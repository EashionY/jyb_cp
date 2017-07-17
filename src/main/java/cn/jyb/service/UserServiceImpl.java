package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jyb.dao.StudentDao;
import cn.jyb.dao.UserDao;
import cn.jyb.dao.VerCodeDao;
import cn.jyb.entity.Student;
import cn.jyb.entity.User;
import cn.jyb.entity.VerCode;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.ImgpathException;
import cn.jyb.exception.NoPhoneFoundException;
import cn.jyb.exception.NoUserFoundException;
import cn.jyb.exception.PhoneException;
import cn.jyb.exception.PwdException;
import cn.jyb.exception.VerCodeException;
import cn.jyb.util.AccountUtil;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private UserDao userDao;
	@Resource
	private VerCodeDao verCodeDao;
	@Resource
	private StudentDao studentDao;
	
	//�������С����
	private final static int PASSWORD_MIN_LENGTH = 6;
	//�������󳤶�
	private final static int PASSWORD_MAX_LENGTH = 16;
	
	public Map<String,Object> login(String phone, String password) throws PhoneException,PwdException{
		Map<String,Object> result = new HashMap<String, Object>();
		if(phone==null || phone.trim().isEmpty()){
			throw new PhoneException("�ֻ��Ų���Ϊ��");
		}
		if(password==null || password.trim().isEmpty()){
			throw new PwdException("���벻��Ϊ��");
		}
		User user = userDao.findByPhone(phone);
		if(user==null){
			throw new NoUserFoundException("�û�������");
		}
		String md5Password = AccountUtil.md5(password);
		if(user.getPassword().equals(md5Password)){
			int user_id = user.getUser_Id();
			Student student = studentDao.findByUserId(user_id);
			String student_idcard = student==null?"":student.getStudent_idcard();
			result.put("student_idcard", student_idcard);
			result.put("address", user.getAddress());
			result.put("birthday", user.getBirthday());
			result.put("height", user.getHeight());
			result.put("id", user.getUser_Id());
			result.put("imgpath", user.getImgpath());
			result.put("interest", user.getInterest());
			result.put("job", user.getJob());
			result.put("nickname", user.getNickname());
			result.put("password", user.getPassword());
			result.put("phone", user.getPhone());
			result.put("role", user.getRole());
			result.put("salary", user.getSalary());
			result.put("sex", user.getSex());
			result.put("signature", user.getSignature());
			result.put("weight", user.getWeight());
			result.put("xingzuo", user.getXingzuo());
			result.put("region", user.getRegion());
			return result;
		}else{
			throw new PwdException("�������");
		}
	}

	public boolean regist(String phone, String password, String role, String regCode) {
		User user = new User();
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		//����ֻ������ʽ
		if(!phone.matches(phoneRegex)){
			throw new PhoneException("�ֻ������ʽ����");
		}
		if(userDao.findByPhone(phone)!=null){
			throw new PhoneException("���ֻ���ע��");
		}
		//��������ʽ 
		if(password==null || password.trim().isEmpty()){
			throw new PwdException("����Ϊ��");
		}
		if(password.length()<PASSWORD_MIN_LENGTH){
			throw new PwdException("���볤�ȹ���");
		}
		if(password.length()>PASSWORD_MAX_LENGTH){
			throw new PwdException("���볤�ȳ���");
		}
		//�����ݿ�vercode����ȡ��phone��Ӧ��ע����֤��
		VerCode verCode = verCodeDao.findByPhone(phone);
		if(verCode==null){
			throw new VerCodeException("δ��ȡ��֤��");
		}
		String code = verCode.getRegcode();
		Timestamp regcodeTime = verCode.getRegcodeTime();
		long now = System.currentTimeMillis();
		//��֤����Чʱ��Ϊ10����(1000*60*10����)
		if((now-regcodeTime.getTime())>(1000*60*10)){
			throw new VerCodeException("��֤��ʧЧ");
		}
		if(!regCode.equals(code)){
			throw new VerCodeException("��֤�����");
		}
		user.setPhone(phone);
		user.setPassword(AccountUtil.md5(password));
		user.setRole(role);
		int i = userDao.save(user);
		if(i!=1){
			throw new DataBaseException("���ݿ�����ʧ��");
		}
		return true;
	}

	public boolean resetPassword(String phone, String newPassword, String pwdCode) {
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		//����ֻ������ʽ
		if(!phone.matches(phoneRegex)){
			throw new PhoneException("�ֻ������ʽ����");
		}
		if(userDao.findByPhone(phone)==null){
			throw new PhoneException("���ֻ���δע��");
		}
		//��������ʽ 
		if(newPassword==null || newPassword.trim().isEmpty()){
			throw new PwdException("����Ϊ��");
		}
		if(newPassword.length()<PASSWORD_MIN_LENGTH){
			throw new PwdException("���볤�ȹ���");
		}
		if(newPassword.length()>PASSWORD_MAX_LENGTH){
			throw new PwdException("���볤�ȳ���");
		}
		//�����ݿ�vercode����ȡ��phone��Ӧ�ĸ�����֤��
		VerCode verCode = verCodeDao.findByPhone(phone);
		if(verCode==null){
			throw new PwdException("δ��ȡ��֤��");
		}
		String code = verCode.getPwdcode();
		Timestamp pwdcodeTime = verCode.getPwdcodeTime();
		long now = System.currentTimeMillis();
		//��֤����Чʱ��Ϊ10����(1000*60*10����)
		if((now-pwdcodeTime.getTime())>(1000*60*10)){
			throw new VerCodeException("��֤��ʧЧ");
		}
		if(!pwdCode.equals(code)){
			throw new VerCodeException("��֤�����");
		}
		//ʹ��md5����������
		String md5NewPassword = AccountUtil.md5(newPassword);
		int i = userDao.modifyPassword(phone, md5NewPassword);
		if(i!=1){
			throw new DataBaseException("�����޸�ʧ��");
		}
		return true;
	}

	public boolean modifyUserinfo(String phone, String nickname, String sex, String address, String birthday,
			String signature, String xingzuo, String height, String weight, String job, String salary,
			String interest,String region,HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("utf-8");
		User user = userDao.findByPhone(phone);
		if(user==null){
			throw new NoUserFoundException("δ�ҵ���Ӧ�û�");
		}
		user.setNickname(nickname);
		user.setSex(sex);
		user.setAddress(address);
		user.setBirthday(birthday);
		user.setSignature(signature);
		user.setXingzuo(xingzuo);
		user.setHeight(height);
		user.setWeight(weight);
		user.setJob(job);
		user.setSalary(salary);
		user.setInterest(interest);
		user.setRegion(region);
		int i = userDao.modifyUserinfo(user);
		if(i!=1){
			throw new DataBaseException("�û���Ϣ�޸�ʧ��");
		}
		return true;
	}

	public String findPhoneById(int user_id) {
		User user = userDao.findById(user_id);
		if(user==null){
			throw new NoUserFoundException("δ�ҵ���Ӧ�û�");
		}
		String phone = user.getPhone();
		if(phone==null || phone.trim().isEmpty()){
			throw new NoPhoneFoundException("δ�ҵ���ص绰����");
		}
		return phone;
	}

	public boolean updateHeadImg(String imgpath,String phone) {
		if(imgpath==null || imgpath.trim().isEmpty()){
			throw new ImgpathException("ͷ��·��Ϊ��");
		}
		int i = userDao.updateHeadImg(imgpath,phone);
		if(i!=1){
			throw new DataBaseException("ͷ��·������ʧ��");
		}
		return true;
	}

}
