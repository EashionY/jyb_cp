package cn.jyb.entity;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	
	private static final long serialVersionUID = 7672295578559051666L;
	//用户编号
	private int user_id;
	//昵称
	private String nickname;
	//用户性别，F表示女性，M表示男性
	private String sex;
	//生日
	private String birthday;
	//手机号
	private String phone;
	//密码
	private String password;
	//地址
	private String address;
	//头像存储路径
	private String imgpath;
	//用户个性签名
	private String signature;
	//用户角色，0表示学员，1表示教练，2表示车主
	private String role;
	//星座
	private String xingzuo;
	//身高
	private String height;
	//体重
	private String weight;
	//工作
	private String job;
	//薪水
	private String salary;
	//兴趣爱好
	private String interest;
	//地区
	private String region;
	//用户的二维码(包含用户id)
	private String qrImg;
	//注册时间
	private Date regTime;
	
	public int getUser_Id() {
		return user_id;
	}

	public void setUser_Id(int user_id) {
		this.user_id = user_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getImgpath() {
		return imgpath;
	}

	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getXingzuo() {
		return xingzuo;
	}

	public void setXingzuo(String xingzuo) {
		this.xingzuo = xingzuo;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getQrImg() {
		return qrImg;
	}

	public void setQrImg(String qrImg) {
		this.qrImg = qrImg;
	}

	public Date getRegTime() {
		return regTime;
	}

	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", nickname=" + nickname + ", sex=" + sex + ", birthday=" + birthday
				+ ", phone=" + phone + ", password=" + password + ", address=" + address + ", imgpath=" + imgpath
				+ ", signature=" + signature + ", role=" + role + ", xingzuo=" + xingzuo + ", height=" + height
				+ ", weight=" + weight + ", job=" + job + ", salary=" + salary + ", interest=" + interest + ", region="
				+ region + ", qrImg=" + qrImg + ", regTime=" + regTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + user_id;
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
		User other = (User) obj;
		if (user_id != other.user_id)
			return false;
		return true;
	}

}
