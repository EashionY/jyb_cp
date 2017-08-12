package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

public interface TeachRecordService {

	/**
	 * 增加约教记录
	 * @param student_id
	 * @param coach_id
	 * @param coach_name
	 * @param teach_subject
	 * @param teach_time
	 * @param teach_field
	 * @param shuttle
	 * @param shuttle_time
	 * @param shuttle_place
	 * @param teach_state
	 * @param tips
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public boolean addTeachRecord(int student_id, int coach_id, String teach_subject, String teach_time,
			String teach_field, String shuttle, String shuttle_time, String shuttle_place, String tips) throws UnsupportedEncodingException;
	
	/**
	 * 学员的约教记录条数
	 * @param student_id
	 * @return
	 */
	public int findStudyRecordNumber(int student_id);
	
	/**
	 * 学员的所有约教记录
	 * @param student_id
	 * @return
	 */
	public List<Map<String, String>> findStudyRecords(int student_id,int page,int pageSize);
	
	/**
	 * 教练的所有教学记录条数
	 * @param coach_id
	 * @return
	 */
	public int findTeachRecordNumber(int coach_id);
	
	/**
	 * 教练的所有教学记录
	 * @param coach_id
	 * @return
	 */
	public List<Map<String, String>> findTeachRecords(int coach_id,int page,int pageSize);
	
	/**
	 * 为已完成的训练添加评价
	 * @param teach_id
	 * @param evaluation
	 * @param evaltype 评价类型
	 * @param evalstar 评价星数
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public boolean addEvaluation(int teach_id,String evaluation,int evaltype,int evalstar) throws UnsupportedEncodingException;
	
	/**
	 * 查找学员给出的所有评价数量
	 * @param student_id
	 * @return
	 */
	public Map<String, Integer> findStudyEvaluationNumber(int student_id);
	
	/**
	 * 查找学员给出的所有评价
	 * @param student_id
	 * @return
	 */
	public List<Map<String, String>> findStudyEvaluation(int student_id,int page,int pageSize);
	
	/**
	 * 查找教练收到的所有评价数量
	 * @param coach_id
	 * @return
	 */
	public Map<String, Integer> findTeachEvaluationNumber(int coach_id);
	
	/**
	 * 查找教练收到的好评/中评/差评
	 * @param coach_id
	 * @param evaltype 评价类型(全部评价=0，好评=1，中评=2，差评=3)
	 * @return
	 */
	public List<Map<String, String>> findTeachEvaluations(int coach_id,int evaltype,int page,int pageSize);
	/**
	 * 教练接受约教请求
	 * @param coach_id
	 * @return
	 */
	public boolean acceptTeach(int teach_id);
	
	/**
	 * 学员完成约教训练
	 * @param teach_id
	 * @return
	 */
	public boolean finishTeach(int teach_id);
	
	/**
	 * 教练拒绝约教请求
	 * @param teach_id
	 * @return
	 */
	public boolean refuseTeach(int teach_id);
	
	/**
	 * 学员取消约教请求
	 * @param teach_id
	 * @return
	 */
	public boolean cancelTeach(int teach_id);
	
	/**
	 * 列出所有约教记录(后台)
	 * @param page
	 * @param pageSize
	 * @return 
	 */
	public List<Map<String,Object>> listAllTeachRecord(int page,int pageSize);
	
	/**
	 * 通过教练名查找记录(后台)
	 * @param coach_name
	 * @return
	 */
	public List<Map<String,Object>> findRecordByCoachName(String coach_name);
	
	/**
	 * 通过学员名查找记录(后台)
	 * @param student_name
	 * @return
	 */
	public List<Map<String,Object>> findRecordByStudentName(String student_name);
	
	/**
	 * 通过约教时间查找记录(后台)
	 * @param teach_time
	 * @return
	 */
	public List<Map<String,Object>> findRecordByTeachTime(String teach_time);
	
	/**
	 * 查找该驾校所有教练收到的评价
	 * @param school_name
	 * @param evaltype
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> listSchoolEval(String school_name,int evaltype,int page,int pageSize);
}
