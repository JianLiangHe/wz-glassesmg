package com.wz.util;

import java.util.List;

public class PageQueryUtil implements java.io.Serializable, IPage {
	
	private int pageSize = 10; // 每页显示条数
	private int currentPage = 1;// 当前页
	private int totalPage;// 总共页数
	private int totalSize;// 总记录数
	private int showPageNum = 5;// 显示出来的页码数量
	private int start = 0;
	private int end = 1;
	private int page;
	private int rows;
	private Object result;
	
	public PageQueryUtil() {
		super();
	}

	public PageQueryUtil(int pageSize, int currentPage) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
	}

	public PageQueryUtil(int pageSize, int currentPage, int totalPage,
			int totalSize) {
		super();
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.totalSize = totalSize;
	}

	/**
	 * 每页第一条记录码
	 * 
	 * @return
	 */
	public int getFirstResult() {
		return (currentPage - 1) * pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		this.totalPage = this.totalSize % this.pageSize == 0 ? this.totalSize
				/ this.pageSize : (this.totalSize / this.pageSize + 1);
		if (this.totalPage == 0) {
			this.totalPage = 1;
		}
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public int getStart() {
		this.start=(getPage()-1)*getRows();
		return this.start;
	}

	public int getEnd() {
		this.end=getPage()*getRows();
		return this.end;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
		this.setPageSize(rows);
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		this.setCurrentPage(page);
	}

	@Override
	public String toString() {
		return "PageQueryUtil [pageSize=" + pageSize + ", currentPage=" + currentPage + ", totalPage=" + totalPage
				+ ", totalSize=" + totalSize + ", showPageNum=" + showPageNum + ", start=" + start + ", end=" + end
				+ ", page=" + page + ", rows=" + rows + "]";
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
