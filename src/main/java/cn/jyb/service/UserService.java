package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.User;
import cn.jyb.exception.PhoneException;
import cn.jyb.exception.PwdException;

public interface UserService {

	/**
	 * 用户登录方法
	 * @param phone
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	public Map<String, Object> login(String phone,String password) throws PhoneException,PwdException;
	
	/**
	 * 用户注册方法
	 * @param phone
	 * @param password
	 * @param role 0-学员，1-教练，2或其他-车主
	 * @param verCode 验证码
	 * @return
	 */
	public boolean regist(String phone,String password,String role,String verCode);
	
	/**
	 * 用户忘记密码，重置密码
	 * @param phone
	 * @param newPwd 新密码
	 * @param verCode 验证码
	 * @return
	 */
	public boolean resetPassword(String phone,String newPassword,String verCode);
	
	/**
	 * 修改用户个人资料
	 * @param phone
	 * @param nickname
	 * @param sex
	 * @param address
	 * @param birthday
	 * @param signature
	 * @param xingzuo
	 * @param height
	 * @param weight
	 * @param job
	 * @param salary
	 * @param interest
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public User modifyUserinfo(String phone,String nickname,String sex,String address,
			String birthday,String signature,String xingzuo,String height,String weight,
			String job,String salary,String interest,String region,HttpServletRequest req)throws UnsupportedEncodingException;
	
	/**
	 * 通过用户id查找电话号码
	 * @param user_id
	 * @return String
	 */
	public String findPhoneById(int user_id);
	
	/**
	 * 上传头像成功之后更新头像的路径
	 * @param imgpath
	 * @param phone
	 * @return boolean 
	 */
	public boolean updateHeadImg(String imgpath,String phone);
	
	/**
	 * 切换用户角色
	 * @param user_id
	 * @param role 0:学员，1:教练，2:车主
	 * @return user 用户信息
	 */
	public User changeRole(Integer user_id,String role);
	
	/**
	 * 修改密码
	 * @param phone
	 * @param oldPwd
	 * @param newPwd
	 * @return boolean
	 */
	public boolean modifyPassword(String phone,String oldPwd,String newPwd);
	
	/**
	 * 用户更换手机号
	 * @param user_id 用户id
	 * @param newPhone 新手机号
	 * @param phoneCode 手机验证码
	 * @return
	 */
	public boolean changePhone(Integer user_id,String newPhone,String phoneCode);
	
	/**
	 * 将用户集成到环信
	 * @return
	 */
	public List<String> regist2Easemob();
	
}
