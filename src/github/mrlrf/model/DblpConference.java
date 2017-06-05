package github.mrlrf.model;

import github.mrlrf.crawlercore.Spider;

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
        getPapersFromUrl();
    }

    public void getPapersFromUrl () {
        //TODO:必须将每个论文的所有信息作为整体爬取

        ArrayList<Paper> papers = new ArrayList<Paper>();

        String source = Spider.sendGet(this.content_url);

        Pattern namePattern = Pattern.compile("<span class=\"title\" itemprop=\"name\">(.+?)</span>");
        Matcher nameMatcher = namePattern.matcher(source);
        Pattern pagePattern = Pattern.compile("<span itemprop=\"pagination\">(\\d+-\\d+)</span>");
        Matcher pageMatcher = pagePattern.matcher(source);
        Pattern urlPattern = Pattern.compile("<a href=\"(http://[^><\"]+?)\" itemprop=\"url\"><img");
        Matcher urlMatcher = urlPattern.matcher(source);

        int i = 0;
        while (nameMatcher.find()) {
            String name = nameMatcher.group(1);
            String page = "";
            String url = "";
            if (pageMatcher.find()) page = pageMatcher.group(1);
            if (urlMatcher.find()) {
                System.out.println("come this");
                url = urlMatcher.group(1);
            }
            papers.add(new Paper(this.conference_id, name, page, url));
            System.out.println("Create paper:" + i++ + "\nname:" + name + ";\nurl:" + url);
        }

        this.papers = papers;
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
