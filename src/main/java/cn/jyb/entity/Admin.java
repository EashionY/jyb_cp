package cn.jyb.entity;

import java.util.Date;
/**
 * 后台管理系统管理员
 * @author Eashion
 *
 */
public class Admin {
    private Integer adminId;

    private String account;

    private String password;
    //管理员权限（1--驾校，2--普通管理员，9--超级管理员）
    private Integer privil;

    private Boolean adminStatus;

    private Date creatime;
    //驾校特有：驾校名
    private String school;

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPrivil() {
        return privil;
    }

    public void setPrivil(Integer privil) {
        this.privil = privil;
    }

    public Boolean getAdminStatus() {
        return adminStatus;
    }

    public void setAdminStatus(Boolean adminStatus) {
        this.adminStatus = adminStatus;
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", account=" + account + ", password=" + password + ", privil=" + privil
				+ ", adminStatus=" + adminStatus + ", creatime=" + creatime + ", school=" + school + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adminId == null) ? 0 : adminId.hashCode());
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
		Admin other = (Admin) obj;
		if (adminId == null) {
			if (other.adminId != null)
				return false;
		} else if (!adminId.equals(other.adminId))
			return false;
		return true;
	}
    
}