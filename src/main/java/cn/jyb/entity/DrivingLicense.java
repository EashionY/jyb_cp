package cn.jyb.entity;

import java.util.Date;

/**
 * ��ʻ֤
 * @author Eashion
 *
 */
public class DrivingLicense {
	
    private Integer id;

    private Integer userId;

    private String driverName;
    //��ʻ֤��
    private String licenseNo;
    //������֤����
    private String issueDate;
    //׼�ݳ���
    private String drivingClass;
    //��ʻ֤��Ƭ
    private String drivingLicensePic;

    private Date creatime;

    private Date passtime;
    //��֤״̬��0--����У�1--��֤�ɹ���2--��֤ʧ�ܣ�
    private Integer drivingLicenseStatus;

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

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getDrivingClass() {
        return drivingClass;
    }

    public void setDrivingClass(String drivingClass) {
        this.drivingClass = drivingClass;
    }

    public String getDrivingLicensePic() {
        return drivingLicensePic;
    }

    public void setDrivingLicensePic(String drivingLicensePic) {
        this.drivingLicensePic = drivingLicensePic;
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

    public Integer getDrivingLicenseStatus() {
        return drivingLicenseStatus;
    }

    public void setDrivingLicenseStatus(Integer drivingLicenseStatus) {
        this.drivingLicenseStatus = drivingLicenseStatus;
    }

	@Override
	public String toString() {
		return "DrivingLicense [id=" + id + ", userId=" + userId + ", driverName=" + driverName + ", licenseNo="
				+ licenseNo + ", issueDate=" + issueDate + ", drivingClass=" + drivingClass + ", drivingLicensePic="
				+ drivingLicensePic + ", creatime=" + creatime + ", passtime=" + passtime + ", drivingLicenseStatus="
				+ drivingLicenseStatus + "]";
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
		DrivingLicense other = (DrivingLicense) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}