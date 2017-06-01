package com.lirf.test;

import com.lirf.crawlercore.Spider;
import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/28 11:30
 */
public class PaperSource {
    @Test
    public void getPapers () {
        //String source= Spider.sendGet("http://dblp.uni-trier.de/db/conf/recsys/recsys2012.html");
        String source= Spider.sendGet("http://dblp.uni-trier.de/db/conf/sigmod/sigmod2017.html");
        System.out.println(source);

        Pattern pattern = Pattern.compile("<a href=\"(http://[^><\"]+?)\" itemprop=\"url\"><img.+?<span class=\"title\" itemprop=\"name\">(.+?)</span>");
        Matcher matcher = pattern.matcher(source);

        //Pattern namePattern = Pattern.compile("<span class=\"title\" itemprop=\"name\">(.+?)</span>");
        //Matcher nameMatcher = namePattern.matcher(source);
        //Pattern pagePattern = Pattern.compile("<span itemprop=\"pagination\">(\\d+-\\d+)</span>");
        //Matcher pageMatcher = pagePattern.matcher(source);
        ////Pattern urlPattern = Pattern.compile("<a href=\"(http://[^><\"]+?)\" itemprop=\"url\"><img(.+?)src=\"http://dblp2\\.uni-trier\\.de/img/paper\\.dark\\.16x16\\.png\" class=\"icon\"");
        //Pattern urlPattern = Pattern.compile("<a href=\"(http://[^><\"]+?)\" itemprop=\"url\"><img");
        //Matcher urlMatcher = urlPattern.matcher(source);
        //
        //while (nameMatcher.find()) {
        //    String name = nameMatcher.group(1);
        //    String page = "";
        //    String url = "";
        //    if (pageMatcher.find()) page = pageMatcher.group(1);
        //    if (urlMatcher.find()) {
        //        url = urlMatcher.group(1);
        //    }
        //    System.out.println(name+url);
        //}
        while (matcher.find()) {
            System.out.println(matcher.group(1)+"\n"+matcher.group(2));
        }
    }
}
