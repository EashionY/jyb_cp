package cn.jyb.entity;

import java.math.BigDecimal;
import java.util.Date;
/**
 * Ǯ����ϸ
 * @author Eashion
 *
 */
public class UserWalletDetail {
    private Integer id;//��ϸ��¼id

    private Integer userId;//�û�id

    private Integer type;//���ͣ�-1�����֣�-2�����������ѣ�1�����룬2��֧����

    private BigDecimal amount;//���

    private Date tradeTime;//���׷���ʱ��

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getTradeTime() {
        return tradeTime;
    }

    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

	@Override
	public String toString() {
		return "UserWalletDetail [id=" + id + ", userId=" + userId + ", type=" + type + ", amount=" + amount
				+ ", tradeTime=" + tradeTime + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		UserWalletDetail other = (UserWalletDetail) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}