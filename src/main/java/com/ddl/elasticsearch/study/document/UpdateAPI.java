package com.ddl.elasticsearch.study.document;

import com.ddl.elasticsearch.study.ElasticsearchClientBase;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.junit.Test;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

/**
 * 更新文档
 * Created by http://quanke.name on 2017/11/10.
 */
public class UpdateAPI extends ElasticsearchClientBase {

    /**
     * 使用 UpdateRequest 操作
     *
     * @throws Exception
     */
    @Test
    public void testForUpdateRequest() throws Exception {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.index("twitter");
        updateRequest.type("tweet");
        updateRequest.id("2");
        updateRequest.doc(jsonBuilder()
                .startObject()
                .field("user", "http://quanke.name")
                .endObject());
        client.update(updateRequest).get();
    }

    /**
     * 使用prepareUpdate
     *
     * @throws Exception
     */
    @Test
    public void testForPrepareUpdate() throws Exception {
//        client.prepareUpdate("twitter", "tweet", "2")
//                .setScript(new Script(ScriptType.INLINE, "ctx._source.user = \"quanke.name\"", null, null))
//                .get();//NullPointerException

        client.prepareUpdate("twitter", "tweet", "2")
                .setDoc(jsonBuilder()
                        .startObject()
                        .field("user", "quanke.name")
                        .endObject())
                .get();

    }

}
