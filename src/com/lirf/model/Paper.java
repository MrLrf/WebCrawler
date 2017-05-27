package com.lirf.model;

import java.util.UUID;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/27 14:52
 */
public class Paper {
    private String paper_id;
    private String conference_id;
    private String paper_name;
    private String page_num;

    public Paper(String conference_id, String paper_name, String page_num) {
        this.paper_id = UUID.randomUUID().toString();
        this.conference_id = conference_id;
        this.paper_name = paper_name;
        this.page_num = page_num;
    }

    public String getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(String paper_id) {
        this.paper_id = paper_id;
    }

    public String getConference_id() {
        return conference_id;
    }

    public void setConference_id(String conference_id) {
        this.conference_id = conference_id;
    }

    public String getPaper_name() {
        return paper_name;
    }

    public void setPaper_name(String paper_name) {
        this.paper_name = paper_name;
    }

    public String getPage_num() {
        return page_num;
    }

    public void setPage_num(String page_num) {
        this.page_num = page_num;
    }
}
