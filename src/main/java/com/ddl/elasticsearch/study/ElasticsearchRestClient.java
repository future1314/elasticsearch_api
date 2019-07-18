package com.ddl.elasticsearch.study;

import com.ddl.elasticsearch.study.util.Utils;
import org.elasticsearch.action.search.SearchResponse;

/**
 * Elasticsearch Rest Client
 * <p>
 * Created by http://quanke.name on 2017/11/10.
 */
public class ElasticsearchRestClient {


    protected void println(SearchResponse searchResponse) {
        Utils.println(searchResponse);
    }
}
