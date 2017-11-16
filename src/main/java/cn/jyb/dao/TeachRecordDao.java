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
	public TeachRecord findByTeachId(String teach_id);
	
	/**
	 * 通过学员id查找所有约教记录数
	 * @param student_id
	 * @return
	 */
	public Integer findStudyRecordNumber(int student_id);
	
	/**
	 * 通过教练id查找所有教学记录数(支付状态必须为支付成功--1)
	 * @param coach_id
	 * @return
	 */
	public Integer findTeachRecordNumber(int coach_id);
	
	/**
	 * 通过学员id查找所有约教记录
	 * @param studentId
	 * @return
	 */
	public List<TeachRecord> findStudyRecords(@Param("student_id")int student_id,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * 通过教练id查找所有教学记录(支付状态必须为支付成功--1)
	 * @param coachId
	 * @param teach_state
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<TeachRecord> findTeachRecords(@Param("coach_id")int coach_id,@Param("teach_state")String teach_state,
			@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * 查看教练处理过的全部订单（即不包括等待确认、学员已取消订单状态以外的订单）
	 * @param coach_id
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<TeachRecord> listDealedRecord(@Param("coach_id")int coach_id,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * 添加评价,添加完成后,状态teach_state变为3
	 * @param teach_id
	 * @param evaluation
	 * @return
	 */
	public int addEvaluation(@Param("teach_id")String teach_id,@Param("evaluation")String evaluation,@Param("evaltype")int evaltype,@Param("evalstar")int evalstar);
	
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
	public List<TeachRecord> findStudyEvaluation(@Param("student_id")int student_id,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
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
	 * 变更约教记录的状态
	 * 教练接受约教请求(teach_state=1)
	 * 学员完成训练(teach_state=2)
	 * 教练拒绝约教请求(teach_state=-1)
	 * 学员取消约教请求(teach_state=-2)
	 * @param teach_id
	 * @param teach_state
	 * @return
	 */
	public int updateTeachState(@Param("teach_id")String teach_id,@Param("teach_state")String teach_state);

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
	public List<TeachRecord> listAllTeachRecord(@Param("teach_subject")String teach_subject,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * 获得所有约教记录条数
	 * @return
	 */
	public int getTeachRecordNum(@Param("teach_subject")String teach_subject);
	
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
	
	/**
	 * 通过约教科目查找记录(后台，科目二或者科目三)
	 * @param teach_subject
	 * @return
	 */
	public List<Map<String,Object>> findRecordBySubject(String teach_subject);

	/**
	 * 修改约教记录的支付状态（0->未支付，1->支付成功）
	 * @param teach_id
	 * @param payStatus
	 * @return
	 */
	public int updatePayStatus(@Param("teach_id")String teach_id,@Param("payStatus")int payStatus);
}
