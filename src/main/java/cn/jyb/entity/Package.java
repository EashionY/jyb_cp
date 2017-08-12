package cn.jyb.entity;

import java.io.Serializable;

public class Package implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8657212428815138364L;

	private Integer packageId;

    private Integer schoolId;

    private String packageName;

    private String packageType;

    private String packageIntro;

    private String packagePrice;

    private String packageContent;

    public Integer getPackageId() {
        return packageId;
    }

    public void setPackageId(Integer packageId) {
        this.packageId = packageId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPackageIntro() {
        return packageIntro;
    }

    public void setPackageIntro(String packageIntro) {
        this.packageIntro = packageIntro;
    }

    public String getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(String packagePrice) {
        this.packagePrice = packagePrice;
    }

    public String getPackageContent() {
        return packageContent;
    }

    public void setPackageContent(String packageContent) {
        this.packageContent = packageContent;
    }

	@Override
	public String toString() {
		return "Package [packageId=" + packageId + ", schoolId=" + schoolId + ", packageName=" + packageName
				+ ", packageType=" + packageType + ", packageIntro=" + packageIntro + ", packagePrice=" + packagePrice
				+ ", packageContent=" + packageContent + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((packageId == null) ? 0 : packageId.hashCode());
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
		Package other = (Package) obj;
		if (packageId == null) {
			if (other.packageId != null)
				return false;
		} else if (!packageId.equals(other.packageId))
			return false;
		return true;
	}
    
}