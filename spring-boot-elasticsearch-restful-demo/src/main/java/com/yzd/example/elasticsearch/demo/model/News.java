package com.yzd.example.elasticsearch.demo.model;

import io.searchbox.annotations.JestId;
import io.searchbox.annotations.JestVersion;

/***
 *
 *
 * Created by yzd on 2018/8/21 17:53.
 */

public class News {

    @JestId
    private String documentId;
    @JestVersion
    private Long documentVersion;
    private String title;
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

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public Long getDocumentVersion() {
        return documentVersion;
    }

    public void setDocumentVersion(Long documentVersion) {
        this.documentVersion = documentVersion;
    }
}
