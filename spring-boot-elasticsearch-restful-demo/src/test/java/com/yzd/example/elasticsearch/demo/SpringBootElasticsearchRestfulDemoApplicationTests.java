package com.yzd.example.elasticsearch.demo;

import com.yzd.example.elasticsearch.demo.configES.SearchTemplateES;
import com.yzd.example.elasticsearch.demo.configES.TemplateEnumES;
import com.yzd.example.elasticsearch.demo.configES.TemplateInfoES;
import com.yzd.example.elasticsearch.demo.configES.TemplateInfoXmlES;
import com.yzd.example.elasticsearch.demo.model.NewsWhere;
import com.yzd.example.elasticsearch.demo.service.SearchService;
import com.yzd.example.elasticsearch.demo.utils.XmlUtil2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.plaf.PanelUI;
import java.util.ArrayList;
import java.util.List;

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
    @Test
    public void searchDomentByJsonTemplateInfoXML(){
        TemplateInfoES templateInfoES= SearchTemplateES.get(TemplateEnumES.查询前10条新闻);
        String json=templateInfoES.getTemplateJSON();
        NewsWhere where=new NewsWhere();
        where.setContent("创建文档");
        searchService.searchDomentByJsonTemplateInfo(json,where);
    }

    /***
     * 通过配置信息读取索引值与索引类型值
     */
    @Test
    public void searchDomentByJsonTemplateInfo2(){
        NewsWhere where=new NewsWhere();
        where.setContent("创建文档");
        searchService.searchDomentByJsonTemplateInfo2(TemplateEnumES.查询前10条新闻带索引配置,where);
    }
    @Test
    public void printTemplateInfoXml(){
        //List<TemplateInfoES> templateInfoESList=new ArrayList<>();
        //templateInfoESList.add(new TemplateInfoES(TemplateEnumES.查询商品信息.getValue(),"查询商品信息","select * from product","template-json","查询商品信息-WHERE"));
        //TemplateInfoXmlES templateInfoXmlESObj=new TemplateInfoXmlES();
        //templateInfoXmlESObj.setTemplateInfo(templateInfoESList);
        //System.out.println(XmlUtil2.toXMLBeautiful(templateInfoXmlESObj,"utf-8"));
    }
}
