package com.teach.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolrBean {

	/**
	 * add index
	 */
	public static void addIndex()
	{
		HttpSolrClient server = SolrServer.getServer();
		List <TestBO> c = new ArrayList<TestBO>();
		try {
			TestBO bo = new TestBO();
			bo.setId("2001");
			bo.setName_s("mary");
			bo.setScore_i(89);
			c.add(bo);
			TestBO bo2 = new TestBO();
			bo2.setId("2002");
			bo2.setName_s("honghong");
			bo2.setScore_i(100);
			c.add(bo2);
			
			server.addBeans(c);
			server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * delete by condition 
	 */
	public static void delete(String condition){
		HttpSolrClient server = SolrServer.getServer();
		try {
			server.deleteByQuery(condition);
			server.commit();
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * search index
	 */
	public static void search()
	{
		HttpSolrClient server = SolrServer.getServer();
		SolrQuery query = new SolrQuery();
		query.setQuery("*:*");
		query.setStart(0);
		query.setRows(5);
		QueryResponse queryResponse;
		try {
			queryResponse = server.query(query);
			List<TestBO>  list = queryResponse.getBeans(TestBO.class);
		        for (TestBO bo : list) {
		        	System.out.println(bo.getName_s());
		        }
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String[] args) {
		search();
	}

}
