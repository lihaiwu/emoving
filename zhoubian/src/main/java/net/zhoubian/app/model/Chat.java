package net.zhoubian.app.model;

import java.io.Serializable;
import java.util.Date;

import com.googlecode.jsonplugin.annotations.JSON;

/**
 * Create on 2010-5-31
 * 
 * 信息类
 * 
 * @author upon
 * @version
 */
@SuppressWarnings("serial")
public class Chat implements Serializable {
	private Integer mid;

	private Long id = System.currentTimeMillis();

	private Date date;

	private String sender;

	private String text;

	public String getText() {
		return this.text;
	}

	// @JSON(serialize=false)
	public Date getDate() {
		return date;
	}

	public String getSender() {
		return sender;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public void setText(String text) {
		this.text = text;
	}

	@JSON(serialize = false)
	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	@JSON(serialize = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
