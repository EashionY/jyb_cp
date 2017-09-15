package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Student implements Serializable {

	private static final long serialVersionUID = -8540960307126460537L;

	private int student_id;
	private int user_id;
	private int school_id;
	//学员姓名
	private String student_name;
	//报考驾照类型
	private String student_license;
	//学员身份证号
	private String student_idcard;
	//推荐码
	private String student_recommend;
	//学员电话
	private String student_tel;
	//报名时间
	private Timestamp signup_time;
	//支付状态
	private Integer pay_status;
	//所报名驾校的套餐
	private String packageName;

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public String getStudent_license() {
		return student_license;
	}

	public void setStudent_license(String student_license) {
		this.student_license = student_license;
	}

	public String getStudent_idcard() {
		return student_idcard;
	}

	public void setStudent_idcard(String student_idcard) {
		this.student_idcard = student_idcard;
	}

	public String getStudent_recommend() {
		return student_recommend;
	}

	public void setStudent_recommend(String student_recommend) {
		this.student_recommend = student_recommend;
	}

	public String getStudent_tel() {
		return student_tel;
	}

	public void setStudent_tel(String student_tel) {
		this.student_tel = student_tel;
	}
	
	public Timestamp getSignup_time() {
		return signup_time;
	}

	public void setSignup_time(Timestamp signup_time) {
		this.signup_time = signup_time;
	}

	public Integer getPay_status() {
		return pay_status;
	}

	public void setPay_status(Integer pay_status) {
		this.pay_status = pay_status;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", user_id=" + user_id + ", school_id=" + school_id
				+ ", student_name=" + student_name + ", student_license=" + student_license + ", student_idcard="
				+ student_idcard + ", student_recommend=" + student_recommend + ", student_tel=" + student_tel
				+ ", signup_time=" + signup_time + ", pay_status=" + pay_status + ", packageName=" + packageName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + student_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (student_id != other.student_id)
			return false;
		return true;
	}
	
}
