package com.yx.etoc.datagift.app.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.yx.baseframe.util.DateTools;
import com.yx.etoc.datagift.cd.entity.DgCdInfoH;
import com.yx.etoc.datagift.ct.entity.DgCtUser;

/**
 * The persistent class for the dg_app_info database table.
 * 
 */
@Entity
@Table(name = "dg_app_info")
public class DgAppInfo implements Serializable, Comparable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "APP_ID")
	private String appId;

	@Column(name = "APP_FILE")
	private String appFile;

	@Column(name = "APP_ICON")
	private String appIcon;

	@Column(name = "APP_NAME")
	private String appName;

	@Column(name = "APP_REMARK")
	private String appRemark;

	@Column(name = "APP_SIZE")
	private String appSize;

	@OneToOne(cascade = { CascadeType.REFRESH }, optional = false, fetch = FetchType.LAZY, targetEntity = DgAppCategory.class)
	@JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false)
	private DgAppCategory category;

	@Column(name = "CREDIT_COUNT")
	private Integer creditCount;

	@Column(name = "EXPE_COUNT")
	private Integer expeCount;

	@Column(name = "FIRE_RULE")
	private String fireRule;

	@Column(name = "FLUX_COUNT")
	private Integer fluxCount;

	@Column(columnDefinition="TIMESTAMP", insertable = false, updatable = false)
	@OrderBy("lastUpdateTime DESC")
	private Timestamp lastUpdateTime;

	@Column(name = "LAST_UPDATE_USER")
	private String lastUpdateUser;

	@Column(name = "PACKAGE_NAME")
	private String packageName;

	private String step;
	
	@Column(name = "ACT_TYPE")
	private String actType;

	private BigDecimal weight;
	
	@OneToMany(mappedBy="appinfo",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private List<DgAppImg> imgs = Lists.newArrayList();
	
	@ManyToMany(mappedBy="apps",fetch = FetchType.LAZY)
	private Set<DgCtUser> users = Sets.newTreeSet();
	
	@OneToMany(mappedBy="appinfo",cascade=CascadeType.REFRESH,fetch=FetchType.LAZY)
	@OrderBy("updateTime DESC")
	private List<DgCdInfoH> cdDetails = Lists.newArrayList();
	
	public DgAppInfo() {
	}

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppFile() {
		return this.appFile;
	}

	public void setAppFile(String appFile) {
		this.appFile = appFile;
	}

	public String getAppIcon() {
		return this.appIcon;
	}

	public void setAppIcon(String appIcon) {
		this.appIcon = appIcon;
	}

	public String getAppName() {
		return this.appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppRemark() {
		return this.appRemark;
	}

	public void setAppRemark(String appRemark) {
		this.appRemark = appRemark;
	}

	public String getAppSize() {
		return this.appSize;
	}

	public void setAppSize(String appSize) {
		this.appSize = appSize;
	}

	public Integer getCreditCount() {
		return this.creditCount;
	}

	public void setCreditCount(Integer creditCount) {
		this.creditCount = creditCount;
	}

	public Integer getExpeCount() {
		return this.expeCount;
	}

	public void setExpeCount(Integer expeCount) {
		this.expeCount = expeCount;
	}

	
	public String getFireRule() {
		return this.fireRule;
	}

	public void setFireRule(String fireRule) {
		this.fireRule = fireRule;
	}

	public Integer getFluxCount() {
		return this.fluxCount;
	}

	public void setFluxCount(Integer fluxCount) {
		this.fluxCount = fluxCount;
	}

	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getPackageName() {
		return this.packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getStep() {
		return this.step;
	}

	public void setStep(String step) {
		this.step = step;
	}

	public BigDecimal getWeight() {
		return this.weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	
	@JsonIgnore
	public DgAppCategory getCategory() {
		return category;
	}

	public void setCategory(DgAppCategory category) {
		this.category = category;
	}

	@JsonIgnore
	public List<DgAppImg> getImgs() {
		return imgs;
	}

	public void setImgs(List<DgAppImg> imgs) {
		this.imgs = imgs;
	}

	public String getActType() {
		return actType;
	}

	public void setActType(String actType) {
		this.actType = actType;
	}

	public Set<DgCtUser> getUsers() {
		return users;
	}

	public void setUsers(Set<DgCtUser> users) {
		this.users = users;
	}

	public List<DgCdInfoH> getCdDetails() {
		return cdDetails;
	}

	public void setCdDetails(List<DgCdInfoH> cdDetails) {
		this.cdDetails = cdDetails;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((appId == null) ? 0 : appId.hashCode());
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
		DgAppInfo other = (DgAppInfo) obj;
		if (appId == null) {
			if (other.appId != null)
				return false;
		} else if (!appId.equals(other.appId))
			return false;
		return true;
	}
	public int compareTo(Object o) {
		DgAppInfo obj = (DgAppInfo)o;
		return this.getLastUpdateTime().compareTo(obj.getLastUpdateTime());
	}
}