package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 教练
 * @author Eashion
 *
 */
public class Coach implements Serializable{

	private static final long serialVersionUID = -7419367318134225806L;

	private int coach_id;
	private int user_id;
	//教练姓名
	private String coach_name;
	//驾校名
	private String school_name;
	//教练被浏览数
	private int coach_browse;
	//教练评分
	private double coach_score;
	//科目二收费
	private double coach_paying_two;
	//科目三收费
	private double coach_paying_three;
	//免费接送距离
	private int coach_freeshuttle;
	//教练身份证号
	private String coach_idcard;
	//教练身份证正面图像路径
	private String coach_idcardfront;
	//教练身份证背面图像路径
	private String coach_idcardback;
	//教练资格证图像路径
	private String coach_qualification;
	//教练驾照类型
	private String coach_license;
	//教练用车
	private String coach_car;
	//驾校地址
	private String school_address;
	//训练场地
	private String train_field;
	//场地经度
	private String field_jingdu;
	//场地纬度
	private String field_weidu;
	//教练训练场地与学员的距离
	private String distance;
	//驾校图片路径
	private String school_imgpath;
	//教练性别
	private String coach_sex;
	//教练生日
	private String coach_birthday;
	//教练所在地区
	private String coach_area;
	//教练资格审核状态(0审核中,1已通过,2未通过)
	private String coach_status;
	//申请时间
	private Timestamp applytime;
	//最后修改时间
	private Timestamp modifytime;
	
	public Coach() {}

	public int getCoach_id() {
		return coach_id;
	}

	public void setCoach_id(int coach_id) {
		this.coach_id = coach_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getCoach_name() {
		return coach_name;
	}

	public void setCoach_name(String coach_name) {
		this.coach_name = coach_name;
	}

	public String getSchool_name() {
		return school_name;
	}

	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}

	public int getCoach_browse() {
		return coach_browse;
	}

	public void setCoach_browse(int coach_browse) {
		this.coach_browse = coach_browse;
	}
	
	public double getCoach_score() {
		return coach_score;
	}

	public void setCoach_score(double coach_score) {
		this.coach_score = coach_score;
	}

	public double getCoach_paying_two() {
		return coach_paying_two;
	}

	public void setCoach_paying_two(double coach_paying_two) {
		this.coach_paying_two = coach_paying_two;
	}

	public double getCoach_paying_three() {
		return coach_paying_three;
	}

	public void setCoach_paying_three(double coach_paying_three) {
		this.coach_paying_three = coach_paying_three;
	}

	public int getCoach_freeshuttle() {
		return coach_freeshuttle;
	}

	public void setCoach_freeshuttle(int coach_freeshuttle) {
		this.coach_freeshuttle = coach_freeshuttle;
	}

	public String getCoach_idcard() {
		return coach_idcard;
	}

	public void setCoach_idcard(String coach_idcard) {
		this.coach_idcard = coach_idcard;
	}

	public String getCoach_idcardfront() {
		return coach_idcardfront;
	}

	public void setCoach_idcardfront(String coach_idcardfront) {
		this.coach_idcardfront = coach_idcardfront;
	}

	public String getCoach_idcardback() {
		return coach_idcardback;
	}

	public void setCoach_idcardback(String coach_idcardback) {
		this.coach_idcardback = coach_idcardback;
	}

	public String getCoach_qualification() {
		return coach_qualification;
	}

	public void setCoach_qualification(String coach_qualification) {
		this.coach_qualification = coach_qualification;
	}

	public String getCoach_license() {
		return coach_license;
	}

	public void setCoach_license(String coach_license) {
		this.coach_license = coach_license;
	}

	public String getCoach_car() {
		return coach_car;
	}

	public void setCoach_car(String coach_car) {
		this.coach_car = coach_car;
	}

	public String getSchool_address() {
		return school_address;
	}

	public void setSchool_address(String school_address) {
		this.school_address = school_address;
	}

	public String getTrain_field() {
		return train_field;
	}

	public void setTrain_field(String train_field) {
		this.train_field = train_field;
	}

	public String getField_jingdu() {
		return field_jingdu;
	}

	public void setField_jingdu(String field_jingdu) {
		this.field_jingdu = field_jingdu;
	}

	public String getField_weidu() {
		return field_weidu;
	}

	public void setField_weidu(String field_weidu) {
		this.field_weidu = field_weidu;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

	public String getSchool_imgpath() {
		return school_imgpath;
	}

	public void setSchool_imgpath(String school_imgpath) {
		this.school_imgpath = school_imgpath;
	}

	public String getCoach_sex() {
		return coach_sex;
	}

	public void setCoach_sex(String coach_sex) {
		this.coach_sex = coach_sex;
	}

	public String getCoach_birthday() {
		return coach_birthday;
	}

	public void setCoach_birthday(String coach_birthday) {
		this.coach_birthday = coach_birthday;
	}

	public String getCoach_area() {
		return coach_area;
	}

	public void setCoach_area(String coach_area) {
		this.coach_area = coach_area;
	}

	public String getCoach_status() {
		return coach_status;
	}

	public void setCoach_status(String coach_status) {
		this.coach_status = coach_status;
	}

	public Timestamp getApplytime() {
		return applytime;
	}

	public void setApplytime(Timestamp applytime) {
		this.applytime = applytime;
	}

	public Timestamp getModifytime() {
		return modifytime;
	}

	public void setModifytime(Timestamp modifytime) {
		this.modifytime = modifytime;
	}

	@Override
	public String toString() {
		return "Coach [coach_id=" + coach_id + ", user_id=" + user_id + ", coach_name=" + coach_name + ", school_name="
				+ school_name + ", coach_browse=" + coach_browse + ", coach_score=" + coach_score
				+ ", coach_paying_two=" + coach_paying_two + ", coach_paying_three=" + coach_paying_three
				+ ", coach_freeshuttle=" + coach_freeshuttle + ", coach_idcard=" + coach_idcard + ", coach_idcardfront="
				+ coach_idcardfront + ", coach_idcardback=" + coach_idcardback + ", coach_qualification="
				+ coach_qualification + ", coach_license=" + coach_license + ", coach_car=" + coach_car
				+ ", school_address=" + school_address + ", train_field=" + train_field + ", field_jingdu="
				+ field_jingdu + ", field_weidu=" + field_weidu + ", distance=" + distance + ", school_imgpath="
				+ school_imgpath + ", coach_sex=" + coach_sex + ", coach_birthday=" + coach_birthday + ", coach_area="
				+ coach_area + ", coach_status=" + coach_status + ", applytime=" + applytime + ", modifytime=" + modifytime
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + coach_id;
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
		Coach other = (Coach) obj;
		if (coach_id != other.coach_id)
			return false;
		return true;
	}

}
