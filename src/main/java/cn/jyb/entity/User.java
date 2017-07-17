package cn.jyb.entity;

import java.io.Serializable;

public class User implements Serializable {
	
	private static final long serialVersionUID = 7672295578559051666L;
	//�û����
	private int user_id;
	//�ǳ�
	private String nickname;
	//�û��Ա�F��ʾŮ�ԣ�M��ʾ����
	private String sex;
	//����
	private String birthday;
	//�ֻ���
	private String phone;
	//����
	private String password;
	//��ַ
	private String address;
	//ͷ��洢·��
	private String imgpath;
	//�û�����ǩ��
	private String signature;
	//�û���ɫ��0��ʾѧԱ��1��ʾ������2��ʾ����
	private String role;
	//����
	private String xingzuo;
	//���
	private String height;
	//����
	private String weight;
	//����
	private String job;
	//нˮ
	private String salary;
	//��Ȥ����
	private String interest;
	//����
	private String region;
	
	public User() { }

	public User(int user_id, String nickname, String sex, String birthday, String phone, String password,
			String address, String imgpath, String signature, String role, String xingzuo, String height, String weight,
			String job, String salary, String interest, String region) {
		super();
		this.user_id = user_id;
		this.nickname = nickname;
		this.sex = sex;
		this.birthday = birthday;
		this.phone = phone;
		this.password = password;
		this.address = address;
		this.imgpath = imgpath;
		this.signature = signature;
		this.role = role;
		this.xingzuo = xingzuo;
		this.height = height;
		this.weight = weight;
		this.job = job;
		this.salary = salary;
		this.interest = interest;
		this.region = region;
	}

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

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", nickname=" + nickname + ", sex=" + sex + ", birthday=" + birthday
				+ ", phone=" + phone + ", password=" + password + ", address=" + address + ", imgpath=" + imgpath
				+ ", signature=" + signature + ", role=" + role + ", xingzuo=" + xingzuo + ", height=" + height
				+ ", weight=" + weight + ", job=" + job + ", salary=" + salary + ", interest=" + interest + ", region="
				+ region + "]";
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
