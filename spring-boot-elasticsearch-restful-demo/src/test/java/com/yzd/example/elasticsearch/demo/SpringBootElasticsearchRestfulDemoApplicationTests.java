package com.yzd.example.elasticsearch.demo;

import com.yzd.example.elasticsearch.demo.model.NewsWhere;
import com.yzd.example.elasticsearch.demo.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.plaf.PanelUI;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootElasticsearchRestfulDemoApplicationTests {

    @Autowired
    SearchService searchService;
	@Test
	public void contextLoads() {
	}
    @Test
    public void createIndex_test() {
        searchService.builderSearchIndex();
    }
    @Test
    public void createDocment(){
	    searchService.createDocment();
    }
    @Test
    public void searchsDocment(){
        searchService.searchsDocment();
    }

    /***
     *  查询推荐使用此方法
     *  //真正生产中使用，可以把查询的模板文件放在xml文件中，这样会比较好管理，而且易读
     *  //查询表达式可以直接从【Elastic HD(-360企业安全集团终端安全子公司)】上sql转为DSL后直接使用
     */
    @Test
    public void searchDomentByJson(){
        searchService.searchDomentByJson();
    }

    /***
     * 查询推荐使用此方法-byArvin推荐（2018-08-22-1057）
     * 通过条件进行查询
     */
    @Test
    public void searchDomentByJsonAndWhere(){
        NewsWhere where=new NewsWhere();
        //where.setContent("创建文档\"");
        where.setContent("创建文档1");
        searchService.searchDomentByJsonAndWhere(where);
    }
}
