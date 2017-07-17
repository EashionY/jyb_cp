package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 朋友圈说说实体类
 * @author Eashion
 *
 */
public class Talks implements Serializable {

	private static final long serialVersionUID = -6785211091372294050L;

	private int id;
	//用户id
	private int user_id;
	//说说的具体内容
	private String talk;
	//创建时间
	private Timestamp creatime;
	//图片1路径
	private String imgpath1;
	//图片2路径
	private String imgpath2;
	//图片3路径
	private String imgpath3;
	//图片4路径
	private String imgpath4;
	//图片5路径
	private String imgpath5;
	//图片6路径
	private String imgpath6;
	//图片7路径
	private String imgpath7;
	//图片8路径
	private String imgpath8;
	//图片9路径
	private String imgpath9;
	
	public Talks() { }

	public Talks(int user_id, String talk, String imgpath1, String imgpath2, String imgpath3, String imgpath4,
			String imgpath5, String imgpath6, String imgpath7, String imgpath8, String imgpath9) {
		super();
		this.user_id = user_id;
		this.talk = talk;
		this.imgpath1 = imgpath1;
		this.imgpath2 = imgpath2;
		this.imgpath3 = imgpath3;
		this.imgpath4 = imgpath4;
		this.imgpath5 = imgpath5;
		this.imgpath6 = imgpath6;
		this.imgpath7 = imgpath7;
		this.imgpath8 = imgpath8;
		this.imgpath9 = imgpath9;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getTalk() {
		return talk;
	}

	public void setTalk(String talk) {
		this.talk = talk;
	}

	public Timestamp getCreatime() {
		return creatime;
	}

	public void setCreatime(Timestamp creatime) {
		this.creatime = creatime;
	}

	public String getImgpath1() {
		return imgpath1;
	}

	public void setImgpath1(String imgpath1) {
		this.imgpath1 = imgpath1;
	}

	public String getImgpath2() {
		return imgpath2;
	}

	public void setImgpath2(String imgpath2) {
		this.imgpath2 = imgpath2;
	}

	public String getImgpath3() {
		return imgpath3;
	}

	public void setImgpath3(String imgpath3) {
		this.imgpath3 = imgpath3;
	}

	public String getImgpath4() {
		return imgpath4;
	}

	public void setImgpath4(String imgpath4) {
		this.imgpath4 = imgpath4;
	}

	public String getImgpath5() {
		return imgpath5;
	}

	public void setImgpath5(String imgpath5) {
		this.imgpath5 = imgpath5;
	}

	public String getImgpath6() {
		return imgpath6;
	}

	public void setImgpath6(String imgpath6) {
		this.imgpath6 = imgpath6;
	}

	public String getImgpath7() {
		return imgpath7;
	}

	public void setImgpath7(String imgpath7) {
		this.imgpath7 = imgpath7;
	}

	public String getImgpath8() {
		return imgpath8;
	}

	public void setImgpath8(String imgpath8) {
		this.imgpath8 = imgpath8;
	}

	public String getImgpath9() {
		return imgpath9;
	}

	public void setImgpath9(String imgpath9) {
		this.imgpath9 = imgpath9;
	}

	@Override
	public String toString() {
		return "Talks [id=" + id + ", user_id=" + user_id + ", talk=" + talk + ", creatime=" + creatime + ", imgpath1="
				+ imgpath1 + ", imgpath2=" + imgpath2 + ", imgpath3=" + imgpath3 + ", imgpath4=" + imgpath4
				+ ", imgpath5=" + imgpath5 + ", imgpath6=" + imgpath6 + ", imgpath7=" + imgpath7 + ", imgpath8="
				+ imgpath8 + ", imgpath9=" + imgpath9 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Talks other = (Talks) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
