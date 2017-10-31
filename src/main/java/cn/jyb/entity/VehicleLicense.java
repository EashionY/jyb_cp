package cn.jyb.entity;

import java.util.Date;
/**
 * 行驶证
 * @author Eashion
 *
 */
public class VehicleLicense {
    private Integer id;

    private Integer userId;
    //车牌号
    private String vehicleNo;
    //车辆所有人
    private String vehicleOwner;
    //汽车品牌
    private String vehicleBrand;
    //车辆识别号
    private String vehicleVin;
    //发动机号
    private String engineNo;
    //行驶证照片
    private String vehicleLicensePic;

    private Date creatime;

    private Date passtime;
    //认证状态（0--审核中，1--认证成功，2--认证失败）
    private Integer vehicleLicenseStatus;

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

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(String vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleVin() {
        return vehicleVin;
    }

    public void setVehicleVin(String vehicleVin) {
        this.vehicleVin = vehicleVin;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getVehicleLicensePic() {
        return vehicleLicensePic;
    }

    public void setVehicleLicensePic(String vehicleLicensePic) {
        this.vehicleLicensePic = vehicleLicensePic;
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }

    public Date getPasstime() {
        return passtime;
    }

    public void setPasstime(Date passtime) {
        this.passtime = passtime;
    }

    public Integer getVehicleLicenseStatus() {
        return vehicleLicenseStatus;
    }

    public void setVehicleLicenseStatus(Integer vehicleLicenseStatus) {
        this.vehicleLicenseStatus = vehicleLicenseStatus;
    }

	@Override
	public String toString() {
		return "VehicleLicense [id=" + id + ", userId=" + userId + ", vehicleNo=" + vehicleNo + ", vehicleOwner="
				+ vehicleOwner + ", vehicleBrand=" + vehicleBrand + ", vehicleVin=" + vehicleVin + ", engineNo="
				+ engineNo + ", vehicleLicensePic=" + vehicleLicensePic + ", creatime=" + creatime + ", passtime="
				+ passtime + ", vehicleLicenseStatus=" + vehicleLicenseStatus + "]";
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
		VehicleLicense other = (VehicleLicense) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}