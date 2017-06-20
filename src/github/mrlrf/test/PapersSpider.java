package github.mrlrf.test;

import github.mrlrf.model.DblpConference;
import github.mrlrf.utils.HttpClientUtil;
import github.mrlrf.utils.TranslateUtilByGoogle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * dblp会议爬取
 *
 * @Author lirf
 * @Date 2017/6/10 19:59
 */
public class PapersSpider {
    public static void main(String[] args) throws Exception {
        String url = "http://dblp.uni-trier.de/db/conf/recsys/";
        String result = null;
        try {
            result = HttpClientUtil.getHTML(url);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Document document = Jsoup.parse(result);
        Elements datas = document.select("div.data");

        for (Element data : datas) {
            List<DblpConference> dblpConferences = new ArrayList<>();
            List<String> authorList = new ArrayList<>();
            Elements authors = data.select("span[itemprop=author]");
            for (Element author : authors) {
                authorList.add(author.select("span[itemprop=name]").first().html());
            }
            Element date = data.select("span[itemprop=datePublished]").first();
            Element title = data.select("span.title").first();
            Element href = data.select("a:containsOwn([contents])").first();

            String titlech = TranslateUtilByGoogle.en2chs(title.text());
            String conferenceurl = href.attr("href");
            dblpConferences.add(new DblpConference("recsys", title.html(), titlech, conferenceurl, authorList));

            Iterator<DblpConference> iterator = dblpConferences.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        }
    }
}
