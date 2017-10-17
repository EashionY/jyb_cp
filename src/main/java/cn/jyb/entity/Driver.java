package cn.jyb.entity;

import java.util.Date;

public class Driver {
    private Integer driverId;

    private Integer userId;

    private String driverName;

    private String driverLicensePic;

    private String drivingLicensePic;

    private String carNo;

    private String carBrand;

    private Date driverRegtime;

    private Integer driverStatus;

    private String driverIdcard;

    private String driverLicenseDate;

    private String carOwner;

    private String drivingLicenseDate;

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverLicensePic() {
        return driverLicensePic;
    }

    public void setDriverLicensePic(String driverLicensePic) {
        this.driverLicensePic = driverLicensePic;
    }

    public String getDrivingLicensePic() {
        return drivingLicensePic;
    }

    public void setDrivingLicensePic(String drivingLicensePic) {
        this.drivingLicensePic = drivingLicensePic;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Date getDriverRegtime() {
        return driverRegtime;
    }

    public void setDriverRegtime(Date driverRegtime) {
        this.driverRegtime = driverRegtime;
    }

    public Integer getDriverStatus() {
        return driverStatus;
    }

    public void setDriverStatus(Integer driverStatus) {
        this.driverStatus = driverStatus;
    }

    public String getDriverIdcard() {
        return driverIdcard;
    }

    public void setDriverIdcard(String driverIdcard) {
        this.driverIdcard = driverIdcard;
    }

    public String getDriverLicenseDate() {
        return driverLicenseDate;
    }

    public void setDriverLicenseDate(String driverLicenseDate) {
        this.driverLicenseDate = driverLicenseDate;
    }

    public String getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }

    public String getDrivingLicenseDate() {
        return drivingLicenseDate;
    }

    public void setDrivingLicenseDate(String drivingLicenseDate) {
        this.drivingLicenseDate = drivingLicenseDate;
    }

	@Override
	public String toString() {
		return "Driver [driverId=" + driverId + ", userId=" + userId + ", driverName=" + driverName
				+ ", driverLicensePic=" + driverLicensePic + ", drivingLicensePic=" + drivingLicensePic + ", carNo="
				+ carNo + ", carBrand=" + carBrand + ", driverRegtime=" + driverRegtime + ", driverStatus="
				+ driverStatus + ", driverIdcard=" + driverIdcard + ", driverLicenseDate=" + driverLicenseDate
				+ ", carOwner=" + carOwner + ", drivingLicenseDate=" + drivingLicenseDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((driverId == null) ? 0 : driverId.hashCode());
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
		Driver other = (Driver) obj;
		if (driverId == null) {
			if (other.driverId != null)
				return false;
		} else if (!driverId.equals(other.driverId))
			return false;
		return true;
	}
    
}