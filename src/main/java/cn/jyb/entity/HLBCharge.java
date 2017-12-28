package cn.jyb.entity;

import java.math.BigDecimal;

public class HLBCharge {
    private Integer id;

    private String car;//车辆名称

    private String carIcon;//车辆图标

    private String carType;//车辆类型

    private Double length;

    private Double width;

    private Double height;

    private Double volume;//体积

    private Double capacity;//载重

    private Integer within;//起步价里程

    private BigDecimal inPrice;//起步价

    private BigDecimal outPrice;//超里程之后每公里收费

    private String state;//状态（1--启用，0--禁用）

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getCarIcon() {
        return carIcon;
    }

    public void setCarIcon(String carIcon) {
        this.carIcon = carIcon;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public Double getWidth() {
        return width;
    }

    public void setWidth(Double width) {
        this.width = width;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }

    public Integer getWithin() {
        return within;
    }

    public void setWithin(Integer within) {
        this.within = within;
    }

    public BigDecimal getInPrice() {
        return inPrice;
    }

    public void setInPrice(BigDecimal inPrice) {
        this.inPrice = inPrice;
    }

    public BigDecimal getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(BigDecimal outPrice) {
        this.outPrice = outPrice;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

	@Override
	public String toString() {
		return "HLBCharge [id=" + id + ", car=" + car + ", carIcon=" + carIcon + ", carType=" + carType + ", length="
				+ length + ", width=" + width + ", height=" + height + ", volume=" + volume + ", capacity=" + capacity
				+ ", within=" + within + ", inPrice=" + inPrice + ", outPrice=" + outPrice + ", state=" + state + "]";
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
		HLBCharge other = (HLBCharge) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
}