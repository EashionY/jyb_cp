package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 云购记录
 * @author Eashion
 *
 */
public class CloudBuy implements Serializable{

	private static final long serialVersionUID = 2361809863018431566L;
	
	private int id;
	//用户id
	private int user_id;
	//商品id
	private int goods_id;
	//用户云购码
	private int buyer_code;
	//期数
	private int period;
	//用户购买金额
	private int buy_amount;
	//用户购买时间
	private Timestamp buy_time;
	
	public CloudBuy() {	}

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

	public int getBuyer_code() {
		return buyer_code;
	}

	public void setBuyer_code(int buyer_code) {
		this.buyer_code = buyer_code;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public int getBuy_amount() {
		return buy_amount;
	}

	public void setBuy_amount(int buy_amount) {
		this.buy_amount = buy_amount;
	}

	public Timestamp getBuy_time() {
		return buy_time;
	}

	public void setBuy_time(Timestamp buy_time) {
		this.buy_time = buy_time;
	}

	@Override
	public String toString() {
		return "CloudBuy [id=" + id + ", user_id=" + user_id + ", goods_id=" + goods_id + ", buyer_code=" + buyer_code
				+ ", period=" + period + ", buy_amount=" + buy_amount + ", buy_time=" + buy_time + "]";
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
		CloudBuy other = (CloudBuy) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
