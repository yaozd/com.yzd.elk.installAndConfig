package com.yzd.example.elasticsearch.demo.service;

import com.google.common.collect.ImmutableList;
import com.yzd.example.elasticsearch.demo.configES.SearchTemplateES;
import com.yzd.example.elasticsearch.demo.configES.TemplateEnumES;
import com.yzd.example.elasticsearch.demo.configES.TemplateInfoES;
import com.yzd.example.elasticsearch.demo.model.News;
import com.yzd.example.elasticsearch.demo.model.NewsWhere;
import com.yzd.example.elasticsearch.demo.utils.ESWhereUtil;
import com.yzd.example.elasticsearch.demo.utils.FastJsonUtil;
import io.searchbox.client.JestClient;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.indices.CreateIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/***
 *
 *
 * Created by yzd on 2018/8/21 17:56.
 */
@Service
public class SearchService {

    @Autowired
    JestClient client ;

public void  test(){
    String query = "";

    Search search = new Search.Builder(query).addIndex("school").addType("student").build();

    try {
        SearchResult result = client.execute(search);
    } catch (IOException e) {
        throw new IllegalStateException(e);
    }
}

    /**
     * 创建es news索引
     */
    public void builderSearchIndex() {
        try {
            client.execute(new CreateIndex.Builder("news").build());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
      /*  int num = 10000;
        long start = System.currentTimeMillis();
        try {
            // 如果索引存在,删除索引
            DeleteIndex deleteIndex = new DeleteIndex("news");
            jestClient.execute(deleteIndex);

            // 创建索引
            CreateIndex createIndex = new CreateIndex("news");
            jestClient.execute(createIndex);
            // Bulk 两个参数1:索引名称2:类型名称(用文章(article)做类型名称)
            Bulk bulk = new Bulk();
            // 添加添加100万条假数据去服务端(ES)
            for (int i = 0; i < num; i++) {
                News news = new News();
                news.setId(i + 1);
                news.setTitle("elasticsearch RESTful搜索引擎-(java jest 使用[入门])" + (i + 1));
                news.setContent("好吧下面我介绍下jest(第三方工具),个人认为还是非常不错的...想对ES用来更好,多多研究源代码吧...迟点,会写一些关于ES的源代码研究文章,现在暂时还是入门的阶段.哈..(不敢,不敢)"
                        + (i + 1));
                bulk.addIndex(new Index.Builder(news).build());
            }
            jestClient.execute(bulk);
        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("创建索引时间:数据量是  " + num + "记录,共用时间 -->> " + (end - start) + " 毫秒");*/
    }
    public void createDocment(){
        News source=new News();
        source.setDocumentId(UUID.randomUUID().toString());
        source.setTitle("测试");
        source.setDocumentVersion(1L);
        source.setContent("创建文档");
        try {
            Index index = new Index.Builder(source).index("news").type("twitter").build();
            client.execute(index);
            client.execute(new CreateIndex.Builder("news").build());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    /**
     * 搜索新闻
     * 目前（2018-08-22）暂时不太会用
     * @return
     */
    public void searchsDocment() {
        //真正生产中使用，可以把查询的模板文件放在xml文件中，这样会比较好管理，而且易读
        String query = "{\"must\":[{\"match_all\":{}}]}";

        Search search = new Search.TemplateBuilder(query)
                // multiple index or types can be added.
                .addIndex("news")
                .addType("twitter")
                .build();
        try {
            SearchResult result = client.execute(search);
            System.out.println(result.getJsonString());
            System.out.println(FastJsonUtil.serialize(result));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        /*try {
            long start = System.currentTimeMillis();
            QueryBuilder queryBuilder = QueryBuilders.queryString(param);
            Search search = new Search(Search.createQueryWithBuilder(queryBuilder.toString()));
            search.addIndex("news");
            search.addType("article");
            JestResult result = jestClient.execute(search);
            long end = System.currentTimeMillis();
            System.out.println("在100万条记录中,搜索新闻,共用时间 -->> " + (end - start) + " 毫秒");
            return result.getSourceAsObjectList(News.class);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;*/
    }

    /***
     *
     *
     * 参考：SpringBoot 整合Jest实例代码讲解
     * https://www.jb51.net/article/145981.htm
     */
    public void searchDomentByJson(){
            //真正生产中使用，可以把查询的模板文件放在xml文件中，这样会比较好管理，而且易读,同时在xml中写好对应的sql语句
            //查询表达式可以直接从【Elastic HD(-360企业安全集团终端安全子公司)】上sql转为DSL后直接使用
            //查询表达式
            //对应的sql语句：select * from news
            String json ="{\"query\":{\"bool\":{\"must\":[{\"match_all\":{}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}";
            //构建搜索功能
            Search search = new Search.Builder(json).addIndex("news").addType("twitter").build();
            try {
                SearchResult result = client.execute(search);
                System.out.println(result.getJsonString());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            } 
    }

    /***
     * 根据模板按条件进行查询
     *
     * @param where
     */
    public void searchDomentByJsonAndWhere(NewsWhere where) {
        String json ="{\"query\":{\"bool\":{\"must\":[{\"term\":{\"content.keyword\":\"#NewsWhere.content#\"}}],\"must_not\":[],\"should\":[]}},\"from\":0,\"size\":10,\"sort\":[],\"aggs\":{}}";
        String whereJson=FastJsonUtil.serialize(where);
        Map<String,Object>whereMap=FastJsonUtil.json2Map(whereJson);
        for (Map.Entry<String, Object> entry : whereMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            //将查询条件中的引号(包括双引号与单引号)进行转义
            json=json.replace(entry.getKey().toString(),ESWhereUtil.convertQuotation(entry.getValue().toString()));
        }
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("news").addType("twitter").build();
        try {
            SearchResult result = client.execute(search);
            System.out.println(result.getJsonString());
            List<SearchResult.Hit<News, Void>> hits = result.getHits(News.class);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    public void searchDomentByJsonTemplateInfo(String json,NewsWhere where){
        String whereJson=FastJsonUtil.serialize(where);
        Map<String,Object>whereMap=FastJsonUtil.json2Map(whereJson);
        for (Map.Entry<String, Object> entry : whereMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            //将查询条件中的引号(包括双引号与单引号)进行转义
            json=json.replace(entry.getKey().toString(),ESWhereUtil.convertQuotation(entry.getValue().toString()));
        }
        //构建搜索功能
        Search search = new Search.Builder(json).addIndex("news").addType("twitter").build();
        try {
            SearchResult result = client.execute(search);
            System.out.println(result.getJsonString());
            List<SearchResult.Hit<News, Void>> hits = result.getHits(News.class);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    /***
     * 通过配置信息读取索引值与索引类型值
     * @param templateEnumES
     * @param where
     */
    public void searchDomentByJsonTemplateInfo2(TemplateEnumES templateEnumES, NewsWhere where){
        TemplateInfoES templateInfoES= SearchTemplateES.get(templateEnumES);
        String json=templateInfoES.getTemplateJSON();
        String indexStr=templateInfoES.getIndexOfDocument().trim();
        List<String> indexList = Arrays.asList(indexStr.split(","));
        String indexTypeStr=templateInfoES.getIndexTypeOfDocument().trim();
        List<String> indexTypeList = Arrays.asList(indexTypeStr.split(","));
        String whereJson=FastJsonUtil.serialize(where);
        Map<String,Object>whereMap=FastJsonUtil.json2Map(whereJson);
        for (Map.Entry<String, Object> entry : whereMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            //将查询条件中的引号(包括双引号与单引号)进行转义
            json=json.replace(entry.getKey().toString(),ESWhereUtil.convertQuotation(entry.getValue().toString()));
        }
        //构建搜索功能
        Search search = new Search.Builder(json).addIndices(indexList).addTypes(indexTypeList).build();
        try {
            SearchResult result = client.execute(search);
            System.out.println(result.getJsonString());
            List<SearchResult.Hit<News, Void>> hits = result.getHits(News.class);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}