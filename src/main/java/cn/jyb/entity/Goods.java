package cn.jyb.entity;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * �ƹ�ƽ̨��Ʒ
 * @author Eashion
 *
 */
public class Goods implements Serializable {

	private static final long serialVersionUID = 2411294293126520840L;
	
	private int id;
	//��Ʒ��
	private String name;
	//��Ʒ����ͼƬ
	private String cover;
	//��Ʒ�۸�
	private double price;
	//��Ʒ�������
	private int total_needs;
	//��Ʒʣ�����
	private int rest_needs;
	//��Ʒ��Ϣ
	private String goods_info;
	//��Ʒ�ƹ�����
	private int period;
	//��ʼ�ƹ�ʱ��(�ϼ�ʱ��)
	private Timestamp sell_time;
	//�¼�ʱ��
	private Timestamp off_time;
	//����ƹ�ʱ��
	private Timestamp finish_time;
	//��Ʒ״̬(0�¼�״̬��1�ϼ��У�2������ƹ�)
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
