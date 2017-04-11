/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.feedback.entity.feedback_s;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * feedback_functionEntity
 * @author feedback_author
 * @version 2016-06-14
 */
public class CmsBookFeedback extends DataEntity<CmsBookFeedback> {
	
	private static final long serialVersionUID = 1L;
	private String readerUserId;		// 普通用户id
	private String content;		// 反馈的内容
	private String title;		// 反馈标题
	
	public CmsBookFeedback() {
		super();
	}

	public CmsBookFeedback(String id){
		super(id);
	}

	@Length(min=1, max=64, message="普通用户id长度必须介于 1 和 64 之间")
	public String getReaderUserId() {
		return readerUserId;
	}

	public void setReaderUserId(String readerUserId) {
		this.readerUserId = readerUserId;
	}
	
	@Length(min=1, max=255, message="反馈的内容长度必须介于 1 和 255 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=100, message="反馈标题长度必须介于 0 和 100 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
}