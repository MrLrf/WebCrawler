package github.mrlrf.model;

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
    private String paper_topical;
    private String paper_name;
    private int page_num;
    private String paper_url;

    public Paper(String conference_id, String paper_name, String page_num, String paper_url) {
        this.paper_id = UUID.randomUUID().toString();
        this.conference_id = conference_id;
        this.paper_topical = "";
        this.paper_name = paper_name;
        if (page_num.equals("")) {
            this.page_num = 0;
        } else {
            String[] nums = page_num.split("-");
            this.page_num = Integer.parseInt(nums[1]) - Integer.parseInt(nums[0]);
        }
        this.paper_url = paper_url;
    }

    @Override
    public String toString() {
        return "Paper{" +
                ", paper_name='" + paper_name + '\'' +
                ", page_num=" + page_num +
                ", paper_url='" + paper_url + '\'' +
                '}';
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

    public int getPage_num() {
        return page_num;
    }

    public void setPage_num(int page_num) {
        this.page_num = page_num;
    }

    public String getPaper_topical() {
        return paper_topical;
    }

    public void setPaper_topical(String paper_topical) {
        this.paper_topical = paper_topical;
    }

    public String getPaper_url() {
        return paper_url;
    }

    public void setPaper_url(String paper_url) {
        this.paper_url = paper_url;
    }
}
