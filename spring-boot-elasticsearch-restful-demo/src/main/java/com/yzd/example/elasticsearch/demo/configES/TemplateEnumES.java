package com.yzd.example.elasticsearch.demo.configES;

/***
 *
 *
 * Created by yzd on 2018/8/22 15:38.
 */

public enum TemplateEnumES {
    查询商品信息(1),
    查询前10条新闻(2),
    查询前10条新闻带索引配置(3),
    查询订单信息(4);
    private int value;

    private TemplateEnumES(int val) {
        this.value = val;
    }

    public int getValue() {
        return this.value;
    }

    public static TemplateEnumES getEnum(int index) {
        for (TemplateEnumES c : TemplateEnumES.values()) {
            if (c.getValue() == index) {
                return c;
            }
        }
        return null;
    }
}
