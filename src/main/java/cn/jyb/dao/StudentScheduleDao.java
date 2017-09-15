package cn.jyb.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.StudentSchedule;

public interface StudentScheduleDao {

	/**
	 * 查询学员指定日期的日程
	 * @param appoint_time
	 * @param coach_id
	 * @param subtype
	 * @param student_id
	 * @return studentSchedule
	 */
	public StudentSchedule findSchedule(@Param("appoint_time")String appoint_time,@Param("coach_id")int coach_id,
			@Param("subtype")String subtype,@Param("student_id")int student_id);

	/**
	 * 保存学员日程表
	 * @param studentSchedule
	 * @return int
	 */
	public int saveStudentSchedule(StudentSchedule studentSchedule);
	
	/**
	 * 更新学员日程表
	 * @param studentSchedule
	 * @return int
	 */
	public int updateStudentSchedule(StudentSchedule studentSchedule);
	
	/**
	 * 查找某教练各个时段的预约人数
	 * @param coach_id
	 * @param appoint_time
	 * @return
	 */
	public Map<String,Integer> listTimeCount(@Param("coach_id")int coach_id,@Param("appoint_time")String appoint_time);
}
