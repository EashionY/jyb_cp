package cn.jyb.entity;

import java.util.Date;
/**
 * 收货地址
 * @author Eashion
 *
 */
public class ShippingAddr {
	//收货地址id
    private Integer addrId;
    //用户id
    private Integer userId;
    //收货人姓名
    private String receiverName;
    //收货人电话
    private String receiverPhone;
    //地址详情
    private String addrDetail;
    //默认地址(1--默认地址)
    private Integer asDefault;
    //创建时间
    private Date creatime;
    //最后修改时间
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