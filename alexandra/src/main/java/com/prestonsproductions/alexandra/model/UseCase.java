package com.prestonsproductions.alexandra.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Preston Frazier
 *
 */
@Entity
@Table(name="use_case")
@NamedQuery(name="UseCase.findAll", query="SELECT u FROM UseCase u")
public class UseCase implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id()
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name="uc_code")
	private String ucCode;
	@Column(name="uc_display")
	private String ucDisplay;
	@Column(name="active_flag")
	private boolean activeFlag;
	@Column(name="created_date")
	private Date createdDate;

	public UseCase() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUcCode() {
		return ucCode;
	}

	public void setUcCode(String ucCode) {
		this.ucCode = ucCode;
	}

	public String getUcDisplay() {
		return ucDisplay;
	}

	public void setUcDisplay(String ucDisplay) {
		this.ucDisplay = ucDisplay;
	}

	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



}