package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.CoachSchedule;

public interface CoachScheduleDao {

	/**
	 * 保存教练预约时间日程
	 * @param coachSchedule
	 * @return int
	 */
	public int saveCoachSchedule(CoachSchedule coachSchedule);
	
	/**
	 * 教练修改预约时间日程
	 * @param coachSchedule
	 * @return int
	 */
	public int updateCoachSchedule(CoachSchedule coachSchedule);
	
	/**
	 * 通过预约日期查找日程
	 * @param appoint_time
	 * @param coach_id
	 * @return coachSchedule
	 */
	public CoachSchedule findSchedule(String appoint_time,int coach_id);
	
	/**
	 * 列出所有教练的日程安排
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<Map<String,Object>> listAllCoachSchedule(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * 获取教练日程表的总条数
	 * @return 
	 */
	public int getCoachScheduleNum();
	
	/**
	 * 通过教练名查找日程
	 * @param coach_name
	 * @return
	 */
	public List<Map<String,Object>> findCoachScheduleByName(String coach_name);
	
	/**
	 * 通过预约日期查找日程
	 * @param appoint_time
	 * @return
	 */
	public List<Map<String,Object>> findCoachScheduleByDate(String appoint_time);
	
	
}
