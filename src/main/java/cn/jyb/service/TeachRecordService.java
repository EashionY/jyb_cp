package cn.jyb.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import cn.jyb.entity.TeachRecord;

public interface TeachRecordService {

	/**
	 * ����Լ�̼�¼(ѧԱԤԼ����ʱ������Ӧʱ�ε�״̬��Ϊ1)
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
	public TeachRecord addTeachRecord(int student_id, int coach_id, String teach_subject, String teach_time,
			String teach_field, String shuttle, String shuttle_time, String shuttle_place, String tips);
	
	/**
	 * ѧԱ��Լ�̼�¼����
	 * @param student_id
	 * @return
	 */
	public int findStudyRecordNumber(int student_id);
	
	/**
	 * ѧԱ������Լ�̼�¼
	 * @param student_id
	 * @return
	 */
	public List<Map<String, Object>> findStudyRecords(int student_id,int page,int pageSize);
	
	/**
	 * ���������н�ѧ��¼����
	 * @param coach_id
	 * @return
	 */
	public int findTeachRecordNumber(int coach_id);
	
	/**
	 * ���������н�ѧ��¼
	 * @param coach_id
	 * @param teach_state ��ѧ״̬
	 * @param page ҳ��
	 * @param pageSize ÿҳ��ʾ����
	 * @return
	 */
	public List<Map<String, Object>> findTeachRecords(int coach_id,String teach_state,int page,int pageSize);
	
	/**
	 * �鿴���������������Լ�̼�¼
	 * @param coach_id
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> listDealedRecord(int coach_id,int page,int pageSize);
	
	/**
	 * Ϊ����ɵ�ѵ���������
	 * @param teach_id
	 * @param evaluation
	 * @param evaltype ��������
	 * @param evalstar ��������
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public boolean addEvaluation(String teach_id,String evaluation,int evaltype,int evalstar) throws UnsupportedEncodingException;
	
	/**
	 * ����ѧԱ������������������
	 * @param student_id
	 * @return
	 */
	public Map<String, Integer> findStudyEvaluationNumber(int student_id);
	
	/**
	 * ����ѧԱ��������������
	 * @param student_id
	 * @return
	 */
	public List<Map<String, String>> findStudyEvaluation(int student_id,int page,int pageSize);
	
	/**
	 * ���ҽ����յ���������������
	 * @param coach_id
	 * @return
	 */
	public Map<String, Integer> findTeachEvaluationNumber(int coach_id);
	
	/**
	 * ���ҽ����յ��ĺ���/����/����
	 * @param coach_id
	 * @param evaltype ��������(ȫ������=0������=1������=2������=3)
	 * @return
	 */
	public List<Map<String, String>> findTeachEvaluations(int coach_id,int evaltype,int page,int pageSize);
	
	/**
	 * ��������Լ������
	 * @param coach_id
	 * @return
	 */
	public boolean acceptTeach(String teach_id);
	
	/**
	 * ѧԱ���Լ��ѵ��
	 * @param teach_id
	 * @return
	 */
	public boolean finishTeach(String teach_id);
	
	/**
	 * �����ܾ�Լ������
	 * @param teach_id
	 * @return
	 */
	public boolean refuseTeach(String teach_id);
	
	/**
	 * ѧԱȡ��Լ������
	 * @param teach_id
	 * @return
	 */
	public boolean cancelTeach(String teach_id);
	
	/**
	 * �г�����Լ�̼�¼(��̨)
	 * @param page
	 * @param pageSize
	 * @return 
	 */
	public List<Map<String,Object>> listAllTeachRecord(String teach_subject,int page,int pageSize);
	
	/**
	 * ͨ�����������Ҽ�¼(��̨)
	 * @param coach_name
	 * @return
	 */
	public List<Map<String,Object>> findRecordByCoachName(String coach_name);
	
	/**
	 * ͨ��ѧԱ�����Ҽ�¼(��̨)
	 * @param student_name
	 * @return
	 */
	public List<Map<String,Object>> findRecordByStudentName(String student_name);
	
	/**
	 * ͨ��Լ��ʱ����Ҽ�¼(��̨)
	 * @param teach_time
	 * @return
	 */
	public List<Map<String,Object>> findRecordByTeachTime(String teach_time);
	
	/**
	 * ͨ��Լ�̿�Ŀ���Ҽ�¼(��̨)
	 * @param teach_subject
	 * @return
	 */
	public List<Map<String,Object>> findRecordBySubject(String teach_subject);
	
	/**
	 * ���Ҹü�У���н����յ�������
	 * @param school_name
	 * @param evaltype
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public Map<String, Object> listSchoolEval(String school_name,int evaltype,int page,int pageSize);
}
