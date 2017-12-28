package cn.jyb.entity;

import java.math.BigDecimal;
import java.util.Date;

public class UserPosition {
    private Integer id;

    private Integer userId;//用户id

    private BigDecimal userLon;//经度

    private BigDecimal userLat;//纬度

    private Date locatime;//定位时间

    private String region;//地区

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getUserLon() {
        return userLon;
    }

    public void setUserLon(BigDecimal userLon) {
        this.userLon = userLon;
    }

    public BigDecimal getUserLat() {
        return userLat;
    }

    public void setUserLat(BigDecimal userLat) {
        this.userLat = userLat;
    }

    public Date getLocatime() {
        return locatime;
    }

    public void setLocatime(Date locatime) {
        this.locatime = locatime;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

	@Override
	public String toString() {
		return "UserPosition [id=" + id + ", userId=" + userId + ", userLon=" + userLon + ", userLat=" + userLat
				+ ", locatime=" + locatime + ", region=" + region + "]";
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
		UserPosition other = (UserPosition) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}