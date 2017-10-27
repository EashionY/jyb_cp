package cn.jyb.entity;

import java.util.Date;
/**
 * �ջ���ַ
 * @author Eashion
 *
 */
public class ShippingAddr {
	//�ջ���ַid
    private Integer addrId;
    //�û�id
    private Integer userId;
    //�ջ�������
    private String receiverName;
    //�ջ��˵绰
    private String receiverPhone;
    //��ַ����
    private String addrDetail;
    //Ĭ�ϵ�ַ(1--Ĭ�ϵ�ַ)
    private Integer asDefault;
    //����ʱ��
    private Date creatime;
    //����޸�ʱ��
    private Date updatime;

    public Integer getAddrId() {
        return addrId;
    }

    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getAddrDetail() {
        return addrDetail;
    }

    public void setAddrDetail(String addrDetail) {
        this.addrDetail = addrDetail;
    }

    public Integer getAsDefault() {
        return asDefault;
    }

    public void setAsDefault(Integer asDefault) {
        this.asDefault = asDefault;
    }

    public Date getCreatime() {
        return creatime;
    }

    public void setCreatime(Date creatime) {
        this.creatime = creatime;
    }

    public Date getUpdatime() {
        return updatime;
    }

    public void setUpdatime(Date updatime) {
        this.updatime = updatime;
    }

	@Override
	public String toString() {
		return "ShippingAddr [addrId=" + addrId + ", userId=" + userId + ", receiverName=" + receiverName
				+ ", receiverPhone=" + receiverPhone + ", addrDetail=" + addrDetail + ", asDefault=" + asDefault
				+ ", creatime=" + creatime + ", updatime=" + updatime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((addrId == null) ? 0 : addrId.hashCode());
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
		ShippingAddr other = (ShippingAddr) obj;
		if (addrId == null) {
			if (other.addrId != null)
				return false;
		} else if (!addrId.equals(other.addrId))
			return false;
		return true;
	}
    
}