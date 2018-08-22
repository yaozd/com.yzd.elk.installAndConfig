package com.yzd.example.elasticsearch.demo.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/***
 *
 *
 * Created by yzd on 2018/8/22 15:15.
 */

public class FileUtil {
    public static String read(String path,String encoding) {
        InputStream inputStream = FileUtil.class.getClass().getResourceAsStream(path);
        try {
            return IOUtils.toString(inputStream, encoding);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(inputStream);
        }
        return null;
    }
}
