package com.yzd.example.elasticsearch.demo.service;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.indices.CloseIndex;
import io.searchbox.indices.CreateIndex;
import io.searchbox.indices.DeleteIndex;
import io.searchbox.indices.aliases.AddAliasMapping;
import io.searchbox.indices.aliases.ModifyAliases;
import io.searchbox.indices.aliases.RemoveAliasMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/***
 *
 *
 * Created by yzd on 2018/8/23 9:38.
 */
@Service
public class ElasticsearchService {
    //更多ES的操作可以参考jest的单元测试
    @Autowired
    JestClient client ;
    /***
     * 创建索引
     *
     * 创建一个索引，这个索引的名称最好带上版本号，比如my_index_v1,my_index_v2等。
     * elasticsearch更改mapping(不停服务重建索引)
     * https://blog.csdn.net/u014042758/article/details/79617591
     */
    public void createIndex() {
        String indexName="oldindex";
        if(isContainUpperCase(indexName)){
            throw new IllegalArgumentException("索引名称不能包含大写字母");
        }
        try {
            client.execute(new CreateIndex.Builder("oldindex").build());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    //别名操作-参考Jest的ModifyAliasesIntegrationTest
    /***
     * 添加别名
     * 在使用别名的情况：
     *  1.插入与更新操作依然使用索引名-插入与更新操作也可以使用别名具体看实际情况
     *  2.查询操作使用别名
     */
    public void addAliases() {
        ModifyAliases modifyAliases = new ModifyAliases.Builder(new AddAliasMapping.Builder("oldindex", "alias").build()).build();
        try {
            JestResult result = client.execute(modifyAliases);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    /***
     * 修改别名
     */
    public void modifyAliases(){
        ModifyAliases modifyAliases = new ModifyAliases.Builder(new RemoveAliasMapping.Builder("oldindex", "alias").build()).build();
        try {
            JestResult result = client.execute(modifyAliases);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }


    /***
     * 删除索引：目前推荐的做法：先关闭索引然后再删除索引。-byArvin(2018-08-23)
     * 关闭索引后就不会再有新的数据写到索引，这时再删除索引会好一些
     */
    public void closeIndexThenDeleteIndex() {
        CloseIndex closeIndex = new CloseIndex.Builder("oldindex").build();
        try {
            JestResult resultCloseIndex = client.execute(closeIndex);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        DeleteIndex indicesExists = new DeleteIndex.Builder("oldindex").build();
        try {
            JestResult resultDeleteIndex = client.execute(indicesExists);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /***
     * 关闭索引
     */
    public void closeIndex() {
        CloseIndex closeIndex = new CloseIndex.Builder("oldindex").build();
        try {
            JestResult result = client.execute(closeIndex);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    /***
     * 删除索引
     */
    public void deleteIndex(){
        DeleteIndex indicesExists = new DeleteIndex.Builder("oldindex").build();
        try {
            JestResult result = client.execute(indicesExists);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /***
     * 是否包含大写字母
     * @param str
     * @return
     */
    public boolean isContainUpperCase(String str) {
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            if(c >= 65 && c <= 90) {
                return true;
            }
        }
        //str.charAt(index)
        return false;
    }
}
