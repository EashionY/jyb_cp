package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.CoachSchedule;

public interface CoachScheduleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CoachSchedule record);

    int insertSelective(CoachSchedule record);

    CoachSchedule selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CoachSchedule record);

    int updateByPrimaryKey(CoachSchedule record);
    
    /**
	 * 通过预约日期查找日程
	 * @param appointTime
	 * @param coachId
	 * @return coachSchedule
	 */
	public CoachSchedule findSchedule(String appointTime,Integer coachId);
	
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
	 * 通过预约日期查找日程
	 * @param appointTime
	 * @return
	 */
	public List<Map<String,Object>> findCoachScheduleByDate(String appointTime);
	
	/**
	 * 通过教练名查找日程
	 * @param coachName
	 * @return
	 */
	public List<Map<String,Object>> findCoachScheduleByName(String coachName);
}