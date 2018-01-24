package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;
/**
 * ��֤��
 * @author Eashion
 *
 */
public class VerCode implements Serializable {

	private static final long serialVersionUID = -3974175646993778749L;

	private int id;
	//�������ֻ���
	private String phone;
	//ע����֤��
	private String regcode;
	//ע����֤�뷢��ʱ��
	private Timestamp regcodeTime;
	//������֤��
	private String pwdcode;
	//������֤�뷢��ʱ��
	private Timestamp pwdcodeTime;
	//֧����֤��
	private String paycode;
	//֧����֤�뷢��ʱ��
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
