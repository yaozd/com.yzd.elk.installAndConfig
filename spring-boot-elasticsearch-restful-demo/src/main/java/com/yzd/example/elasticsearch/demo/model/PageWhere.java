package com.yzd.example.elasticsearch.demo.model;

import com.alibaba.fastjson.annotation.JSONField;

/***
 *
 *
 * Created by yzd on 2018/8/22 16:05.
 */

public class PageWhere {
    // 指定的或是页面参数
    // 当前页
    private int currentPage;
    // 每页显示多少条
    @JSONField(name="#PageWhere.pageSize#")
    private int pageSize;
    // 页码列表的开始索引（包含）
    @JSONField(name="#PageWhere.pageBeginIndex#")
    private int pageBeginIndex;
}
