package com.yzd.example.elasticsearch.demo.utils;

/***
 *
 *
 * Created by yzd on 2018/8/22 10:19.
 */

public class ESWhereUtil {
    /***
     *
     * 将查询条件中的引号(包括双引号与单引号)进行转义
     * @param where
     * @return
     */
    public  static String convertQuotation(String where){
        if(where==null&&where.isEmpty()){return where;}
        if(where.contains("\"")||where.contains("'")){ return where.replace("\"","\\\"").replace("'","\'");}
        return where;
    }
}
