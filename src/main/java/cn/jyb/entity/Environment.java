package cn.jyb.entity;

import java.io.Serializable;
/**
 * 驾校教学环境
 * @author Eashion
 *
 */
public class Environment implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3367380468050816408L;

	private Integer environmentId;

    private Integer schoolId;

    private String img1;

    private String img2;

    private String img3;

    private String img4;

    private String img5;

    private String img6;

    private String img7;

    private String img8;

    private String img9;

    private String img10;

    private String img11;

    private String img12;

    public Integer getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(Integer environmentId) {
        this.environmentId = environmentId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public String getImg3() {
        return img3;
    }

    public void setImg3(String img3) {
        this.img3 = img3;
    }

    public String getImg4() {
        return img4;
    }

    public void setImg4(String img4) {
        this.img4 = img4;
    }

    public String getImg5() {
        return img5;
    }

    public void setImg5(String img5) {
        this.img5 = img5;
    }

    public String getImg6() {
        return img6;
    }

    public void setImg6(String img6) {
        this.img6 = img6;
    }

    public String getImg7() {
        return img7;
    }

    public void setImg7(String img7) {
        this.img7 = img7;
    }

    public String getImg8() {
        return img8;
    }

    public void setImg8(String img8) {
        this.img8 = img8;
    }

    public String getImg9() {
        return img9;
    }

    public void setImg9(String img9) {
        this.img9 = img9;
    }

    public String getImg10() {
        return img10;
    }

    public void setImg10(String img10) {
        this.img10 = img10;
    }

    public String getImg11() {
        return img11;
    }

    public void setImg11(String img11) {
        this.img11 = img11;
    }

    public String getImg12() {
        return img12;
    }

    public void setImg12(String img12) {
        this.img12 = img12;
    }

	@Override
	public String toString() {
		return "Environment [environmentId=" + environmentId + ", schoolId=" + schoolId + ", img1=" + img1 + ", img2="
				+ img2 + ", img3=" + img3 + ", img4=" + img4 + ", img5=" + img5 + ", img6=" + img6 + ", img7=" + img7
				+ ", img8=" + img8 + ", img9=" + img9 + ", img10=" + img10 + ", img11=" + img11 + ", img12=" + img12
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((environmentId == null) ? 0 : environmentId.hashCode());
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
		Environment other = (Environment) obj;
		if (environmentId == null) {
			if (other.environmentId != null)
				return false;
		} else if (!environmentId.equals(other.environmentId))
			return false;
		return true;
	}
    
}