package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.School;

public interface SchoolDao {

	/**
	 * 保存驾校信息
	 * @param school
	 */
	public void save(School school);
	
	/**
	 * 更新驾校浏览数
	 * @param school_id
	 */
	public void updateSchoolBrowse(String school_id);
	
	/**
	 * 更新驾校与用户之间的距离
	 * @param school_id
	 * @param distance
	 */
	public void updateSchoolDistance(String school_id,String distance);
	
	/**
	 * 查询所有驾校的经纬度
	 * @return
	 */
	public List<School> findJWdu();
	
	/**
	 * 按照距离排序查找驾校
	 * @param offset
	 * @param pageSize
	 * @param school_area 驾校所在区域
	 * @return
	 */
	public List<School> findSchoolByDistance(int offset,int pageSize,String school_area);
	
	/**
	 * 查找驾校，默认排序
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<School> findSchool(@Param("offset")int offset,@Param("pageSize")int pageSize,
			@Param("school_area")String school_area,@Param("school_status")Integer school_status);
	
	/**
	 * 按照浏览数排序查找驾校
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<School> findSchoolByBrowse(int offset,int pageSize,String school_area);
	
	/**
	 * 通过驾校名查找
	 * @param school_name
	 * @return
	 */
	public School findSchoolByName(String school_name);
	
	/**
	 * 修改驾校信息
	 * @param school
	 * @return
	 */
	public int modifySchoolInfo(School school);

	/**
	 * 通过驾校名字模糊查询
	 * @param school_name
	 * @return
	 */
	public List<School> searchSchoolByName(String school_name);
	
	/**
	 * 删除驾校
	 * @param school_id
	 * @return
	 */
	public int deleteSchool(String school_id);
	
	/**
	 * 获取某个地区驾校数量
	 * @param school_area
	 * @return
	 */
	public int getSchoolNum(@Param("school_area")String school_area,@Param("school_status")Integer school_status);

	/**
	 * 修改驾校logo
	 * @param school_logo
	 * @param school_name
	 * @return
	 */
	public int modifySchoolLogo(@Param("school_logo")String school_logo,@Param("school_name")String school_name);
	
	/**
	 * 某驾校详情
	 * @param school_id
	 * @return
	 */
	public Map<String,Object> schoolDetail(int school_id);
	
	/**
	 * 修改驾校的营业执照
	 * @param school_license
	 * @param school_name
	 * @return
	 */
	public int modifySchoolLicense(@Param("school_license")String school_license,@Param("school_name")String school_name);
	
	/**
	 * 根据主键选择性更新数据
	 * @param school
	 * @return
	 */
	public int updateByPrimaryKeySelective(School school);
	/**
	 * 通过学校id查找学校
	 * @param school_id
	 * @return
	 */
	public School findById(Integer school_id);
}
