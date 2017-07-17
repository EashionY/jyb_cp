package cn.jyb.dao;

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
	public StudentSchedule findSchedule(String appoint_time,int coach_id,String subtype,int student_id);

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
	
	
}
