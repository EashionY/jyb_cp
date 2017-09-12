package cn.jyb.service;

import java.util.List;

import cn.jyb.entity.Admin;

public interface AdminService {

	/**
	 * ����Ա��¼
	 * @param account
	 * @param password
	 * @return
	 */
	Admin login(String account,String password);
	
	/**
	 * �޸�����
	 * @param account
	 * @param oldPassowrd
	 * @param newPassword
	 * @return
	 */
	Admin modifyPwd(String account,String oldPassword,String newPassword);
	
	/**
	 * �鿴���й���Ա
	 * @param privil
	 * @return
	 */
	List<Admin> listAdmins(Integer privil);
	
	/**
	 * ����/���ù���Ա�˺�
	 * @param adminId
	 * @param adminStatus
	 */
	void dealAdmin(Integer adminId,boolean adminStatus);
	
	/**
	 * ��������Ա�˺�
	 * @param account
	 * @param password
	 * @param privil
	 * @param school
	 */
	void saveAdmin(String account,String password,Integer privil,String school);
}
