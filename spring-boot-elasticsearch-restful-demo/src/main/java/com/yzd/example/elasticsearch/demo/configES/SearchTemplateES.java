package com.yzd.example.elasticsearch.demo.configES;

import com.yzd.example.elasticsearch.demo.utils.FileUtil;
import com.yzd.example.elasticsearch.demo.utils.XmlUtil2;

import java.util.HashMap;
import java.util.Map;

/***
 * 查询模板配置信息XML
 *
 * Created by yzd on 2018/8/22 15:22.
 */

public class SearchTemplateES {
    private static Map<Integer, TemplateInfoES> configMap = new HashMap<Integer, TemplateInfoES>();

    static {
        String insurerXmlStr = FileUtil.read("/TemplateInfoES.xml", "utf-8");
        TemplateInfoXmlES templateXmlObj = XmlUtil2.fromXML(insurerXmlStr, TemplateInfoXmlES.class);
        templateXmlObj.getTemplateInfo().forEach(t -> configMap.put(t.getTemplateID(), t));
    }

    public static TemplateInfoES get(TemplateEnumES templateEnumES) {
        return configMap.get(templateEnumES.getValue());
    }
}
