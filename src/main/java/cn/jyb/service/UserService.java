package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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
	
}
