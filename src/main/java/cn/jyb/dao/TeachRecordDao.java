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
	public TeachRecord findByTeachId(int teach_id);
	
	/**
	 * ͨ��ѧԱid��������Լ�̼�¼��
	 * @param student_id
	 * @return
	 */
	public Integer findStudyRecordNumber(int student_id);
	
	/**
	 * ͨ������id�������н�ѧ��¼��
	 * @param coach_id
	 * @return
	 */
	public Integer findTeachRecordNumber(int coach_id);
	
	/**
	 * ͨ��ѧԱid��������Լ�̼�¼
	 * @param studentId
	 * @return
	 */
	public List<TeachRecord> findStudyRecords(int student_id,int offset,int pageSize);
	
	/**
	 * ͨ������id�������н�ѧ��¼
	 * @param coachId
	 * @return
	 */
	public List<TeachRecord> findTeachRecords(int coach_id,int offset,int pageSize);
	
	/**
	 * �������,�����ɺ�,״̬teach_state��Ϊ3
	 * @param teach_id
	 * @param evaluation
	 * @return
	 */
	public int addEvaluation(int teach_id,String evaluation,int evaltype,int evalstar);
	
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
	public List<TeachRecord> findStudyEvaluation(int student_id,int offset,int pageSize);
	
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
	 * ��������Լ������(teach_state=1)
	 * @param teach_id
	 * @return
	 */
	public int acceptTeach(int teach_id);
	
	/**
	 * ѧԱ���ѵ��(teach_id=2)
	 * @param teach_id
	 * @return
	 */
	public int finishTeach(int teach_id);
	
	/**
	 * �����ܾ�Լ������(teach_id=-1)
	 * @param teach_id
	 * @return
	 */
	public int refuseTeach(int teach_id);
	
	/**
	 * ѧԱȡ��Լ������(teach_id=-2)
	 * @param teach_id
	 * @return
	 */
	public int cancelTeach(int teach_id);

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
	public List<TeachRecord> listAllTeachRecord(@Param("offset")int offset,@Param("pageSize")int pageSize);
	
	/**
	 * �������Լ�̼�¼����
	 * @return
	 */
	public int getTeachRecordNum();
	
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
}
