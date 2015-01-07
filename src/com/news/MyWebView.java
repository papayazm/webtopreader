package com.news;

import android.content.Context;
import android.webkit.WebView;

public class MyWebView extends WebView {
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}

	String uuid;
	int total;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	public MyWebView(Context context,String uuid,int total) {
		super(context);
		this.uuid=uuid;
		// TODO Auto-generated constructor stub
	}

}
