package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.TeachRecord;

public interface TeachRecordDao {

	/**
	 * 增加约教记录
	 * @param record
	 * @return
	 */
	public int saveTeachRecord(TeachRecord record);
	
	/**
	 * 通过约教id查找约教记录
	 * @param teachId
	 * @return
	 */
	public TeachRecord findByTeachId(int teach_id);
	
	/**
	 * 通过学员id查找所有约教记录数
	 * @param student_id
	 * @return
	 */
	public Integer findStudyRecordNumber(int student_id);
	
	/**
	 * 通过教练id查找所有教学记录数
	 * @param coach_id
	 * @return
	 */
	public Integer findTeachRecordNumber(int coach_id);
	
	/**
	 * 通过学员id查找所有约教记录
	 * @param studentId
	 * @return
	 */
	public List<TeachRecord> findStudyRecords(int student_id,int offset,int pageSize);
	
	/**
	 * 通过教练id查找所有教学记录
	 * @param coachId
	 * @return
	 */
	public List<TeachRecord> findTeachRecords(int coach_id,int offset,int pageSize);
	
	/**
	 * 添加评价,添加完成后,状态teach_state变为3
	 * @param teach_id
	 * @param evaluation
	 * @return
	 */
	public int addEvaluation(int teach_id,String evaluation,int evaltype,int evalstar);
	
	/**
	 * 学员做出的所有评价数
	 * @param student_id
	 * @return
	 */
	public Integer findStudyEvaluationNumber(int student_id);
	
	/**
	 * 查找学员做出的不同类型的评价数
	 * @param student_id
	 * @param evaltype
	 * @return
	 */
	public Integer findStudyEvaluationNumByType(@Param("student_id")int student_id,@Param("evaltype")int evaltype);
	
	/**
	 * 查找学员给出的所有评价，状态teach_state必须是3
	 * @param student_id
	 * @offset 当前页码对应的偏移量
	 * @return
	 */
	public List<TeachRecord> findStudyEvaluation(int student_id,int offset,int pageSize);
	
	/**
	 * 教练收到的所有评价数
	 * @param coach_id
	 * @return
	 */
	public Integer findTeachEvaluationNumber(int coach_id);
	
	/**
	 * 查找不同类型的评价数
	 * @param evaltype
	 * @return
	 */
	public Integer findTeachEvaluationNumByType(@Param("coach_id")int coach_id,@Param("evaltype")int evaltype);
	
	/**
	 * 查找教练收到的所有评价，状态teach_state必须是3
	 * @param coach_id
	 * @return
	 */
	public List<TeachRecord> findTeachEvaluation(@Param("coach_id")int coach_id,@Param("offset")int offset,
			@Param("pageSize")int pageSize);
	
	/**
	 * 查找教练收到的所有好评/中评/差评
	 * @param coach_id 教练id
	 * @param evaltype 评价类型
	 * @return
	 */
	public List<TeachRecord> findTeachEvaluations(@Param("coach_id")int coach_id,
			@Param("evaltype")int evaltype,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * 教练接受约教请求(teach_state=1)
	 * @param teach_id
	 * @return
	 */
	public int acceptTeach(int teach_id);
	
	/**
	 * 学员完成训练(teach_id=2)
	 * @param teach_id
	 * @return
	 */
	public int finishTeach(int teach_id);
	
	/**
	 * 教练拒绝约教请求(teach_id=-1)
	 * @param teach_id
	 * @return
	 */
	public int refuseTeach(int teach_id);
	
	/**
	 * 学员取消约教请求(teach_id=-2)
	 * @param teach_id
	 * @return
	 */
	public int cancelTeach(int teach_id);

	/**
	 * 获得教练的评分
	 * @param coach_id
	 * @return 教练的综合评分
	 */
	public double getCoachScore(int coach_id);
	
	/**
	 * 列出所有约教记录
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<TeachRecord> listAllTeachRecord(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * 获得所有约教记录条数
	 * @return
	 */
	public int getTeachRecordNum();
	
	/**
	 * 通过教练名查找记录(后台，模糊查询)
	 * @param coach_name
	 * @return
	 */
	public List<Map<String,Object>> findRecordByCoachName(String coach_name);
	
	/**
	 * 通过学员名查找记录(后台，模糊查询)
	 * @param student_name
	 * @return
	 */
	public List<Map<String,Object>> findRecordByStudentName(String student_name);
	
	/**
	 * 通过约教时间查找记录(后台，模糊查询)
	 * @param teach_time
	 * @return
	 */
	public List<Map<String,Object>> findRecordByTeachTime(String teach_time);
}
