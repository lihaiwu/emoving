package net.zhoubian.app.model;

import java.util.Date;

/**
 * GroupBuy generated by MyEclipse Persistence Tools
 */

public class GroupBuy implements java.io.Serializable {

	// Fields

	private Integer id;

	private String title;

	private String content;

	private Long uid;

	private Date signStart;

	private Date signEnd;

	private Date purchaseStart;

	private Date purchaseEnd;

	private String status;

	private Date createTime;

	private String url;

	// Constructors

	/** default constructor */
	public GroupBuy() {
	}

	/** full constructor */
	public GroupBuy(String title, String content, Long uid, Date signStart,
			Date signEnd, Date purchaseStart, Date purchaseEnd, String status,
			Date createTime, String url) {
		this.title = title;
		this.content = content;
		this.uid = uid;
		this.signStart = signStart;
		this.signEnd = signEnd;
		this.purchaseStart = purchaseStart;
		this.purchaseEnd = purchaseEnd;
		this.status = status;
		this.createTime = createTime;
		this.url = url;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUid() {
		return this.uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
	}

	public Date getSignStart() {
		return this.signStart;
	}

	public void setSignStart(Date signStart) {
		this.signStart = signStart;
	}

	public Date getSignEnd() {
		return this.signEnd;
	}

	public void setSignEnd(Date signEnd) {
		this.signEnd = signEnd;
	}

	public Date getPurchaseStart() {
		return this.purchaseStart;
	}

	public void setPurchaseStart(Date purchaseStart) {
		this.purchaseStart = purchaseStart;
	}

	public Date getPurchaseEnd() {
		return this.purchaseEnd;
	}

	public void setPurchaseEnd(Date purchaseEnd) {
		this.purchaseEnd = purchaseEnd;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}