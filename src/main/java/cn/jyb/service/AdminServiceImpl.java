package cn.jyb.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.jyb.dao.AdminMapper;
import cn.jyb.entity.Admin;
import cn.jyb.exception.AdminException;
import cn.jyb.exception.DataBaseException;
import cn.jyb.exception.PwdException;
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminMapper adminMapper;
	
	public Admin login(String account, String password) {
		if(account==null || account.trim().isEmpty()){
			throw new AdminException("账号不能为空");
		}
		if(password==null || password.trim().isEmpty()){
			throw new AdminException("密码不能为空");
		}
		Admin admin = adminMapper.findByAccount(account);
		if(admin==null){
			throw new AdminException("账号错误");
		}
		if(!password.equals(admin.getPassword())){
			throw new PwdException("密码错误");
		}
		if(admin.getAdminStatus()==false){
			throw new AdminException("账号已被禁用，请联系超级管理员恢复使用");
		}
		return admin;
	}

	public Admin modifyPwd(String account, String oldPassword, String newPassword) {
		if(account==null || account.trim().isEmpty()){
			throw new AdminException("账号不能为空");
		}
		if(newPassword==null || newPassword.trim().isEmpty()){
			throw new AdminException("密码不能为空");
		}
		Admin admin = adminMapper.findByAccount(account);
		if(admin==null){
			throw new AdminException("账号错误");
		}
		if(!admin.getPassword().equals(oldPassword)){
			throw new PwdException("密码错误");
		}
		admin.setPassword(newPassword);
		int i = adminMapper.updateByPrimaryKeySelective(admin);
		if(i != 1){
			throw new AdminException("密码修改失败");
		}
		return admin;
	}

	public List<Admin> listAdmins(Integer privil) {
		List<Admin> result;
		try {
			result = adminMapper.listAdmins(privil);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("数据库异常");
		}
		return result;
	}

	public void dealAdmin(Integer adminId, boolean adminStatus) {
		Admin admin = new Admin();
		admin.setAdminId(adminId);
		admin.setAdminStatus(adminStatus);
		int i = adminMapper.updateByPrimaryKeySelective(admin);
		if(i != 1){
			throw new AdminException("处理失败");
		}
	}

	public void saveAdmin(String account, String password, Integer privil, String school) {
		Admin admin = adminMapper.findByAccount(account);
		//账号已存在
		if(admin != null){
			throw new AdminException("账号已存在");
		}else{
			admin = new Admin();
			admin.setAccount(account);
			admin.setPassword(password);
			admin.setPrivil(privil);
			admin.setAdminStatus(true);
			admin.setSchool(school);
			int i = adminMapper.insertSelective(admin);
			if(i != 1){
				throw new AdminException("新增管理员失败");
			}
		}
	}

	
}
