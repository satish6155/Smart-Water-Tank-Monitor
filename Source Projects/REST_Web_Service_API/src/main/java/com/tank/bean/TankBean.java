package com.tank.bean;

import java.io.Serializable;

import javax.persistence.Transient;

import com.tank.entity.Tank;

public class TankBean implements Serializable {
	
	@Transient
	private static final long serialVersionUID = -1232395859408322328L;
	private String tankId;
	private long waterLevel;
	private long pumpStatus;
	
	public TankBean(){
		super();
	}

	public TankBean(Tank tank) {
		super();
		this.tankId = tank.getTankId();
		this.waterLevel = tank.getWaterLevel();
		this.pumpStatus = tank.getPumpStatus();
	}
	public String getTankId() {
		return tankId;
	}
	public void setTankId(String tankId) {
		this.tankId = tankId;
	}
	public long getWaterLevel() {
		return waterLevel;
	}
	public void setWaterLevel(long waterLevel) {
		this.waterLevel = waterLevel;
	}
	public long getPumpStatus() {
		return pumpStatus;
	}
	public void setPumpStatus(long pumpStatus) {
		this.pumpStatus = pumpStatus;
	}
	@Override
	public String toString() {
		return "TankBean [tankId=" + tankId + ", waterLevel=" + waterLevel
				+ ", pumpStatus=" + pumpStatus + "]";
	}
	
	

}
