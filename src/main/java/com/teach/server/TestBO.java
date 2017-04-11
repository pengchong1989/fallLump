package com.teach.server;

import java.io.Serializable;

import org.apache.solr.client.solrj.beans.Field;

public class TestBO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Field
    public String id;
    @Field
    public String name_s;
    @Field
    public int score_i;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName_s() {
		return name_s;
	}
	public void setName_s(String name_s) {
		this.name_s = name_s;
	}
	public int getScore_i() {
		return score_i;
	}
	public void setScore_i(int score_i) {
		this.score_i = score_i;
	}
    
}
