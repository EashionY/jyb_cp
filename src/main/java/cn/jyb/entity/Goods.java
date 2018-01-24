package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 云购平台商品
 * @author Eashion
 *
 */
public class Goods implements Serializable {

	private static final long serialVersionUID = 2411294293126520840L;
	
	private int id;
	//商品名
	private String name;
	//商品封面图片
	private String cover;
	//商品价格
	private double price;
	//商品总需次数
	private int total_needs;
	//商品剩余次数
	private int rest_needs;
	//商品信息
	private String goods_info;
	//商品云购期数
	private int period;
	//开始云购时间(上架时间)
	private Timestamp sell_time;
	//下架时间
	private Timestamp off_time;
	//完成云购时间
	private Timestamp finish_time;
	//商品状态(0下架状态，1上架中，2已完成云购)
	private String goods_status;
	
	public Goods() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCover() {
		return cover;
	}

	public void setCover(String cover) {
		this.cover = cover;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getTotal_needs() {
		return total_needs;
	}

	public void setTotal_needs(int total_needs) {
		this.total_needs = total_needs;
	}

	public int getRest_needs() {
		return rest_needs;
	}

	public void setRest_needs(int rest_needs) {
		this.rest_needs = rest_needs;
	}

	public String getGoods_info() {
		return goods_info;
	}

	public void setGoods_info(String goods_info) {
		this.goods_info = goods_info;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public Timestamp getSell_time() {
		return sell_time;
	}

	public void setSell_time(Timestamp sell_time) {
		this.sell_time = sell_time;
	}

	public Timestamp getOff_time() {
		return off_time;
	}

	public void setOff_time(Timestamp off_time) {
		this.off_time = off_time;
	}

	public Timestamp getFinish_time() {
		return finish_time;
	}

	public void setFinish_time(Timestamp finish_time) {
		this.finish_time = finish_time;
	}

	public String getGoods_status() {
		return goods_status;
	}

	public void setGoods_status(String goods_status) {
		this.goods_status = goods_status;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", cover=" + cover + ", price=" + price + ", total_needs="
				+ total_needs + ", rest_needs=" + rest_needs + ", goods_info=" + goods_info + ", period=" + period
				+ ", sell_time=" + sell_time + ", off_time=" + off_time + ", finish_time=" + finish_time
				+ ", goods_status=" + goods_status + "]";
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
		Goods other = (Goods) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
