package crawlercore;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/26 20:02
 */
public class Spider {
    public static String sendGet (String url) {
        BufferedReader reader = null;
        String result = "";

        try {
            URL realUrl = new URL(url);
            URLConnection urlConnection = realUrl.openConnection();

            reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"utf-8"));

            String line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Zhihu> regexString (String result) {
        ArrayList<Zhihu> lists = new ArrayList<Zhihu>();

        //定义正则表达式,括号中是爬取得内容
        Pattern urlPattern = Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
        Matcher urlMatcher = urlPattern.matcher(result);

        while(urlMatcher.find()) {
            Zhihu zhihu = new Zhihu("https://www.zhihu.com" + urlMatcher.group(1));
            lists.add(zhihu);
        }

        return lists;
    }

    @Test
    public void getResult () {
        System.out.println(sendGet("https://www.zhihu.com/explore/recommendations"));
    }

}
