package cn.jyb.service;

import java.util.List;

import cn.jyb.entity.Admin;

public interface AdminService {

	/**
	 * 管理员登录
	 * @param account
	 * @param password
	 * @return
	 */
	Admin login(String account,String password);
	
	/**
	 * 修改密码
	 * @param account
	 * @param oldPassowrd
	 * @param newPassword
	 * @return
	 */
	Admin modifyPwd(String account,String oldPassword,String newPassword);
	
	/**
	 * 查看所有管理员
	 * @param privil
	 * @return
	 */
	List<Admin> listAdmins(Integer privil);
	
	/**
	 * 开启/禁用管理员账号
	 * @param adminId
	 * @param adminStatus
	 */
	void dealAdmin(Integer adminId,boolean adminStatus);
	
	/**
	 * 新增管理员账号
	 * @param account
	 * @param password
	 * @param privil
	 * @param school
	 */
	void saveAdmin(String account,String password,Integer privil,String school);
}
