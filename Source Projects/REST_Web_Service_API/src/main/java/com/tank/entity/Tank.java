package com.tank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@Entity
@Table(name = "TANK")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Tank")
public class Tank extends BaseEntity {

	@Transient
	private static final long serialVersionUID = -1232395859408322328L;

	@Column(nullable = false, length = 20, unique = true)
	private String tankId;

	private String tankName;

	private long waterLevel;

	private long pumpStatus;
	
	@Column(nullable = true)
	private int maxDepth;
	
	@Column(nullable = true)
	private int minDepth;
	
	@Column(nullable = true)
	private int triggerDepth;
	
	@Column(nullable = true)
	private int autoOnPump;
	
	private String clientId;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getTankId() {
		return tankId;
	}

	public void setTankId(String tankId) {
		this.tankId = tankId;
	}

	public String getTankName() {
		return tankName;
	}

	public void setTankName(String tankName) {
		this.tankName = tankName;
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

	public int getMaxDepth() {
		return maxDepth;
	}

	public void setMaxDepth(int maxDepth) {
		this.maxDepth = maxDepth;
	}

	public int getMinDepth() {
		return minDepth;
	}

	public void setMinDepth(int minDepth) {
		this.minDepth = minDepth;
	}

	public int getTriggerDepth() {
		return triggerDepth;
	}

	public void setTriggerDepth(int triggerDepth) {
		this.triggerDepth = triggerDepth;
	}

	public int getAutoOnPump() {
		return autoOnPump;
	}

	public void setAutoOnPump(int autoOnPump) {
		this.autoOnPump = autoOnPump;
	}
	


}
