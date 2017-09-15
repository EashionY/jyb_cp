package cn.jyb.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.StudentSchedule;

public interface StudentScheduleDao {

	/**
	 * ��ѯѧԱָ�����ڵ��ճ�
	 * @param appoint_time
	 * @param coach_id
	 * @param subtype
	 * @param student_id
	 * @return studentSchedule
	 */
	public StudentSchedule findSchedule(@Param("appoint_time")String appoint_time,@Param("coach_id")int coach_id,
			@Param("subtype")String subtype,@Param("student_id")int student_id);

	/**
	 * ����ѧԱ�ճ̱�
	 * @param studentSchedule
	 * @return int
	 */
	public int saveStudentSchedule(StudentSchedule studentSchedule);
	
	/**
	 * ����ѧԱ�ճ̱�
	 * @param studentSchedule
	 * @return int
	 */
	public int updateStudentSchedule(StudentSchedule studentSchedule);
	
	/**
	 * ����ĳ��������ʱ�ε�ԤԼ����
	 * @param coach_id
	 * @param appoint_time
	 * @return
	 */
	public Map<String,Integer> listTimeCount(@Param("coach_id")int coach_id,@Param("appoint_time")String appoint_time);
}
