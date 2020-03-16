package com.tank.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = -1232395859408322328L;

	@Id
	@GenericGenerator(name = "sequencePerEntityGenerator", strategy = "com.tank.util.CustomSequenceGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "prefer_sequence_per_entity", value = "true"),
			@org.hibernate.annotations.Parameter(name = "sequence_per_entity_suffix", value = "_seq"),
			@org.hibernate.annotations.Parameter(name = "initial_value", value = "50000") })
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "sequencePerEntityGenerator")
	private Long id;

	@Column(columnDefinition = "NUMBER(1)")
	private Integer status;

	private Timestamp creationTimeStamp;

	private Timestamp lastUpdatedTimeStamp;

	private Long createdBy;

	private Long lastUpdatedBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		if (status == null) {
			status = 1;
		}
		this.status = status;
	}

	public Timestamp getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(Timestamp creationTimeStamp) {
		if (creationTimeStamp == null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			creationTimeStamp = timestamp;
		}
		this.creationTimeStamp = creationTimeStamp;
	}

	public Timestamp getLastUpdatedTimeStamp() {
		return lastUpdatedTimeStamp;
	}

	public void setLastUpdatedTimeStamp(Timestamp lastUpdatedTimeStamp) {

		if (lastUpdatedTimeStamp == null) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			lastUpdatedTimeStamp = timestamp;
		}
		this.lastUpdatedTimeStamp = lastUpdatedTimeStamp;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", status=" + status
				+ ", creationTimeStamp=" + creationTimeStamp
				+ ", lastUpdatedTimeStamp=" + lastUpdatedTimeStamp
				+ ", createdBy=" + createdBy + ", lastUpdatedBy="
				+ lastUpdatedBy + "]";
	}

}
