package cn.jyb.dao;

import java.util.List;

import cn.jyb.entity.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    Admin findByAccount(String account);
    
    List<Admin> listAdmins(Integer privil);
}