package com.yzd.example.elasticsearch.demo.model;

import com.alibaba.fastjson.annotation.JSONField;

/***
 *NewsWhere 的
 *
 * Created by yzd on 2018/8/22 10:00.
 */

public class NewsWhere {
    //#NewsWhere.title#用于替换模板中查询条件对应的KEY
    @JSONField(name="#NewsWhere.title#")
    private String title;
    @JSONField(name="#NewsWhere.content#")
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
