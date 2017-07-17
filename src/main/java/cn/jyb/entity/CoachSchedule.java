package cn.jyb.entity;

import java.io.Serializable;

public class CoachSchedule implements Serializable {

	private static final long serialVersionUID = -541527881657625603L;

	private int id;
	private int coach_id;
	//约教日期(年月日)
	private String appoint_time;
	//各个时段能否预约(-1不能预约，0可以预约)
	private String time1;
	private String time2;
	private String time3;
	private String time4;
	private String time5;
	private String time6;
	private String time7;
	private String time8;
	private String time9;
	private String time10;
	
	public CoachSchedule() { }

	public CoachSchedule(int id, int coach_id, String appoint_time, String time1, String time2, String time3,
			String time4, String time5, String time6, String time7, String time8, String time9, String time10) {
		super();
		this.id = id;
		this.coach_id = coach_id;
		this.appoint_time = appoint_time;
		this.time1 = time1;
		this.time2 = time2;
		this.time3 = time3;
		this.time4 = time4;
		this.time5 = time5;
		this.time6 = time6;
		this.time7 = time7;
		this.time8 = time8;
		this.time9 = time9;
		this.time10 = time10;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCoach_id() {
		return coach_id;
	}

	public void setCoach_id(int coach_id) {
		this.coach_id = coach_id;
	}

	public String getAppoint_time() {
		return appoint_time;
	}

	public void setAppoint_time(String appoint_time) {
		this.appoint_time = appoint_time;
	}

	public String getTime1() {
		return time1;
	}

	public void setTime1(String time1) {
		this.time1 = time1;
	}

	public String getTime2() {
		return time2;
	}

	public void setTime2(String time2) {
		this.time2 = time2;
	}

	public String getTime3() {
		return time3;
	}

	public void setTime3(String time3) {
		this.time3 = time3;
	}

	public String getTime4() {
		return time4;
	}

	public void setTime4(String time4) {
		this.time4 = time4;
	}

	public String getTime5() {
		return time5;
	}

	public void setTime5(String time5) {
		this.time5 = time5;
	}

	public String getTime6() {
		return time6;
	}

	public void setTime6(String time6) {
		this.time6 = time6;
	}

	public String getTime7() {
		return time7;
	}

	public void setTime7(String time7) {
		this.time7 = time7;
	}

	public String getTime8() {
		return time8;
	}

	public void setTime8(String time8) {
		this.time8 = time8;
	}

	public String getTime9() {
		return time9;
	}

	public void setTime9(String time9) {
		this.time9 = time9;
	}

	public String getTime10() {
		return time10;
	}

	public void setTime10(String time10) {
		this.time10 = time10;
	}

	@Override
	public String toString() {
		return "CoachSchedule [id=" + id + ", coach_id=" + coach_id + ", appoint_time=" + appoint_time + ", time1="
				+ time1 + ", time2=" + time2 + ", time3=" + time3 + ", time4=" + time4 + ", time5=" + time5 + ", time6="
				+ time6 + ", time7=" + time7 + ", time8=" + time8 + ", time9=" + time9 + ", time10=" + time10 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		CoachSchedule other = (CoachSchedule) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
