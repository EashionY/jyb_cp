package cn.jyb.entity;

import java.io.Serializable;

public class TeachField implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 434281937552607428L;

	private Integer fieldId;

    private Integer schoolId;

    private String fieldName;

    private String fieldAddress;

    private String fieldLon;

    private String fieldLat;

    private String fieldDistance;

    private String fieldImg;

    public Integer getFieldId() {
        return fieldId;
    }

    public void setFieldId(Integer fieldId) {
        this.fieldId = fieldId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldAddress() {
        return fieldAddress;
    }

    public void setFieldAddress(String fieldAddress) {
        this.fieldAddress = fieldAddress;
    }

    public String getFieldLon() {
        return fieldLon;
    }

    public void setFieldLon(String fieldLon) {
        this.fieldLon = fieldLon;
    }

    public String getFieldLat() {
        return fieldLat;
    }

    public void setFieldLat(String fieldLat) {
        this.fieldLat = fieldLat;
    }

    public String getFieldDistance() {
        return fieldDistance;
    }

    public void setFieldDistance(String fieldDistance) {
        this.fieldDistance = fieldDistance;
    }

    public String getFieldImg() {
        return fieldImg;
    }

    public void setFieldImg(String fieldImg) {
        this.fieldImg = fieldImg;
    }

	@Override
	public String toString() {
		return "TeachField [fieldId=" + fieldId + ", schoolId=" + schoolId + ", fieldName=" + fieldName
				+ ", fieldAddress=" + fieldAddress + ", fieldLon=" + fieldLon + ", fieldLat=" + fieldLat
				+ ", fieldDistance=" + fieldDistance + ", fieldImg=" + fieldImg + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fieldId == null) ? 0 : fieldId.hashCode());
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
		TeachField other = (TeachField) obj;
		if (fieldId == null) {
			if (other.fieldId != null)
				return false;
		} else if (!fieldId.equals(other.fieldId))
			return false;
		return true;
	}
    
}