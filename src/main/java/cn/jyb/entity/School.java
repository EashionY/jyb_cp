package cn.jyb.entity;

import java.io.Serializable;

public class School implements Serializable{

	private static final long serialVersionUID = 2231589825842701047L;
	
	//��УID
	private int school_id;
	//��Уlogo·��
	private String school_logo;
	//���� ��У����   
	private String school_name;   
	//��У��ַ
	private String school_address;  
	//���ż�У������
	private String school_slogan;  
	//�ҵ�λ�õ����ż�У����
	private String school_distance;  
	//��У����
	private String school_jingdu;   
	//��Уγ��	
	private String school_weidu; 
	//��У���������   
	private int school_browse;  
	//��У�շ�
	private int school_price;  
	//��У�绰 
	private String school_tel;  
	//��У���ڵ���
	private String school_area; 
	//��У״̬��0δ����1����
	private Integer school_status;
	//��УӪҵִ��
	private String school_license;
	
	public int getSchool_id() {
		return school_id;
	}
	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	public String getSchool_logo() {
		return school_logo;
	}
	public void setSchool_logo(String school_logo) {
		this.school_logo = school_logo;
	}
	public String getSchool_name() {
		return school_name;
	}
	public void setSchool_name(String school_name) {
		this.school_name = school_name;
	}
	public String getSchool_address() {
		return school_address;
	}
	public void setSchool_address(String school_address) {
		this.school_address = school_address;
	}
	public String getSchool_slogan() {
		return school_slogan;
	}
	public void setSchool_slogan(String school_slogan) {
		this.school_slogan = school_slogan;
	}
	public String getSchool_distance() {
		return school_distance;
	}
	public void setSchool_distance(String school_distance) {
		this.school_distance = school_distance;
	}
	public String getSchool_jingdu() {
		return school_jingdu;
	}
	public void setSchool_jingdu(String school_jingdu) {
		this.school_jingdu = school_jingdu;
	}
	public String getSchool_weidu() {
		return school_weidu;
	}
	public void setSchool_weidu(String school_weidu) {
		this.school_weidu = school_weidu;
	}
	public int getSchool_browse() {
		return school_browse;
	}
	public void setSchool_browse(int school_browse) {
		this.school_browse = school_browse;
	}
	public int getSchool_price() {
		return school_price;
	}
	public void setSchool_price(int school_price) {
		this.school_price = school_price;
	}
	public String getSchool_tel() {
		return school_tel;
	}
	public void setSchool_tel(String school_tel) {
		this.school_tel = school_tel;
	}
	public String getSchool_area() {
		return school_area;
	}
	public void setSchool_area(String school_area) {
		this.school_area = school_area;
	}

	public Integer getSchool_status() {
		return school_status;
	}
	public void setSchool_status(Integer school_status) {
		this.school_status = school_status;
	}
	public String getSchool_license() {
		return school_license;
	}
	public void setSchool_license(String school_license) {
		this.school_license = school_license;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + school_id;
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
		School other = (School) obj;
		if (school_id != other.school_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "School [school_id=" + school_id + ", school_logo=" + school_logo + ", school_name=" + school_name
				+ ", school_address=" + school_address + ", school_slogan=" + school_slogan + ", school_distance="
				+ school_distance + ", school_jingdu=" + school_jingdu + ", school_weidu=" + school_weidu
				+ ", school_browse=" + school_browse + ", school_price=" + school_price + ", school_tel=" + school_tel
				+ ", school_area=" + school_area + ", school_status=" + school_status + ", school_license="
				+ school_license + "]";
	}

}
