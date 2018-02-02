package cn.jyb.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * 用户钱包提现申请记录
 * @author Eashion
 *
 */
public class UserWalletDraw {
    private Integer drawId;

    private Integer userId;

    private BigDecimal drawAmount;//提现金额

    private BigDecimal serviceCharge;//手续费

    private Date applyTime;//申请时间

    private Date passTime;//通过时间

    private Integer status;//状态（-1：未通过，1：申请中，2：已通过，3：已完成）

    public Integer getDrawId() {
        return drawId;
    }

    public void setDrawId(Integer drawId) {
        this.drawId = drawId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getDrawAmount() {
        return drawAmount;
    }

    public void setDrawAmount(BigDecimal drawAmount) {
        this.drawAmount = drawAmount;
    }

    public BigDecimal getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(BigDecimal serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getPassTime() {
        return passTime;
    }

    public void setPassTime(Date passTime) {
        this.passTime = passTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	@Override
	public String toString() {
		return "UserWalletDraw [drawId=" + drawId + ", userId=" + userId + ", drawAmount=" + drawAmount
				+ ", serviceCharge=" + serviceCharge + ", applyTime=" + applyTime + ", passTime=" + passTime
				+ ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((drawId == null) ? 0 : drawId.hashCode());
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
		UserWalletDraw other = (UserWalletDraw) obj;
		if (drawId == null) {
			if (other.drawId != null)
				return false;
		} else if (!drawId.equals(other.drawId))
			return false;
		return true;
	}
    
}