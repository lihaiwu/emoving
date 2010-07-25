package net.zhoubian.app.util;

import java.util.List;

import org.apache.commons.lang.SerializationUtils;
import org.hibernate.criterion.DetachedCriteria;

public class Page {
	/** 总记录数 */
	private int totalProperty;

	/** 分页结果 */
	private List root;

	/** 开始页码 */
	private int start;

	/** 每页多少 */
	private int limit;

	/** 成功与否 */
	private boolean success;

	/** 查询条件 */
	private List conditions;

	private DetachedCriteria result;

	private DetachedCriteria count;

	private String strCondition;

	private String strCondition2;

	public String getStrCondition() {
		return strCondition;
	}

	public void setStrCondition(String strCondition) {
		this.strCondition = strCondition;
	}

	public String getStrCondition2() {
		return strCondition2;
	}

	public void setStrCondition2(String strCondition2) {
		this.strCondition2 = strCondition2;
	}


	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit == 0 ? 20 : limit;
	}

	public List getRoot() {
		return root;
	}

	public void setRoot(List root) {
		this.root = root;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start + 1;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public int getTotalProperty() {
		return totalProperty;
	}

	public void setTotalProperty(int totalProperty) {
		this.totalProperty = totalProperty;
	}

	public List getConditions() {
		return conditions;
	}

	public void setConditions(List conditions) {
		this.conditions = conditions;
	}

	public DetachedCriteria getCount() {
		return count;
	}

	public void setCount(DetachedCriteria count) {
		this.count = count;
	}

	public DetachedCriteria getResult() {
		return result;
	}

	public void setResult(DetachedCriteria result) {
		this.count = (DetachedCriteria) SerializationUtils.clone(result);
		this.result = result;
	}

}
