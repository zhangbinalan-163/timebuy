package com.alan.app.timebuy.entity;// default package

import java.util.Date;

public class News{

	private Integer newsId;//主键
	private String news;//消息内容
	private Date starttime;//开始时间
	private Date finishtime;//结束时间
	private String phone;//手机号
	private Float money;//金额
	private String coordname;//坐标名称
	private Double coordx;//坐标x
	private Double coordy;//坐标y
	private String pic;//图片名称
	private int userid;//用户主键
	private int acceptUserid;//接受消息用户主键
	private int label;//标签种类 1跑腿 2陪我 3学霸 4公益
	private int praise;//点赞数
	private int share;//分享次数
	private int tag;//等待中0；进行中1；已完成2；申诉中3；已取消4；延时中5
	private Date accepttime;//接受任务时间

	public Date getAccepttime() {
		return accepttime;
	}

	public void setAccepttime(Date accepttime) {
		this.accepttime = accepttime;
	}

	public int getShare() {
		return share;
	}

	public void setShare(int share) {
		this.share = share;
	}

	public int getTag() {
		return tag;
	}

	public void setTag(int tag) {
		this.tag = tag;
	}

	public int getPraise() {
		return praise;
	}

	public void setPraise(int praise) {
		this.praise = praise;
	}

	public int getAcceptUserid() {
		return acceptUserid;
	}

	public void setAcceptUserid(int acceptUserid) {
		this.acceptUserid = acceptUserid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public Integer getNewsId() {
		return this.newsId;
	}

	public void setNewsId(Integer newsId) {
		this.newsId = newsId;
	}

	public String getNews() {
		return this.news;
	}

	public void setNews(String news) {
		this.news = news;
	}

	public Date getStarttime() {
		return this.starttime;
	}

	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}

	public Date getFinishtime() {
		return this.finishtime;
	}

	public void setFinishtime(Date finishtime) {
		this.finishtime = finishtime;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getLabel() {
		return label;
	}

	public void setLabel(int label) {
		this.label = label;
	}

	public Float getMoney() {
		return this.money;
	}

	public void setMoney(Float money) {
		this.money = money;
	}

	public String getCoordname() {
		return this.coordname;
	}

	public void setCoordname(String coordname) {
		this.coordname = coordname;
	}

	public Double getCoordx() {
		return coordx;
	}

	public void setCoordx(Double coordx) {
		this.coordx = coordx;
	}

	public Double getCoordy() {
		return coordy;
	}

	public void setCoordy(Double coordy) {
		this.coordy = coordy;
	}
}