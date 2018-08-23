package com.yzd.example.elasticsearch.demo.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/***
 *
 *
 * Created by yzd on 2018/8/23 10:16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchServiceTest {
    @Autowired
    ElasticsearchService elasticsearchService;
    @Test
    public void createIndex() {
        elasticsearchService.createIndex();
    }
    @Test
    public void addAliases() {
        elasticsearchService.addAliases();
    }
    @Test
    public void modifyAliases() {
        elasticsearchService.modifyAliases();
    }

    @Test
    public void closeIndexThenDeleteIndex() {
        elasticsearchService.closeIndexThenDeleteIndex();
    }

    @Test
    public void closeIndex() {
        elasticsearchService.closeIndex();
    }

    @Test
    public void deleteIndex() {
        elasticsearchService.deleteIndex();
    }


}