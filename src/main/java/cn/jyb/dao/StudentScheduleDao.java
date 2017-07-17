package cn.jyb.dao;

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
	public StudentSchedule findSchedule(String appoint_time,int coach_id,String subtype,int student_id);

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
	
	
}
