package cn.jyb.entity;
/**
 * 货拉宝订单接单邀请
 * @author Eashion
 */
public class HLBInvite {
    private Integer id;

    private String hlbOrderNo;//订单号

    private Integer invited;//被邀请接单人用户id

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHlbOrderNo() {
        return hlbOrderNo;
    }

    public void setHlbOrderNo(String hlbOrderNo) {
        this.hlbOrderNo = hlbOrderNo;
    }

    public Integer getInvited() {
        return invited;
    }

    public void setInvited(Integer invited) {
        this.invited = invited;
    }

	@Override
	public String toString() {
		return "HLBInvite [id=" + id + ", hlbOrderNo=" + hlbOrderNo + ", invited=" + invited + "]";
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
		HLBInvite other = (HLBInvite) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}