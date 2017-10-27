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

	// 密码的最小长度
	private final static int PASSWORD_MIN_LENGTH = 6;
	// 密码的最大长度
	private final static int PASSWORD_MAX_LENGTH = 16;

	public Map<String, Object> login(String phone, String password) throws PhoneException, PwdException {
		Map<String, Object> result = new HashMap<String, Object>();
		if (phone == null || phone.trim().isEmpty()) {
			throw new PhoneException("手机号不能为空");
		}
		if (password == null || password.trim().isEmpty()) {
			throw new PwdException("密码不能为空");
		}
		User user = userDao.findByPhone(phone);
		if (user == null) {
			throw new NoUserFoundException("用户不存在");
		}
		String md5Password = AccountUtil.md5(password);
		if (user.getPassword().equals(md5Password)) {
			int user_id = user.getUser_Id();
			Student student = studentDao.findByUserId(user_id);
			String student_idcard = student == null ? "" : student.getStudent_idcard();
			String student_id = student == null ? "" : String.valueOf(student.getStudent_id());
			result.put("student_idcard", student_idcard);
			result.put("student_id", student_id);
			result.put("address", user.getAddress());
			result.put("birthday", user.getBirthday());
			result.put("height", user.getHeight());
			result.put("id", user.getUser_Id());
			// 如果头像路径为空，则替换为默认的logo
			String headImg = user.getImgpath() == null ? "http://39.108.73.207/img/default/logo.png"
					: user.getImgpath();
			result.put("imgpath", headImg);
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
		} else {
			throw new PwdException("密码错误");
		}
	}

	public boolean regist(String phone, String password, String role, String regCode) {
		User user = new User();
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		// 检查手机号码格式
		if (!phone.matches(phoneRegex)) {
			throw new PhoneException("手机号码格式错误");
		}
		if (userDao.findByPhone(phone) != null) {
			throw new PhoneException("该手机已注册");
		}
		// 检查密码格式
		if (password == null || password.trim().isEmpty()) {
			throw new PwdException("密码为空");
		}
		if (password.length() < PASSWORD_MIN_LENGTH) {
			throw new PwdException("密码长度过短");
		}
		if (password.length() > PASSWORD_MAX_LENGTH) {
			throw new PwdException("密码长度超限");
		}
		// 从数据库vercode表中取得phone对应的注册验证码
		VerCode verCode = verCodeDao.findByPhone(phone);
		if (verCode == null) {
			throw new VerCodeException("未获取验证码");
		}
		String code = verCode.getRegcode();
		Timestamp regcodeTime = verCode.getRegcodeTime();
		long now = System.currentTimeMillis();
		// 验证码有效时间为10分钟(1000*60*10毫秒)
		if ((now - regcodeTime.getTime()) > (1000 * 60 * 10)) {
			throw new VerCodeException("验证码失效");
		}
		if (!regCode.equals(code)) {
			throw new VerCodeException("验证码错误");
		}
		user.setPhone(phone);
		user.setPassword(AccountUtil.md5(password));
		user.setRole(role);
		// 设置默认头像为应用logo
		String headImg = "http://39.108.73.207/img/default/head.png";
		user.setImgpath(headImg);
		int i = userDao.save(user);
		if (i != 1) {
			throw new DataBaseException("数据库连接失败");
		}
		return true;
	}

	public boolean resetPassword(String phone, String newPassword, String pwdCode) {
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		// 检查手机号码格式
		if (!phone.matches(phoneRegex)) {
			throw new PhoneException("手机号码格式错误");
		}
		if (userDao.findByPhone(phone) == null) {
			throw new PhoneException("该手机尚未注册");
		}
		// 检查密码格式
		if (newPassword == null || newPassword.trim().isEmpty()) {
			throw new PwdException("密码为空");
		}
		if (newPassword.length() < PASSWORD_MIN_LENGTH) {
			throw new PwdException("密码长度过短");
		}
		if (newPassword.length() > PASSWORD_MAX_LENGTH) {
			throw new PwdException("密码长度超限");
		}
		// 从数据库vercode表中取得phone对应的改密验证码
		VerCode verCode = verCodeDao.findByPhone(phone);
		if (verCode == null) {
			throw new PwdException("未获取验证码");
		}
		String code = verCode.getPwdcode();
		Timestamp pwdcodeTime = verCode.getPwdcodeTime();
		long now = System.currentTimeMillis();
		// 验证码有效时间为10分钟(1000*60*10毫秒)
		if ((now - pwdcodeTime.getTime()) > (1000 * 60 * 10)) {
			throw new VerCodeException("验证码失效");
		}
		if (!pwdCode.equals(code)) {
			throw new VerCodeException("验证码错误");
		}
		// 使用md5加密新密码
		String md5NewPassword = AccountUtil.md5(newPassword);
		int i = userDao.modifyPassword(phone, md5NewPassword);
		if (i != 1) {
			throw new DataBaseException("密码修改失败");
		}
		return true;
	}

	public User modifyUserinfo(String phone, String nickname, String sex, String address, String birthday,
			String signature, String xingzuo, String height, String weight, String job, String salary, String interest,
			String region, HttpServletRequest req) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		User user = userDao.findByPhone(phone);
		if (user == null) {
			throw new NoUserFoundException("未找到对应用户");
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
		if (i != 1) {
			throw new DataBaseException("用户信息修改失败");
		}
		return userDao.findByPhone(phone);
	}

	public String findPhoneById(int user_id) {
		User user = userDao.findById(user_id);
		if (user == null) {
			throw new NoUserFoundException("未找到对应用户");
		}
		String phone = user.getPhone();
		if (phone == null || phone.trim().isEmpty()) {
			throw new NoPhoneFoundException("未找到相关电话号码");
		}
		return phone;
	}

	public boolean updateHeadImg(String imgpath, String phone) {
		if (imgpath == null || imgpath.trim().isEmpty()) {
			throw new ImgpathException("头像路径为空");
		}
		int i = userDao.updateHeadImg(imgpath, phone);
		if (i != 1) {
			throw new DataBaseException("头像路径更新失败");
		}
		return true;
	}

	public User changeRole(Integer user_id, String role) {
		int i = userDao.changeRole(user_id, role);
		User user = userDao.findById(user_id);
		if(i != 1){
			throw new DataBaseException("端口切换失败");
		}
		return user;
	}

	public boolean modifyPassword(String phone, String oldPwd, String newPwd) {
		User user = userDao.findByPhone(phone);
		//原密码错误
		if(!AccountUtil.md5(oldPwd).equals(user.getPassword())){
			throw new PwdException("旧密码错误");
		}else{
			newPwd = AccountUtil.md5(newPwd);
			int i = userDao.modifyPassword(phone, newPwd);
			if(i != 1){
				throw new PwdException("密码修改失败");
			}
			return true;
		}
	}

	public boolean changePhone(Integer user_id, String newPhone, String phoneCode) {
		String phoneRegex = "^1[3|4|5|7|8][0-9]{9}$";
		// 检查手机号码格式
		if (!newPhone.matches(phoneRegex)) {
			throw new PhoneException("手机号码格式错误");
		}
		User user = userDao.findByPhone(newPhone);
		if(user != null){
			throw new PhoneException("该手机号已注册");
		}else{
			// 从数据库vercode表中取得phone对应的验证码
			VerCode verCode = verCodeDao.findByPhone(newPhone);
			if (verCode == null) {
				throw new VerCodeException("未获取验证码");
			}
			String code = verCode.getRegcode();
			Timestamp regcodeTime = verCode.getRegcodeTime();
			long now = System.currentTimeMillis();
			// 验证码有效时间为10分钟(1000*60*10毫秒)
			if ((now - regcodeTime.getTime()) > (1000 * 60 * 10)) {
				throw new VerCodeException("验证码失效");
			}
			if (!phoneCode.equals(code)) {
				throw new VerCodeException("验证码错误");
			}
			int i = userDao.changePhone(user_id, newPhone);
			if(i != 1){
				throw new PhoneException("手机号更换失败");
			}
			return true;
		}
	}

	
	
}
