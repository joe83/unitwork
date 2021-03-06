package com.yx.etoc.datagift.ct.entity;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the dg_ct_user_change_log database table.
 * 
 */
@Embeddable
public class DgCtUserChangeLogPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="UPDATE_TIME")
	private String updateTime;

	@Column(name="USER_ID")
	private String userId;

    public DgCtUserChangeLogPK() {
    }
	public String getUpdateTime() {
		return this.updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof DgCtUserChangeLogPK)) {
			return false;
		}
		DgCtUserChangeLogPK castOther = (DgCtUserChangeLogPK)other;
		return 
			this.updateTime.equals(castOther.updateTime)
			&& this.userId.equals(castOther.userId);

    }
    
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.updateTime.hashCode();
		hash = hash * prime + this.userId.hashCode();
		
		return hash;
    }
}