package net.zhoubian.app.model;

/**
 * Category generated by MyEclipse Persistence Tools
 */

public class Category implements java.io.Serializable {

	// Fields

	private String categoryId;

	private String categoryName;

	private Integer sort;

	// Constructors

	/** default constructor */
	public Category() {
	}

	/** full constructor */
	public Category(String categoryName, Integer sort) {
		this.categoryName = categoryName;
		this.sort = sort;
	}

	// Property accessors

	public String getCategoryId() {
		return this.categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return this.categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Integer getSort() {
		return this.sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

}