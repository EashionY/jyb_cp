package cn.jyb.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * ƴ������
 * @author Eashion
 *
 */
public class DriverOrder {
	//ƴ������id
    private Integer driverOrderId;
    //����ʱ��
    private String departTime;
    //������
    private String departure;
    //Ŀ�ĵ�
    private String destination;
    //����
    private BigDecimal fare;
    //��λ/����
    private Integer seat;
    //����
    private String carType;
    //��ϵ��
    private String contact;
    //��ϵ�˵绰
    private String contactPhone;
    //��ע
    private String remark;
    //�����������û�id
    private Integer publishId;
    //�ӵ����û�id
    private Integer receiptId;
    //�������ͣ�1Ϊ����������2Ϊ�˿Ͷ�����
    private Integer orderType;
    //����״̬����
    private Integer orderStatus;
    //��������ʱ��
    private Date publishTime;
    //�����ӵ�ʱ��
    private Date receiptTime;

    public Integer getDriverOrderId() {
        return driverOrderId;
    }

    public void setDriverOrderId(Integer driverOrderId) {
        this.driverOrderId = driverOrderId;
    }

    public String getDepartTime() {
        return departTime;
    }

    public void setDepartTime(String departTime) {
        this.departTime = departTime;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getFare() {
        return fare;
    }

    public void setFare(BigDecimal fare) {
        this.fare = fare;
    }

    public Integer getSeat() {
        return seat;
    }

    public void setSeat(Integer seat) {
        this.seat = seat;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getPublishId() {
        return publishId;
    }

    public void setPublishId(Integer publishId) {
        this.publishId = publishId;
    }

    public Integer getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(Integer receiptId) {
        this.receiptId = receiptId;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getReceiptTime() {
        return receiptTime;
    }

    public void setReceiptTime(Date receiptTime) {
        this.receiptTime = receiptTime;
    }

	@Override
	public String toString() {
		return "DriverOrder [driverOrderId=" + driverOrderId + ", departTime=" + departTime + ", departure=" + departure
				+ ", destination=" + destination + ", fare=" + fare + ", seat=" + seat + ", carType=" + carType
				+ ", contact=" + contact + ", contactPhone=" + contactPhone + ", remark=" + remark + ", publishId="
				+ publishId + ", receiptId=" + receiptId + ", orderType=" + orderType + ", orderStatus=" + orderStatus
				+ ", publishTime=" + publishTime + ", receiptTime=" + receiptTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((driverOrderId == null) ? 0 : driverOrderId.hashCode());
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
		DriverOrder other = (DriverOrder) obj;
		if (driverOrderId == null) {
			if (other.driverOrderId != null)
				return false;
		} else if (!driverOrderId.equals(other.driverOrderId))
			return false;
		return true;
	}
    
}