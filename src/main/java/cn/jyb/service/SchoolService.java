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
			String school_jingdu,String school_weidu,double school_price,String school_tel,
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
			String school_jingdu,String school_weidu,double school_price,String school_tel,
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
	 * 修改驾校的营业执照(后台管理系统)
	 * @param request
	 * @param school_name
	 * @return
	 * @throws IOException
	 */
	public boolean modifySchoolLicense(HttpServletRequest request,String school_name) throws IOException;
	
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
	public Map<String, Object> findAllSchool(int page,int pageSize,String school_area,Integer school_status);
	
	/**
	 * 删除驾校（后台管理系统）
	 * @param school_id
	 * @return
	 */
	public boolean deleteSchool(String school_id);
	
	/**
	 * 驾校详情
	 * @param school_id
	 * @return
	 */
	public Map<String,Object> schoolDetail(int school_id,Double lon1,Double lat1);
	
	/**
	 * 添加驾校练车环境(后台管理系统)
	 * @param request
	 * @param school_id
	 * @return
	 * @throws IOException 
	 */
	public boolean addEnvironment(HttpServletRequest request,int school_id) throws IOException;
	
	/**
	 * 添加驾校报名套餐(后台管理系统)
	 * @param school_id
	 * @param packageName
	 * @param packageType
	 * @param packageIntro
	 * @param packagePrice
	 * @param packageContent
	 * @return
	 */
	public boolean addPackage(Integer school_id,String packageName,String packageType,String packageIntro,String packagePrice,String packageContent);
	
	/**
	 * 删除报名套餐(后台管理系统)
	 * @param packageId
	 * @return
	 */
	public boolean deletePackage(int packageId);
	
	/**
	 * 修改报名套餐
	 * @param packageId
	 * @param school_id
	 * @param packageName
	 * @param packageType
	 * @param packageIntro
	 * @param packagePrice
	 * @param packageContent
	 * @return
	 */
	public boolean modifyPackage(int packageId,int school_id,String packageName,String packageType,String packageIntro,
			String packagePrice,String packageContent);
	
	/**
	 * 添加训练场地(后台管理系统)
	 * @param school_id
	 * @param fieldName
	 * @param fieldAddress
	 * @param fieldLon
	 * @param fieldLat
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public boolean addTeachField(Integer school_id,String school_name,String fieldName,String fieldAddress,String fieldLon,String fieldLat,HttpServletRequest request) throws IOException;
	
	/**
	 * 修改训练场地信息（后台管理系统）
	 * @param fieldId
	 * @param fieldName
	 * @param fieldAddress
	 * @param fieldLon
	 * @param fieldLat
	 * @return
	 */
	public boolean modifyTeachFieldInfo(Integer fieldId,String school_name,String fieldName,String fieldAddress,String fieldLon,String fieldLat,HttpServletRequest request) throws IOException;
	
	/**
	 * 删除训练场地(后台管理系统)
	 * @param fieldId
	 * @return
	 */
	public boolean deleteTeachField(Integer fieldId);
	
	/**
	 * 审核驾校（后台管理系统）
	 * @param school_id
	 * @param schoolStatus
	 * @return
	 */
	public Integer dealSchool(Integer school_id,Integer schoolStatus);
	
	/**
	 * 修改训练场地封面
	 * @param school_name
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	public boolean modifyTeachFieldImg(Integer fieldId, String school_name, HttpServletRequest request) throws IOException;
}
