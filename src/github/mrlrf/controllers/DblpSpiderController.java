package github.mrlrf.controllers;

import github.mrlrf.Services.interfaces.DblpConferenceService;
import github.mrlrf.Services.interfaces.PaperService;
import github.mrlrf.model.DblpConference;
import github.mrlrf.model.Paper;
import github.mrlrf.utils.HttpClientUtil;
import github.mrlrf.utils.TranslateUtilByBaidu;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 爬取dblp上论文的Controller
 *
 * @Author lirf
 * @Date 2017/6/11 14:52
 */
public class DblpSpiderController {

    @Autowired
    private DblpConferenceService dblpConferenceService;
    @Autowired
    private PaperService paperService;

    /**
     * 爬取会议
     * @param urls Map: Key(会议名), Value(URL)
     */
    public static List<DblpConference> conferenceSpider(Map<String, String> urls) {
        //String url = "http://dblp.uni-trier.de/db/conf/recsys/";
        List<DblpConference> conferences = new ArrayList<>();
        String result = null;
        for (String key : urls.keySet()) {
            try {
                String url = urls.get(key);
                result = HttpClientUtil.getHTML(url);
                Document document = Jsoup.parse(result);
                Elements datas = document.select("div.data");

                //System.out.println(url);
                //int index = 0;
                for (Element data : datas) {
                    //System.out.println(index++);
                    List<String> authorList = new ArrayList<>();
                    Elements authors = data.select("span[itemprop=author]");
                    for (Element author : authors) {
                        authorList.add(author.select("span[itemprop=name]").first().html());
                    }
                    Element date = data.select("span[itemprop=datePublished]").first();
                    Element title = data.select("span.title").first();
                    Element href = data.select("a:containsOwn([contents])").first();

                    String conferenceUrl = "";
                    if (href != null) {
                        conferenceUrl = href.attr("href");
                    }
                    //String titleCh = TranslateUtil.en2chs(title.html());

                    DblpConference dblpConference = new DblpConference(key, title.html(), "", conferenceUrl, authorList);
                    conferences.add(dblpConference);

                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return conferences;
    }

    /**
     * 爬取论文
     * @param conference_id 会议ID
     * @param url 会议URL
     */
    public static List<Paper> paperSpider(String conference_id, String url) {
        //String url = "http://dblp.uni-trier.de/db/conf/recsys/recsys2016.html.";
        List<Paper> paperList = new ArrayList<>();
        String result = null;
        try {
            result = HttpClientUtil.getHTML(url);
            Document document = Jsoup.parse(result);

            Elements papers = document.select("li.entry.inproceedings");
            for (Element paper : papers) {
                Element paperA = paper.select("li.drop-down").first()
                        .select("div.head").first().select("a").first();
                String paper_url = "";
                if (paperA != null) {
                    paper_url = paperA.attr("href");
                }
                Element paperData = paper.select("div.data").first();
                String paper_name = paperData.select("span.title").first().html();
                //String paper_namech = TranslateUtil.en2chs(paper_name);
                String paperPage = "";
                Element pagination = paperData.select("span[itemprop=pagination]").first();
                if (pagination != null) {
                    paperPage = pagination.html();
                }

                String paper_namech = TranslateUtilByBaidu.en2chs(paper_name);

                paperList.add(new Paper(conference_id, paper_name, paper_namech, paperPage, paper_url));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paperList;
    }

    @Test
    public void main() {
        //String url = "http://dblp.uni-trier.de/db/conf/recsys/";
        Map<String, String> urls = new HashMap<>();
        urls.put("resys", "http://dblp.uni-trier.de/db/conf/recsys/");
        urls.put("SIGMOD", "http://dblp.uni-trier.de/db/conf/sigmod/");
        List<DblpConference> conferences = conferenceSpider(urls);
        //
        //Iterator<DblpConference> conferenceIterator = conferences.iterator();
        //while (conferenceIterator.hasNext()) {
        //    DblpConference dblpConference = conferenceIterator.next();
        //    System.out.println(dblpConference);
        //    dblpConferenceService.insertConference(dblpConference);
        //
        //    List<Paper> papers = paperSpider(dblpConference.getConference_id(), dblpConference.getContent_url());
        //
        //    Iterator<Paper> paperIterator = papers.iterator();
        //    while (paperIterator.hasNext()) {
        //        Paper paper = paperIterator.next();
        //        System.out.println(paper);
        //        paperService.insertPaper(paper);
        //    }
        //
        //}
    }

}
