package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class TeachRecord implements Serializable {

	private static final long serialVersionUID = -5451508181981234214L;
	
	//Լ�̼�¼id
	private int teach_id;
	//ѧԱid
	private int student_id;
	//����id
	private int coach_id;
	//Լ�̿�Ŀ
	private String teach_subject;
	//Լ��ʱ��
	private String teach_time;
	//Լ�̵ص�
	private String teach_field;
	//�Ƿ�����Ҫ����
	private String shuttle;
	//����ʱ��
	private String shuttle_time;
	//���͵ص�
	private String shuttle_place;
	//Լ��״̬(0�ȴ�ȷ�ϣ�1��ȷ�ϣ�2�����ѵ����3��������ۣ�-1�����ܾ�������-2ѧԱȡ������)
	private String teach_state;
	//����
	private String tips;
	//����
	private String evaluation;
	//����ʱ��
	private Timestamp evaltime;
	//�������ͣ���1����2����3��
	private int evaltype;
	//��������
	private int evalstar;
	//����ʱ��
	private Timestamp creatime;
	//���ѵ��ʱ��
	private Timestamp finishtime;
	
	public TeachRecord() {}

	public TeachRecord(int teach_id, int student_id, int coach_id, String teach_subject, String teach_time,
			String teach_field, String shuttle, String shuttle_time, String shuttle_place, String teach_state,
			String tips, String evaluation, Timestamp evaltime, int evaltype, int evalstar, Timestamp creatime,
			Timestamp finishtime) {
		super();
		this.teach_id = teach_id;
		this.student_id = student_id;
		this.coach_id = coach_id;
		this.teach_subject = teach_subject;
		this.teach_time = teach_time;
		this.teach_field = teach_field;
		this.shuttle = shuttle;
		this.shuttle_time = shuttle_time;
		this.shuttle_place = shuttle_place;
		this.teach_state = teach_state;
		this.tips = tips;
		this.evaluation = evaluation;
		this.evaltime = evaltime;
		this.evaltype = evaltype;
		this.evalstar = evalstar;
		this.creatime = creatime;
		this.finishtime = finishtime;
	}

	public int getTeach_id() {
		return teach_id;
	}

	public void setTeach_id(int teach_id) {
		this.teach_id = teach_id;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getCoach_id() {
		return coach_id;
	}

	public void setCoach_id(int coach_id) {
		this.coach_id = coach_id;
	}

	public String getTeach_subject() {
		return teach_subject;
	}

	public void setTeach_subject(String teach_subject) {
		this.teach_subject = teach_subject;
	}

	public String getTeach_time() {
		return teach_time;
	}

	public void setTeach_time(String teach_time) {
		this.teach_time = teach_time;
	}

	public String getTeach_field() {
		return teach_field;
	}

	public void setTeach_field(String teach_field) {
		this.teach_field = teach_field;
	}

	public String getShuttle() {
		return shuttle;
	}

	public void setShuttle(String shuttle) {
		this.shuttle = shuttle;
	}

	public String getShuttle_time() {
		return shuttle_time;
	}

	public void setShuttle_time(String shuttle_time) {
		this.shuttle_time = shuttle_time;
	}

	public String getShuttle_place() {
		return shuttle_place;
	}

	public void setShuttle_place(String shuttle_place) {
		this.shuttle_place = shuttle_place;
	}

	public String getTeach_state() {
		return teach_state;
	}

	public void setTeach_state(String teach_state) {
		this.teach_state = teach_state;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getEvaluation() {
		return evaluation;
	}

	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}

	public Timestamp getEvaltime() {
		return evaltime;
	}

	public void setEvaltime(Timestamp evaltime) {
		this.evaltime = evaltime;
	}

	public int getEvaltype() {
		return evaltype;
	}

	public void setEvaltype(int evaltype) {
		this.evaltype = evaltype;
	}

	public int getEvalstar() {
		return evalstar;
	}

	public void setEvalstar(int evalstar) {
		this.evalstar = evalstar;
	}

	public Timestamp getCreatime() {
		return creatime;
	}

	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}

	public Timestamp getFinishtime() {
		return finishtime;
	}

	public void setFinishtime(Timestamp finishtime) {
		this.finishtime = finishtime;
	}

	@Override
	public String toString() {
		return "TeachRecord [teach_id=" + teach_id + ", student_id=" + student_id + ", coach_id=" + coach_id
				+ ", teach_subject=" + teach_subject + ", teach_time=" + teach_time + ", teach_field=" + teach_field
				+ ", shuttle=" + shuttle + ", shuttle_time=" + shuttle_time + ", shuttle_place=" + shuttle_place
				+ ", teach_state=" + teach_state + ", tips=" + tips + ", evaluation=" + evaluation + ", evaltime="
				+ evaltime + ", evaltype=" + evaltype + ", evalstar=" + evalstar + ", creatime=" + creatime
				+ ", finishtime=" + finishtime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + teach_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeachRecord other = (TeachRecord) obj;
		if (teach_id != other.teach_id)
			return false;
		return true;
	}
	
}