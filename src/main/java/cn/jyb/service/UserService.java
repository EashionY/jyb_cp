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
	 * �û���¼����
	 * @param phone
	 * @param password
	 * @return
	 * @throws SQLException 
	 */
	public Map<String, Object> login(String phone,String password) throws PhoneException,PwdException;
	
	/**
	 * �û�ע�᷽��
	 * @param phone
	 * @param password
	 * @param role 0-ѧԱ��1-������2������-����
	 * @param verCode ��֤��
	 * @return
	 */
	public boolean regist(String phone,String password,String role,String verCode);
	
	/**
	 * �û��������룬��������
	 * @param phone
	 * @param newPwd ������
	 * @param verCode ��֤��
	 * @return
	 */
	public boolean resetPassword(String phone,String newPassword,String verCode);
	
	/**
	 * �޸��û���������
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
	 * ͨ���û�id���ҵ绰����
	 * @param user_id
	 * @return String
	 */
	public String findPhoneById(int user_id);
	
	/**
	 * �ϴ�ͷ��ɹ�֮�����ͷ���·��
	 * @param imgpath
	 * @param phone
	 * @return boolean 
	 */
	public boolean updateHeadImg(String imgpath,String phone);
	
}
