package cn.jyb.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import cn.jyb.entity.Coach;

public interface CoachService {
	
	/**
	 * 通过名字搜索教练
	 * @param coach_name
	 * @return coach
	 */
	public List<Map<String,Object>> findCoachByName(String coach_name,String coach_area);
	
	/**
	 * 热门教练
	 * @return list
	 */
	public List<Map<String,Object>> hotCoach(int page,int pageSize,String coach_area);
	
	/**
	 * 教练资格申请
	 * @param user_id
	 * @param phone
	 * @param coach_name
	 * @param coach_sex
	 * @param coach_brithday
	 * @param school_name
	 * @param school_address
	 * @param train_field
	 * @param field_jingdu
	 * @param field_weidu
	 * @param coach_license
	 * @param request
	 * @return coach
	 * @throws IOException
	 */
	public Coach insertCoach(int user_id,String phone,String coach_name,String coach_sex,
			String coach_birthday,String school_name, String school_address,
			String train_field,String field_jingdu,String field_weidu,
			String coach_license,String coach_car,String coach_area,HttpServletRequest request) throws IOException;
	
	/**
	 * 教学设置
	 * @param coach_id
	 * @param coach_paying_two
	 * @param coach_paying_three
	 * @param coach_freeshuttle
	 * @return
	 */
	public boolean teachSet(String coach_id,String coach_paying_two,
			String coach_paying_three,String coach_freeshuttle);
	
	/**
	 * 更新教练被浏览数
	 * @param coach_id 教练id
	 * @return
	 */
	public boolean updateCoachBrowse(String coach_id);

	/**
	 * 列出所有教练(后台管理系统)
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> listAllCoach(int page,int pageSize);
	
	/**
	 * 根据状态查找对应的教练(后台管理系统)
	 * @param coach_status('0'审核中,'1'已通过,'2'未通过)
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> listCoachByStatus(String coach_status,int page,int pageSize);
	
	/**
	 * 操作教练资格申请(后台管理系统)
	 * @param coach_id
	 * @param coach_status
	 */
	public String dealCoach(String coach_id,String coach_status);
	
	/**
	 * 查找附近教练
	 * @param lon 用户经度
	 * @param lat 用户纬度
	 * @param page
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @param range
	 * @return 附近教练列表
	 */
	public List<Map<String,Object>> coachNearby(String lon,String lat,int page,int pageSize,String coach_area,
			String coach_sex,String range);
	
	/**
	 * 根据评分查找教练
	 * @param lon 用户经度
	 * @param lat 用户纬度
	 * @param page
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @param range
	 * @return
	 */
	public List<Map<String,Object>> listCoachByScore(String lon,String lat,int page,int pageSize,String coach_area,String coach_sex,String range);
	
	/**
	 * 默认排序查找教练
	 * @param lon 用户经度
	 * @param lat 用户纬度
	 * @param page
	 * @param pageSize
	 * @param coach_area
	 * @param coach_sex
	 * @param range
	 * @return
	 */
	public List<Map<String,Object>> listCoachDefault(String lon,String lat,int page,int pageSize,String coach_area,String coach_sex,String range);
}
