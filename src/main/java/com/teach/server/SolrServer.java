package com.teach.server;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.util.SimpleOrderedMap;

public class SolrServer {

	private static HttpSolrClient server = null;
	private static String url ="http://localhost:8080/solr/test";
	
	public static HttpSolrClient getServer() 
	{
		if(server == null){
			server = new HttpSolrClient(url);
			server.setDefaultMaxConnectionsPerHost(1000); 
			server.setMaxTotalConnections(10000);
			server.setConnectionTimeout(60000);//�������ӳ�ʱʱ�䣨��λ���룩 1000
			server.setSoTimeout(60000);//// ���ö���ݳ�ʱʱ��(��λ����) 1000
			server.setFollowRedirects(false);//��ѭ�Ӷ���
			server.setAllowCompression(true);//����ѹ��
		}
		/*SolrQuery query =  new SolrQuery();
		List<NewsQuery> newsquerys = new ArrayList<NewsQuery>();
		query.setQuery("id:"+8107).setParam("fl", "id,title,keywords")
		.setParam("mlt", "true").setParam("mlt.fl", "text").setParam("mlt.mindf", "1").setParam("mlt.mintf", "1").setParam("mlt.count", String.valueOf("10"));
		QueryResponse response =  server.query(query);
		SimpleOrderedMap<SolrDocumentList> mltResults = (SimpleOrderedMap<SolrDocumentList>) response.getResponse().get("moreLikeThis");
        for (int i = 0; i < mltResults.size(); i++) {
            SolrDocumentList items = mltResults.getVal(i);
            for (SolrDocument doc : items) {
                String idStr = doc.getFieldValue("id").toString();
                String del_flag = doc.getFieldValue("del_flag").toString();
                String audit_state = doc.getFieldValue("audit_state").toString();
                if (idStr.equals(8107)) continue;// 排除本身
                if("1".equals(del_flag)) continue;
                if(!"3".equals(audit_state)) continue;
                NewsQuery article = new NewsQuery();
                article.setId(idStr);
                if(doc.getFieldValue("title")!=null){
                	article.setTitle(doc.getFieldValue("title").toString());
                }
                
                if(doc.getFieldValue("cat_id")!=null){
                	article.setCat_id(doc.getFieldValue("cat_id").toString());
                }
                if(doc.getFieldValue("cat_name")!=null){
                	article.setCat_name(doc.getFieldValue("cat_name").toString());
                }
                if(doc.getFieldValue("thumb")!=null){
                	article.setThumb(doc.getFieldValue("thumb").toString());
                }
                if(doc.getFieldValue("ct_type")!=null){
                	article.setCt_type(doc.getFieldValue("ct_type").toString());
                }
                if(doc.getFieldValue("update_date")!=null){
                	article.setUpdate_date(doc.getFieldValue("update_date").toString());
                }
                if(doc.getFieldValue("id")!=null){
                	article.setId(doc.getFieldValue("id").toString());
                }
                if(doc.getFieldValue("news_type")!=null){
                	article.setNews_type(doc.getFieldValue("news_type").toString());
                }
                if(doc.getFieldValue("memberName")!=null){
                	article.setMemberName(doc.getFieldValue("memberName").toString());
                }
                if(doc.getFieldValue("headimg")!=null){
                	article.setHeadimg(doc.getFieldValue("headimg").toString());
                }
                
             
                newsquerys.add(article);                
      
            }
        }*/

      return server;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getServer());
	}

}
