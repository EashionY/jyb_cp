package cn.jyb.entity;

import java.util.Date;

public class HLBSure {
    private Integer id;

    private String hlbOrderNo;//������

    private String nearby;//��������˿͸�����0/1��

    private Date nearbyTime;//���︽����ʱ��

    private String aboard;//�˿�ȷ���ϳ���0/1��

    private Date aboardTime;

    private String start;//������ʼ�г̣�0/1��

    private Date startTime;

    private String pArrive;//�˿�ȷ�ϵ��0/1��

    private Date pArriveTime;

    private String dArrive;//����ȷ�ϵ��0/1��

    private Date dArriveTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHlbOrderNo() {
        return hlbOrderNo;
    }

    public void setHlbOrderNo(String hlbOrderNo) {
        this.hlbOrderNo = hlbOrderNo;
    }

    public String getNearby() {
        return nearby;
    }

    public void setNearby(String nearby) {
        this.nearby = nearby;
    }

    public Date getNearbyTime() {
        return nearbyTime;
    }

    public void setNearbyTime(Date nearbyTime) {
        this.nearbyTime = nearbyTime;
    }

    public String getAboard() {
        return aboard;
    }

    public void setAboard(String aboard) {
        this.aboard = aboard;
    }

    public Date getAboardTime() {
        return aboardTime;
    }

    public void setAboardTime(Date aboardTime) {
        this.aboardTime = aboardTime;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getpArrive() {
        return pArrive;
    }

    public void setpArrive(String pArrive) {
        this.pArrive = pArrive;
    }

    public Date getpArriveTime() {
        return pArriveTime;
    }

    public void setpArriveTime(Date pArriveTime) {
        this.pArriveTime = pArriveTime;
    }

    public String getdArrive() {
        return dArrive;
    }

    public void setdArrive(String dArrive) {
        this.dArrive = dArrive;
    }

    public Date getdArriveTime() {
        return dArriveTime;
    }

    public void setdArriveTime(Date dArriveTime) {
        this.dArriveTime = dArriveTime;
    }

	@Override
	public String toString() {
		return "HLBSure [id=" + id + ", hlbOrderNo=" + hlbOrderNo + ", nearby=" + nearby + ", nearbyTime=" + nearbyTime
				+ ", aboard=" + aboard + ", aboardTime=" + aboardTime + ", start=" + start + ", startTime=" + startTime
				+ ", pArrive=" + pArrive + ", pArriveTime=" + pArriveTime + ", dArrive=" + dArrive + ", dArriveTime="
				+ dArriveTime + "]";
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
		HLBSure other = (HLBSure) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}