package com.ddl.elasticsearch.study.document;

import com.ddl.elasticsearch.study.ElasticsearchClientBase;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Index API 可以存储一个JSON格式的文档
 * Created by http://quanke.name on 2017/11/10.
 */
public class IndexAPI extends ElasticsearchClientBase {


    /**
     * 使用json字符串来构造文档内容
     *
     * @throws Exception
     */
    @Test
    public void testForUseStr() throws Exception {
        String json = "{" +
                "\"user\":\"kimchy\"," +
                "\"postDate\":\"2019-01-30\"," +
                "\"message\":\"trying out Elasticsearch\"" +
                "}";
        client.prepareIndex("twitter", "tweet", "2")
                .setSource(json, XContentType.JSON)
                .get();

        System.out.println("testForUseStr twitter 创建成功");

    }

    /**
     * 使用map来构造文档内容
     *
     * @throws Exception
     */
    @Test
    public void testForUseMap() throws Exception {

        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user", "kimchy");
        json.put("postDate", "2017-10-10");
        json.put("message", "trying out Elasticsearch");

        IndexResponse response = client.prepareIndex("twitter", "tweet", String.valueOf(1))
                .setSource(json)
                .get();

        System.out.println(response.getResult());//CREATED

//        Assert.assertEquals("CREATED", response.getResult().name());

        System.out.println("testForUseMap twitter 创建成功");
    }

    /**
     * 使用elasticsearch官方提供的json构造器来构造文档内容
     *
     * @throws Exception
     */
    @Test
    public void testForUseXContentBuilder() throws Exception {
        XContentBuilder builder = XContentFactory.jsonBuilder()
                .startObject()
                .field("user", "kimchy")
                .field("postDate", new Date())
                .field("age",10)
                .field("gender","male")
                .field("message", "trying out Elasticsearch")
                .endObject();
        client.prepareIndex("twitter", "tweet", "21")//21,22,23,24,25
        //client.prepareIndex("twitter", "tweet", "21,22,23,24,25")
                .setSource(builder)
                .get();//有责更新之

        System.out.println("testForUseXContentBuilder twitter 创建成功");
    }


}
