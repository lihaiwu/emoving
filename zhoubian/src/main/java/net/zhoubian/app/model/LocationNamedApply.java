package net.zhoubian.app.model;

import java.util.Date;

/**
 * LocationNamedApply generated by MyEclipse Persistence Tools
 */

public class LocationNamedApply implements java.io.Serializable {

	// Fields

	private Long id;

	private String locationName;

	private Integer locationType;

	private String provinceId;

	private String cityId;

	private String countyId;

	private String streetId;

	private Float longitude;

	private Float latitude;

	private Long uid;

	private Date createTime;

	private String approveResult;

	private Long approverId;

	private Date approveTime;

	// Constructors

	/** default constructor */
	public LocationNamedApply() {
	}

	/** minimal constructor */
	public LocationNamedApply(String locationName, Integer locationType) {
		this.locationName = locationName;
		this.locationType = locationType;
	}

	/** full constructor */
	public LocationNamedApply(String locationName, Integer locationType,
			String provinceId, String cityId, String countyId, String streetId,
			Float longitude, Float latitude, Long uid, Date createTime,
			String approveResult, Long approverId, Date approveTime) {
		this.locationName = locationName;
		this.locationType = locationType;
		this.provinceId = provinceId;
		this.cityId = cityId;
		this.countyId = countyId;
		this.streetId = streetId;
		this.longitude = longitude;
		this.latitude = latitude;
		this.uid = uid;
		this.createTime = createTime;
		this.approveResult = approveResult;
		this.approverId = approverId;
		this.approveTime = approveTime;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLocationName() {
		return this.locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public Integer getLocationType() {
		return this.locationType;
	}

	public void setLocationType(Integer locationType) {
		this.locationType = locationType;
	}

	public String getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return this.cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCountyId() {
		return this.countyId;
	}

	public void setCountyId(String countyId) {
		this.countyId = countyId;
	}

	public String getStreetId() {
		return this.streetId;
	}

	public void setStreetId(String streetId) {
		this.streetId = streetId;
	}

	public Float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getApproveResult() {
		return this.approveResult;
	}

	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}

	public Long getApproverId() {
		return this.approverId;
	}

	public void setApproverId(Long approverId) {
		this.approverId = approverId;
	}

	public Date getApproveTime() {
		return this.approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

}