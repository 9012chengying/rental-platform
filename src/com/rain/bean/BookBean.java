package com.rain.bean;

public class BookBean {
	/**
	 * 楼盘的数据表的bean
	 */
	private int bid;//id
	private String card;//信息来源
	private String name;//房源名称
	private String autho;//连接
	private int num;//房价
	private String type;//面积
	private int status;//表示状态，1为正在收藏，2是取消收藏
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCard() {
		return card;
	}
	public void setCard(String card) {
		this.card = card;
	}
	public String getAutho() {
		return autho;
	}
	public void setAutho(String autho) {
		this.autho = autho;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}
