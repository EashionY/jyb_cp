package cn.jyb.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.School;

public interface SchoolService {

	/**
	 * 更新驾校被浏览数
	 * @param school_id
	 * @return
	 */
	public boolean updateSchoolBrowse(String school_id);
	
	/**
	 * 按照距离排序查找驾校
	 * @param lon1 用户经度
	 * @param lat1 用户纬度
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<School> findSchoolByDistance(String lon1,String lat1,int page,int pageSize,String school_area);
	
	/**
	 * 按照浏览数排序查找驾校
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<School> findSchoolByBrowse(int page,int pageSize,String school_area);
	
	/**
	 * 添加驾校（后台管理系统）
	 * @param school_name
	 * @param school_address
	 * @param school_slogan
	 * @param school_jingdu
	 * @param school_weidu
	 * @param school_price
	 * @param school_tel
	 * @param school_area
	 * @param request
	 * @throws IOException 
	 */
	public boolean addSchool(String school_name,String school_address,String school_slogan,
			String school_jingdu,String school_weidu,String school_price,String school_tel,
			String school_area,HttpServletRequest request) throws IOException;
	
	/**
	 * 修改驾校信息（后台管理系统）
	 * @param school_id
	 * @param school_name
	 * @param school_address
	 * @param school_slogan
	 * @param school_jingdu
	 * @param school_weidu
	 * @param school_price
	 * @param school_tel
	 * @param school_area
	 * @return
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 */
	public boolean modifySchoolInfo(String school_id,String school_name,String school_address,String school_slogan,
			String school_jingdu,String school_weidu,String school_price,String school_tel,
			String school_area,HttpServletRequest req) throws UnsupportedEncodingException;
	
	/**
	 * 修改驾校logo(后台管理系统)
	 * @param request
	 * @param school_name
	 * @return
	 * @throws IOException
	 */
	public boolean modifySchoolLogo(HttpServletRequest request,String school_name) throws IOException;
	
	/**
	 * 通过驾校名搜索驾校（后台管理系统）
	 * @param school_name
	 * @return
	 */
	public List<School> searchSchool(String school_name);
	
	/**
	 * 查找某个地区的所有驾校（后台管理系统）
	 * @param page
	 * @param pageSize
	 * @param school_area
	 * @return
	 */
	public Map<String, Object> findAllSchool(int page,int pageSize,String school_area);
	
	/**
	 * 删除驾校（后台管理系统）
	 * @param school_id
	 * @return
	 */
	public boolean deleteSchool(String school_id);
}
