package com.lirf.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/27 14:50
 */
public class DblpConference {
    private String conference_id;
    private String conference_name;
    private String conference_content;
    private String conference_date;
    private String content_url;
    private List<Paper> papers;

    public DblpConference(String conference_name, String conference_content,String content_url) {
        this.conference_id = UUID.randomUUID().toString();
        this.conference_name = conference_name;
        this.conference_content = conference_content;
        Pattern datePattern = Pattern.compile("((January|February|March|April|May|June|July|August|September|October|November|December)\\s(\\d{1,2}|\\d{1,2}-\\d{1,2}),\\s\\d{4})");
        Matcher dateMatcher = datePattern.matcher(conference_content);
        if (dateMatcher.find()) this.conference_date = dateMatcher.group(1);
        this.content_url = content_url;
        this.papers = new ArrayList<Paper>();
    }

    @Override
    public String toString() {
        return "DblpConference{" +
                "conference_id='" + conference_id + '\'' +
                ", conference_name='" + conference_name + '\'' +
                ", conference_date='" + conference_date + '\'' +
                ", content_url='" + content_url + '\'' +
                ", papers=" + papers +
                '}';
    }

    public String getConference_id() {
        return conference_id;
    }

    public void setConference_id(String conference_id) {
        this.conference_id = conference_id;
    }

    public String getConference_name() {
        return conference_name;
    }

    public void setConference_name(String conference_name) {
        this.conference_name = conference_name;
    }

    public String getConference_date() {
        return conference_date;
    }

    public void setConference_date(String conference_date) {
        this.conference_date = conference_date;
    }

    public String getContent_url() {
        return content_url;
    }

    public void setContent_url(String content_url) {
        this.content_url = content_url;
    }

    public List<Paper> getPapers() {
        return papers;
    }

    public void setPapers(List<Paper> papers) {
        this.papers = papers;
    }

    public String getConference_content() {
        return conference_content;
    }

    public void setConference_content(String conference_content) {
        this.conference_content = conference_content;
    }
}
