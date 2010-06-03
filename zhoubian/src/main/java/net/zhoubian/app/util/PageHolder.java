package net.zhoubian.app.util;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PageHolder {
	// 记录日志
	private static final Log logger = LogFactory.getLog(PageHolder.class);
	// 检查分页模式是否为简单模式，简单模式下不计算总页数，总记录数，只显示当前页，上一页，下一页，跳到多少页，为０表示非简单模式，为１表示简单模式
	private int simpleMode = 0;

	private Integer priority;

	private Integer overDue;

	// 默认每页显示记录数
	private int pageSize = 12;

	// 总记录数
	private int resultSize = -1;

	// 记录索引，从0开始
	private int firstIndex = 0;

	// 页码
	private int currentPage = 1;

	// 总页数
	private int totalPage = 0;

	// 当前页记录，可以将任意的对象列表设置到该属性中
	private List<?> resultList;

	// 是否查询总记录数
	private boolean getCount = false;

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getOverDue() {
		return overDue;
	}

	public void setOverDue(Integer overDue) {
		this.overDue = overDue;
	}

	// 获取当前页
	public int getCurrentPage() {
		return currentPage;
	}

	// 设置当前页，当前页从第一页开始，设置页的同时设置记录索引
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		this.firstIndex = (currentPage - 1) * this.pageSize;
	}

	// 获取当前页记录
	public List<?> getResultList() {
		return resultList;
	}

	// 设置当前页记录
	public void setResultList(List<?> resultList) {
		this.resultList = resultList;
	}

	// 获取总记录数
	public int getResultSize() {
		return resultSize;
	}

	// 设置总记录数并计算总页数
	public void setResultSize(int resultSize) {
		this.resultSize = resultSize;
		int mod = resultSize % pageSize;
		if (mod == 0) {
			totalPage = resultSize / pageSize;
		} else {
			totalPage = resultSize / pageSize + 1;
		}
		logger.debug("TotalPage :" + totalPage);
	}

	// 获取每页显示记录数
	public int getPageSize() {
		return pageSize;
	}

	// 设置每页显示记录数
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	// 设置总页数
	public int getTotalPage() {
		return totalPage;
	}

	// 获取总页数
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	// 获取当前页纪录开始索引
	public int getFirstIndex() {
		return firstIndex;
	}

	// 设置当前页记录索引
	public void setFirstIndex(int firstIndex) {
		this.firstIndex = firstIndex;
	}

	// 是否已经查询过总记录数，如果没有查询过则返回true，否则返回false
	public boolean isGetCount() {
		if (resultSize == -1) {
			logger.debug("Need To Get Total Result Amount");
			getCount = true;
		}
		if (simpleMode == 1) {
			return false;
		} else {
			return getCount;
		}
	}

	// 设置是否已经获取过总记录数，通常在setResultSize后将getCount设置为true
	public void setGetCount(boolean getCount) {
		this.getCount = getCount;
	}

	// 获取生成页面选择下拉框的html字符串
	public String getPageOption() {
		StringBuffer optionBuffer = new StringBuffer();

		for (int i = 1; i <= totalPage; i++) {
			optionBuffer.append("<OPTION VALUE=\"");
			optionBuffer.append(i);
			optionBuffer.append("\" ");
			if (i == currentPage) {
				optionBuffer.append("selected=\"selected\"");
			}
			optionBuffer.append(">");
			optionBuffer.append(i);
			optionBuffer.append("</option>");
		}
		return optionBuffer.toString();

	}

	public String getPageInfo() {
		StringBuffer sb = new StringBuffer();
		sb.append("<tr>");
		sb.append("<input type=\"hidden\" name=\"currPage\" value=\""
				+ currentPage + "\"/>");

		sb.append("<input type='hidden' name='pageSize' value='" + pageSize
				+ "'/>");
		sb.append("<input type='hidden' name='jumpPage' value=''/>");
		sb.append("<input type='hidden' name='totalPage' value=" + totalPage
				+ "/>");
		sb
				.append("<td height='40' colspan='6'><div id='page'>[&nbsp;&nbsp;<a href='#' onClick='toPage(1)'>首页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='#' onClick='toPage("
						+ ((currentPage - 1) < 1 ? 1 : (currentPage - 1))
						+ ")>上一页</a>&nbsp;&nbsp;|&nbsp;&nbsp;");
		for (int i = 1; i <= totalPage; i++) {
			if (i == currentPage) {
				sb.append("<strong>" + i + "</strong>");
			} else {
				sb.append("<a href='#' onClick='toPage(" + i + ")'>" + i
						+ "</a> ");

			}
		}
		sb
				.append("&nbsp;&nbsp;|&nbsp;&nbsp;<a href='#' onClick='toPage("
						+ ((currentPage + 1) > totalPage ? totalPage
								: (currentPage + 1))
						+ ")+'>下一页</a>&nbsp;&nbsp;|&nbsp;&nbsp;<a href='#' onClick='toPage("
						+ totalPage + ")'>末页</a> &nbsp;&nbsp;]</div> </td>");
		sb.append("</tr> ");

		return sb.toString();
	}

	// 获取分页模式
	public int getSimpleMode() {
		return simpleMode;
	}

	// 设置分页模式
	public void setSimpleMode(int simpleMode) {
		this.simpleMode = simpleMode;
	}

	// 将当前页初始化为第1页的构造函数,初始化firstIndex
	public PageHolder() {
		initPageParameter(1);
	}

	// 指定当前页的构造函数,初始化firstIndex
	public PageHolder(int currentPage) {
		initPageParameter(currentPage);
	}

	// 指定总记录数和当前页的构造函数,初始化firstIndex
	public PageHolder(int currentPage, int resultSize) {

		initPageParameter(currentPage, resultSize);
	}

	// 根据request对象来初始化总记录数,当前页和firstIndex
	public PageHolder(HttpServletRequest request) {

		initPageParameter(request);
	}

	// 初始化当前页和记录索引
	private void initPageParameter(int currentPage) {
		this.currentPage = currentPage;
		logger.debug("Current Page :" + currentPage);
		firstIndex = (currentPage - 1) * pageSize;
		logger.debug("FirstIndex :" + firstIndex);

	}

	// 初始化当前页，记录索引和总记录数
	private void initPageParameter(int currentPage, int resultSize) {
		this.currentPage = currentPage;
		logger.debug("Current Page :" + currentPage);
		firstIndex = (currentPage - 1) * pageSize;
		logger.debug("FirstIndex :" + firstIndex);
		if (simpleMode == 0)
			setResultSize(resultSize);

	}

	// 根据request请求来初始化
	private void initPageParameter(HttpServletRequest request) {
		String simpleModeStr = request.getParameter("simpleMode");
		if (!StringUtils.isBlank(simpleModeStr)) {
			this.simpleMode = Integer.parseInt(simpleModeStr);
		}
		String currentPage = request.getParameter("currPage");
		if (!StringUtils.isBlank(currentPage)) {
			this.currentPage = Integer.parseInt(currentPage);
		}
		String resultSize = request.getParameter("resSize");
		if (!StringUtils.isBlank(resultSize)) {
			this.resultSize = Integer.parseInt(resultSize);
		}
		String pageSize = request.getParameter("pageSize");
		if (!StringUtils.isBlank(pageSize)) {
			this.pageSize = Integer.parseInt(pageSize);
		}

		String _priority = request.getParameter("priority");
		if (!StringUtils.isBlank(_priority)) {
			this.priority = Integer.parseInt(_priority);
			request.setAttribute("priority", priority);
		}
		String dueDate = request.getParameter("overDue");
		if (!StringUtils.isBlank(dueDate)) {
			overDue = Integer.parseInt(dueDate);
			request.setAttribute("overDue", overDue);
		}

		// 非简单模式
		if (simpleMode == 0) {
			int mod = this.resultSize % this.pageSize;
			if (mod == 0) {
				totalPage = this.resultSize / this.pageSize;
			} else {
				totalPage = this.resultSize / this.pageSize + 1;
			}
		}
		String lastPage = request.getParameter("lastPage");
		String nextPage = request.getParameter("nextPage");
		String jumpPage = request.getParameter("jumpPage");
		String total = request.getParameter("totalPage");
		if (!StringUtils.isBlank(total)) {
			totalPage = Integer.parseInt(total);
		}
		if (!StringUtils.isBlank(lastPage) && lastPage.equals("true")) {
			if (this.currentPage <= 1) {
				this.currentPage = 1;
			} else {
				this.currentPage = this.currentPage - 1;
			}
		} else if (!StringUtils.isBlank(nextPage) && nextPage.equals("true")) {
			if (simpleMode == 0) {
				if (this.currentPage + 1 > totalPage) {
					this.currentPage = totalPage;
				} else {
					this.currentPage = this.currentPage + 1;
				}
			} else {
				this.currentPage = this.currentPage + 1;
			}
		} else if (!StringUtils.isBlank(jumpPage)) {
			int i = Integer.parseInt(jumpPage);
			if (i <= 1) {
				this.currentPage = 1;
			} else if ((i > totalPage) && (simpleMode == 0)) {
				this.currentPage = totalPage;
			} else {
				this.currentPage = i;
			}
		}
		initPageParameter(this.currentPage, this.resultSize);

	}
}