package com.ddl.elasticsearch.study.document;

import com.ddl.elasticsearch.study.ElasticsearchClientBase;
import org.elasticsearch.action.delete.DeleteResponse;
import org.junit.Test;

/**
 * 删除文档
 * Created by http://quanke.name on 2017/11/10.
 */
public class DeleteAPI extends ElasticsearchClientBase {

    @Test
    public void testForDeleteAPI() throws Exception {
        DeleteResponse response = client.prepareDelete("twitter", "tweet", "21").get();//不存在 也成功。

        System.out.println("删除成功！");
    }
}
