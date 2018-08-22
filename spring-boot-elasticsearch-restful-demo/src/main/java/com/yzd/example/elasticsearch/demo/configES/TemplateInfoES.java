package com.yzd.example.elasticsearch.demo.configES;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * ES查询模板信息
 *
 * Created by yzd on 2018/8/22 15:24.
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateInfoES {
    // 模板ID
    private int templateID;
    // 模板名称
    private String templateName;
    // 模板描述信息
    private String templateDes;
    // 模板对应索引信息-多个索引中间用”,“(英文逗号)分割：eg:news,orders
    private String indexOfDocument;
    // 模板对应索引类型信息-多个索引类型中间用”,“(英文逗号)分割：eg:newstype,footype
    private String indexTypeOfDocument;
    // 模板对应SQL
    private String templateSQL;
    // 模板对应JSON
    private String templateJSON;


}

