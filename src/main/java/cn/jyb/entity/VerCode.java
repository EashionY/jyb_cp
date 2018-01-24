package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * 验证码
 * @author Eashion
 *
 */
public class VerCode implements Serializable {

	private static final long serialVersionUID = -3974175646993778749L;

	private int id;
	//接收者手机号
	private String phone;
	//注册验证码
	private String regcode;
	//注册验证码发送时间
	private Timestamp regcodeTime;
	//改密验证码
	private String pwdcode;
	//改密验证码发送时间
	private Timestamp pwdcodeTime;
	//支付验证码
	private String paycode;
	//支付验证码发送时间
	private Timestamp paycodeTime;
	
	public VerCode() { }

	public VerCode(int id, String phone, String regcode, Timestamp regcodeTime, String pwdcode, Timestamp pwdcodeTime,
			String paycode, Timestamp paycodeTime) {
		super();
		this.id = id;
		this.phone = phone;
		this.regcode = regcode;
		this.regcodeTime = regcodeTime;
		this.pwdcode = pwdcode;
		this.pwdcodeTime = pwdcodeTime;
		this.paycode = paycode;
		this.paycodeTime = paycodeTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegcode() {
		return regcode;
	}

	public void setRegcode(String regcode) {
		this.regcode = regcode;
	}

	public Timestamp getRegcodeTime() {
		return regcodeTime;
	}

	public void setRegcodeTime(Timestamp regcodeTime) {
		this.regcodeTime = regcodeTime;
	}

	public String getPwdcode() {
		return pwdcode;
	}

	public void setPwdcode(String pwdcode) {
		this.pwdcode = pwdcode;
	}

	public Timestamp getPwdcodeTime() {
		return pwdcodeTime;
	}

	public void setPwdcodeTime(Timestamp pwdcodeTime) {
		this.pwdcodeTime = pwdcodeTime;
	}

	public String getPaycode() {
		return paycode;
	}

	public void setPaycode(String paycode) {
		this.paycode = paycode;
	}

	public Timestamp getPaycodeTime() {
		return paycodeTime;
	}

	public void setPaycodeTime(Timestamp paycodeTime) {
		this.paycodeTime = paycodeTime;
	}

	@Override
	public String toString() {
		return "VerCode [id=" + id + ", phone=" + phone + ", regcode=" + regcode + ", regcodeTime=" + regcodeTime
				+ ", pwdcode=" + pwdcode + ", pwdcodeTime=" + pwdcodeTime + ", paycode=" + paycode + ", paycodeTime="
				+ paycodeTime + "]";
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
		VerCode other = (VerCode) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
