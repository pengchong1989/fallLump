package com.teach.server;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;


public class NewsQuery implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
    public String id;
	@Field
    public String news_type;
	@Field
    public String title;
	@Field
    public String cat_id;
	@Field
    public String cat_name;
	@Field
    public String thumb;
	@Field
    public String update_date;
	@Field
    public String time_stamp;
	@Field
    public String description;
	@Field
    public String del_flag;
	@Field
    public String status;
	@Field
    public String audit_state;
	@Field
    public String content;
	@Field
    public String ct_type;
	@Field
    public String memberName;
	@Field
    public String headimg;
	
	
	
	
	
	public String getHeadimg() {
		return headimg;
	}
	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getNews_type() {
		return news_type;
	}
	public void setNews_type(String news_type) {
		this.news_type = news_type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCat_id() {
		return cat_id;
	}
	public void setCat_id(String cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}
	public String getThumb() {
		return thumb;
	}
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}
	public String getUpdate_date() {
		return update_date.replace(".0", "");
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}
	public String getTime_stamp() {
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp) {
		this.time_stamp = time_stamp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDel_flag() {
		return del_flag;
	}
	public void setDel_flag(String del_flag) {
		this.del_flag = del_flag;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAudit_state() {
		return audit_state;
	}
	public void setAudit_state(String audit_state) {
		this.audit_state = audit_state;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCt_type() {
		return ct_type;
	}
	public void setCt_type(String ct_type) {
		this.ct_type = ct_type;
	}

  
	
	
    
}
