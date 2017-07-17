package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ÔÆ¹ºÖÐ½±¼ÇÂ¼
 * @author Eashion
 *
 */
public class CloudLottery implements Serializable {

	private static final long serialVersionUID = -6101777386717454113L;

	private int id;
	private int user_id;
	private int goods_id;
	//ÖÐ½±ÔÆ¹ºÂë
	private int lottery_code;
	//ÔÆ¹ºÆÚÊý
	private int period;
	//½ÒÏþÊ±¼ä
	private Timestamp announce_time;
	
	public CloudLottery() { }

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

	public int getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}

	public int getLottery_code() {
		return lottery_code;
	}

	public void setLottery_code(int lottery_code) {
		this.lottery_code = lottery_code;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public Timestamp getAnnounce_time() {
		return announce_time;
	}

	public void setAnnounce_time(Timestamp announce_time) {
		this.announce_time = announce_time;
	}

	@Override
	public String toString() {
		return "CloudLottery [id=" + id + ", user_id=" + user_id + ", goods_id=" + goods_id + ", lottery_code="
				+ lottery_code + ", period=" + period + ", announce_time=" + announce_time + "]";
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
		CloudLottery other = (CloudLottery) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
