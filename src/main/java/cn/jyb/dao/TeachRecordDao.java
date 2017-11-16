package cn.jyb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.jyb.entity.TeachRecord;

public interface TeachRecordDao {

	/**
	 * ����Լ�̼�¼
	 * @param record
	 * @return
	 */
	public int saveTeachRecord(TeachRecord record);
	
	/**
	 * ͨ��Լ��id����Լ�̼�¼
	 * @param teachId
	 * @return
	 */
	public TeachRecord findByTeachId(String teach_id);
	
	/**
	 * ͨ��ѧԱid��������Լ�̼�¼��
	 * @param student_id
	 * @return
	 */
	public Integer findStudyRecordNumber(int student_id);
	
	/**
	 * ͨ������id�������н�ѧ��¼��(֧��״̬����Ϊ֧���ɹ�--1)
	 * @param coach_id
	 * @return
	 */
	public Integer findTeachRecordNumber(int coach_id);
	
	/**
	 * ͨ��ѧԱid��������Լ�̼�¼
	 * @param studentId
	 * @return
	 */
	public List<TeachRecord> findStudyRecords(@Param("student_id")int student_id,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * ͨ������id�������н�ѧ��¼(֧��״̬����Ϊ֧���ɹ�--1)
	 * @param coachId
	 * @param teach_state
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<TeachRecord> findTeachRecords(@Param("coach_id")int coach_id,@Param("teach_state")String teach_state,
			@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * �鿴�����������ȫ�����������������ȴ�ȷ�ϡ�ѧԱ��ȡ������״̬����Ķ�����
	 * @param coach_id
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<TeachRecord> listDealedRecord(@Param("coach_id")int coach_id,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * �������,�����ɺ�,״̬teach_state��Ϊ3
	 * @param teach_id
	 * @param evaluation
	 * @return
	 */
	public int addEvaluation(@Param("teach_id")String teach_id,@Param("evaluation")String evaluation,@Param("evaltype")int evaltype,@Param("evalstar")int evalstar);
	
	/**
	 * ѧԱ����������������
	 * @param student_id
	 * @return
	 */
	public Integer findStudyEvaluationNumber(int student_id);
	
	/**
	 * ����ѧԱ�����Ĳ�ͬ���͵�������
	 * @param student_id
	 * @param evaltype
	 * @return
	 */
	public Integer findStudyEvaluationNumByType(@Param("student_id")int student_id,@Param("evaltype")int evaltype);
	
	/**
	 * ����ѧԱ�������������ۣ�״̬teach_state������3
	 * @param student_id
	 * @offset ��ǰҳ���Ӧ��ƫ����
	 * @return
	 */
	public List<TeachRecord> findStudyEvaluation(@Param("student_id")int student_id,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * �����յ�������������
	 * @param coach_id
	 * @return
	 */
	public Integer findTeachEvaluationNumber(int coach_id);
	
	/**
	 * ���Ҳ�ͬ���͵�������
	 * @param evaltype
	 * @return
	 */
	public Integer findTeachEvaluationNumByType(@Param("coach_id")int coach_id,@Param("evaltype")int evaltype);
	
	/**
	 * ���ҽ����յ����������ۣ�״̬teach_state������3
	 * @param coach_id
	 * @return
	 */
	public List<TeachRecord> findTeachEvaluation(@Param("coach_id")int coach_id,@Param("offset")int offset,
			@Param("pageSize")int pageSize);
	
	/**
	 * ���ҽ����յ������к���/����/����
	 * @param coach_id ����id
	 * @param evaltype ��������
	 * @return
	 */
	public List<TeachRecord> findTeachEvaluations(@Param("coach_id")int coach_id,
			@Param("evaltype")int evaltype,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * ���Լ�̼�¼��״̬
	 * ��������Լ������(teach_state=1)
	 * ѧԱ���ѵ��(teach_state=2)
	 * �����ܾ�Լ������(teach_state=-1)
	 * ѧԱȡ��Լ������(teach_state=-2)
	 * @param teach_id
	 * @param teach_state
	 * @return
	 */
	public int updateTeachState(@Param("teach_id")String teach_id,@Param("teach_state")String teach_state);

	/**
	 * ��ý���������
	 * @param coach_id
	 * @return �������ۺ�����
	 */
	public double getCoachScore(int coach_id);
	
	/**
	 * �г�����Լ�̼�¼
	 * @param offset
	 * @param pageSize
	 * @return
	 */
	public List<TeachRecord> listAllTeachRecord(@Param("teach_subject")String teach_subject,@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * �������Լ�̼�¼����
	 * @return
	 */
	public int getTeachRecordNum(@Param("teach_subject")String teach_subject);
	
	/**
	 * ͨ�����������Ҽ�¼(��̨��ģ����ѯ)
	 * @param coach_name
	 * @return
	 */
	public List<Map<String,Object>> findRecordByCoachName(String coach_name);
	
	/**
	 * ͨ��ѧԱ�����Ҽ�¼(��̨��ģ����ѯ)
	 * @param student_name
	 * @return
	 */
	public List<Map<String,Object>> findRecordByStudentName(String student_name);
	
	/**
	 * ͨ��Լ��ʱ����Ҽ�¼(��̨��ģ����ѯ)
	 * @param teach_time
	 * @return
	 */
	public List<Map<String,Object>> findRecordByTeachTime(String teach_time);
	
	/**
	 * ͨ��Լ�̿�Ŀ���Ҽ�¼(��̨����Ŀ�����߿�Ŀ��)
	 * @param teach_subject
	 * @return
	 */
	public List<Map<String,Object>> findRecordBySubject(String teach_subject);

	/**
	 * �޸�Լ�̼�¼��֧��״̬��0->δ֧����1->֧���ɹ���
	 * @param teach_id
	 * @param payStatus
	 * @return
	 */
	public int updatePayStatus(@Param("teach_id")String teach_id,@Param("payStatus")int payStatus);
}
