package com.vincent.demo.util;

import java.util.ArrayList;
import java.util.List;

public class PaginationModel<T> {
	
	private long recordTotal;
	
	private long pageIndex;
	
	private long pageSize;
	
	private long pageTotal = 0;
	
	private List<T> data;
	
	private String html;
	
	public long getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(long pageIndex) {
		this.pageIndex = pageIndex;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getRecordTotal() {
		return recordTotal;
	}

	public void setRecordTotal(long recordTotal) {
		this.recordTotal = recordTotal;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		if(data == null){
			data = new ArrayList<T>();
		}
		this.data = data;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public long getPageTotal() {
		if(data != null && pageSize>0){
			long total = (recordTotal + pageSize - 1) / pageSize;
			if(total == 0){
				total = 1;
			}
			return total;
		}
		return pageTotal;
	}

	public void setPageTotal(long pageTotal) {
		this.pageTotal = pageTotal;
	}
	
}