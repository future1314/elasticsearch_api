package com.ddl.elasticsearch.study.search;

import com.ddl.elasticsearch.study.ElasticsearchClientBase;
import org.elasticsearch.action.search.MultiSearchResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;

/**
 * 在同一API中执行多个搜索请求
 * 官方文档 @see <a href='https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/java-search-scrolling.html'></a>
 * 中文文档 @see <a href='https://es.quanke.name/search-api/using-scrolls-in-java.html'></a>
 * Created by http://quanke.name on 2017/11/15.
 */
public class MultiSearchAPI extends ElasticsearchClientBase {
    @Test
    public void testMultiSearch() throws Exception {

        SearchRequestBuilder srb1 = client
                .prepareSearch().setQuery(QueryBuilders.queryStringQuery("elasticsearch")).setSize(2);//只显示前几条

        SearchRequestBuilder srb2 = client
                .prepareSearch().setQuery(QueryBuilders.matchQuery("user", "kimchy")).setSize(2);//只显示前几条

        MultiSearchResponse sr = client.prepareMultiSearch()
                .add(srb1)
                .add(srb2)
                .get();

        // You will get all individual responses from MultiSearchResponse#getResponses()
        long nbHits = 0;
        for (MultiSearchResponse.Item item : sr.getResponses()) {
            SearchResponse response = item.getResponse();

            nbHits += response.getHits().getTotalHits();

            println(response);
        }

        System.out.println("nbHits:" + nbHits);

    }

}
