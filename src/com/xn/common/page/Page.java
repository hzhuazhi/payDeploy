package com.xn.common.page;

/**
 * 分页返回参数的封装
 * @author yoko
 * @since 2016-05-27
 */
public class Page {

	private int pageNum = 1; // 当前页
	private int pageSize = 10; // 页大小
	private int rowCount = 0; // 总行数
	private int pageCount = 0; // 总页数
	private int startIndex = 0;// 当前页起始记录
	private int endIndex = 0;// 当前页到达的记录

	public Page() {

	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getRowCount() {
		return rowCount;
	}

	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
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

	public void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}

}