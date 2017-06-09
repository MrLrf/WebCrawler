package github.mrlrf.crawlercore;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.cookie.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultProxyRoutePlanner;
import org.apache.http.impl.cookie.BestMatchSpecFactory;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.BrowserCompatSpecFactory;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/6/5 17:37
 */
public class HTML {
    /**默认方法 */
    public String[] getHTML(String url) throws ClientProtocolException, IOException {
        String[] html = new String[2];
        html[1] = "null";
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)   //socket超时
                .setConnectTimeout(5000)   //connect超时
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
        HttpGet httpGet = new HttpGet(url);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            html[0] = String.valueOf(response.getStatusLine().getStatusCode());
            html[1] = EntityUtils.toString(response.getEntity(), "utf-8");
            //System.out.println(html);
        } catch (IOException e) {
            System.out.println("----------Connection timeout--------");
        }
        return html;
    }

    /**cookie方法的getHTMl() 设置cookie策略,防止cookie rejected问题,拒绝写入cookie     --重载,3参数:url, hostName, port */
    public String getHTML(String url, String hostName, int port) throws URISyntaxException, ClientProtocolException, IOException {
        //采用用户自定义的cookie策略
        HttpHost proxy = new HttpHost(hostName, port);
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        CookieSpecProvider cookieSpecProvider = new CookieSpecProvider() {
            public CookieSpec create(HttpContext context) {
                return new BrowserCompatSpec() {
                    @Override
                    public void validate(Cookie cookie, CookieOrigin origin) throws MalformedCookieException {
                        //Oh, I am easy...
                    }
                };
            }
        };
        Registry<CookieSpecProvider> r = RegistryBuilder
                .<CookieSpecProvider> create()
                .register(CookieSpecs.BEST_MATCH, new BestMatchSpecFactory())
                .register(CookieSpecs.BROWSER_COMPATIBILITY, new BrowserCompatSpecFactory())
                .register("easy", cookieSpecProvider)
                .build();
        RequestConfig requestConfig = RequestConfig.custom()
                .setCookieSpec("easy")
                .setSocketTimeout(5000) //socket超时
                .setConnectTimeout(5000) //connect超时
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultCookieSpecRegistry(r)
                .setRoutePlanner(routePlanner)
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        String html = "null"; //用于验证是否正常取到html
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            html = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (IOException e) {
            System.out.println("----Connection timeout----");
        }
        return html;
    }

    /**proxy代理IP方法 */
    public String getHTMLbyProxy(String targetUrl, String hostName, int port) throws ClientProtocolException, IOException {
        HttpHost proxy = new HttpHost(hostName, port);
        String html = "null";
        DefaultProxyRoutePlanner routePlanner = new DefaultProxyRoutePlanner(proxy);
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000) //socket超时
                .setConnectTimeout(5000) //connect超时
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setRoutePlanner(routePlanner)
                .setDefaultRequestConfig(requestConfig)
                .build();
        HttpGet httpGet = new HttpGet(targetUrl);
        try {
            CloseableHttpResponse response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if(statusCode == HttpStatus.SC_OK) { //状态码200: OK
                html = EntityUtils.toString(response.getEntity(), "gb2312");
            }
            response.close();
            //System.out.println(html); //打印返回的html
        } catch (IOException e) {
            System.out.println("----Connection timeout----");
        }
        return html;
    }

    private boolean isExistHTML(String html) throws InterruptedException {
        boolean isExist = false;
        Pattern pNoResult = Pattern.compile("\\\\u6ca1\\\\u6709\\\\u627e\\\\u5230\\\\u76f8"
                + "\\\\u5173\\\\u7684\\\\u5fae\\\\u535a\\\\u5462\\\\uff0c\\\\u6362\\\\u4e2a"
                + "\\\\u5173\\\\u952e\\\\u8bcd\\\\u8bd5\\\\u5427\\\\uff01"); //没有找到相关的微博呢，换个关键词试试吧！（html页面上的信息）
        Matcher mNoResult = pNoResult.matcher(html);
        if(!mNoResult.find()) {
            isExist = true;
        }
        return isExist;
    }

    /**把所有html写到本地txt文件存储 */
    public static void writeHTML2txt(String html, int num) throws IOException {
        String savePath = "d:/weibo/weibohtml/" + num + ".txt";
        File f = new File(savePath);
        FileWriter fw = new FileWriter(f);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(html);
        bw.close();
    }


    @Test
    public void header() {
        HttpClient httpClient = new DefaultHttpClient();
        try {
            HttpGet httpget = new HttpGet("http://www.baidu.com");
            httpget.setHeader("Accept", "text/html, */*; q=0.01");
            httpget.setHeader("Accept-Encoding", "gzip, deflate,sdch");
            httpget.setHeader("Accept-Language", "zh-CN,zh;q=0.8");
            httpget.setHeader("Connection", "keep-alive");
            httpget.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.124 Safari/537.36)");

            HttpResponse response = httpClient.execute(httpget);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine()); //状态码
            if(entity != null) {
                System.out.println(entity.getContentLength());
                System.out.println(entity.getContent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String parse(String html) {
        String s = "";
        Document doc = Jsoup.parse(html);
        Elements userNames = doc.select("dt[class].face > a");
        Elements userids = doc.select("span > a[action-data]");
        Elements dates = doc.select("a[date]");
        Elements tweetids = doc.select("dl[mid]");
        Elements tweets = doc.select("p > em");
        Elements forwardNums = doc.select("a:contains(转发)");
        Elements commentNums = doc.select("a:contains(评论)");
        for(Element userName : userNames) {
            String attr = userName.attr("title");
            s += "<userName> " + attr + " </userName>";
        }
        for(Element userid : userids) {
            String attr = userid.attr("action-data");
            attr = attr.substring(attr.indexOf("uid="));
            Pattern p = Pattern.compile("[0-9]+");
            Matcher m = p.matcher(attr);
            if(m.find()) {
                attr = m.group();
            }
            s += "<userid> " + attr + " </userid>";
        }
        for(Element date : dates) {
            String attr = date.text();
            s += "<date> " + attr + " </date>";
        }
        for(Element tweetid : tweetids) {
            String attr = tweetid.attr("mid");
            s += "<tweetid> " + attr + " </tweetid>";
        }
        for(Element tweet : tweets) {
            String attr = tweet.text();
            s += "<tweetSentence> " + attr + " </tweetSentence>";
        }
        for(Element forwardNum : forwardNums) {
            String attr = forwardNum.text();
            if(attr.equals("转发")) {
                attr = "0";
            }
            else {
                if(!attr.contains("转发(")) {
                    attr = "0";
                }
                else {
                    attr = attr.substring(attr.indexOf("转发(")+3, attr.indexOf(")"));
                }
            }
            System.out.println(attr);
            s += "<forwardNum> " + attr + " </forwardNum>";
        }
        for(Element commentNum : commentNums) {
            String attr = commentNum.text();
            if(attr.equals("评论")) {
                attr = "0";
            }
            else {
                if(!attr.contains("评论(")) {
                    attr = "0";
                }
                else {
                    attr = attr.substring(attr.indexOf("评论(")+3, attr.indexOf(""));
                }
            }
            System.out.println(attr);
            s += "<commentNum> " + attr + " </commentNum>";
        }
        //System.out.println(s);
        return s;
    }


    @Test
    public void sendGet() throws IOException {
        String url = "http://s.weibo.com/weibo/苹果手机&nodup=1&page=50";
        //String url = "http://t.163.com/tag/苹果手机";
        String[] htmls = getHTML(url);
        writeHTML2txt(htmls[1], 1);

        System.out.println(parse(htmls[1]));

        //System.out.println(htmls[0]);
        //System.out.println(htmls[1]);
    }
}
