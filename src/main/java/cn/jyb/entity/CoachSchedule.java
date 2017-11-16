package cn.jyb.entity;
/**
 * 教练日程表
 * @author Eashion
 *
 */
public class CoachSchedule {
	
    private Integer id;

    private Integer coachId;

    private String appointTime;
    //时间段1(7:00~8:00)
  	private String time1;
  	//时间段2(8:20~9:20)
  	private String time2;
  	//时间段3(9:40~10:40)
  	private String time3;
  	//时间段4(11:00~12:00)
  	private String time4;
  	//时间段5(12:40~13:40)
  	private String time5;
  	//时间段6(14:00~15:00)
  	private String time6;
  	//时间段7(15:20~16:20)
  	private String time7;
  	//时间段8(16:40~17:40)
  	private String time8;
  	//时间段9(18:00~19:00)
  	private String time9;
  	//时间段10(19:20~20:20)
  	private String time10;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCoachId() {
        return coachId;
    }

    public void setCoachId(Integer coachId) {
        this.coachId = coachId;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
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
		return "CoachSchedule [id=" + id + ", coachId=" + coachId + ", appointTime=" + appointTime + ", time1=" + time1
				+ ", time2=" + time2 + ", time3=" + time3 + ", time4=" + time4 + ", time5=" + time5 + ", time6=" + time6
				+ ", time7=" + time7 + ", time8=" + time8 + ", time9=" + time9 + ", time10=" + time10 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}