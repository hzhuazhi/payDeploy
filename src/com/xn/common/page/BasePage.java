package com.xn.common.page;

/**
 * 
 * ClassName: BasePage2
 * @Description: TODO
 * @author yoko
 * @date 2016-7-27
 */
public class BasePage {

	private int start = 0;
	private int length = 10;
	private Integer pageNum = 1;
	private int pageSize = 10; // 页大小
	private int rowCount = 0; // 总行数
	private int pageCount = 0; // 总页数
	private int startIndex = 0;// 当前页起始记录
	private int endIndex = 0;// 当前页到达的记录

	private Page page = new Page();

	//分页算法
	protected void doPage() {
		//一般js框架可以根据分页参数自动计算总页数
		//计算分页参数，用于分页查询
		this.pageCount = this.rowCount / this.pageSize;
		if ((this.rowCount % this.pageSize != 0))
			this.pageCount++;

		this.startIndex = (this.pageNum - 1) * this.pageSize;
		this.endIndex = this.startIndex + this.pageSize;
		if ((this.startIndex + this.pageSize) > this.rowCount)
			this.endIndex = this.rowCount;

		//封装要输出的分页参数
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		page.setRowCount(rowCount);
		page.setPageCount(pageCount);
		page.setStartIndex(startIndex);
		page.setEndIndex(endIndex);
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
		this.pageSize = this.length;
		if (this.start == 0) {
			this.pageNum = 1;
		} else {
			this.pageNum = this.start / this.length + 1;
		}
		this.doPage();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public int getEndIndex() {
		return endIndex;
	}

	public void SetEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getStart() {
		return start;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getLength() {
		return length;
	}

}
