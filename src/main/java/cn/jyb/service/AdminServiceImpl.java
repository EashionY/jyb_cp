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
			throw new AdminException("�˺Ų���Ϊ��");
		}
		if(password==null || password.trim().isEmpty()){
			throw new AdminException("���벻��Ϊ��");
		}
		Admin admin = adminMapper.findByAccount(account);
		if(admin==null){
			throw new AdminException("�˺Ŵ���");
		}
		if(!password.equals(admin.getPassword())){
			throw new PwdException("�������");
		}
		if(admin.getAdminStatus()==false){
			throw new AdminException("�˺��ѱ����ã�����ϵ��������Ա�ָ�ʹ��");
		}
		return admin;
	}

	public Admin modifyPwd(String account, String oldPassword, String newPassword) {
		if(account==null || account.trim().isEmpty()){
			throw new AdminException("�˺Ų���Ϊ��");
		}
		if(newPassword==null || newPassword.trim().isEmpty()){
			throw new AdminException("���벻��Ϊ��");
		}
		Admin admin = adminMapper.findByAccount(account);
		if(admin==null){
			throw new AdminException("�˺Ŵ���");
		}
		if(!admin.getPassword().equals(oldPassword)){
			throw new PwdException("�������");
		}
		admin.setPassword(newPassword);
		int i = adminMapper.updateByPrimaryKeySelective(admin);
		if(i != 1){
			throw new AdminException("�����޸�ʧ��");
		}
		return admin;
	}

	public List<Admin> listAdmins(Integer privil) {
		List<Admin> result;
		try {
			result = adminMapper.listAdmins(privil);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DataBaseException("���ݿ��쳣");
		}
		return result;
	}

	public void dealAdmin(Integer adminId, boolean adminStatus) {
		Admin admin = new Admin();
		admin.setAdminId(adminId);
		admin.setAdminStatus(adminStatus);
		int i = adminMapper.updateByPrimaryKeySelective(admin);
		if(i != 1){
			throw new AdminException("����ʧ��");
		}
	}

	public void saveAdmin(String account, String password, Integer privil, String school) {
		Admin admin = adminMapper.findByAccount(account);
		//�˺��Ѵ���
		if(admin != null){
			throw new AdminException("�˺��Ѵ���");
		}else{
			admin = new Admin();
			admin.setAccount(account);
			admin.setPassword(password);
			admin.setPrivil(privil);
			admin.setAdminStatus(true);
			admin.setSchool(school);
			int i = adminMapper.insertSelective(admin);
			if(i != 1){
				throw new AdminException("��������Աʧ��");
			}
		}
	}

	
}
