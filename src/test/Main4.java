package test;

import crawlercore.Spider;
import crawlercore.Zhihu;

import java.util.ArrayList;

/**抓取知乎多条问题和对应问题的链接的测试类
 * Created by codingBoy on 16/10/20.
 */
public class Main4 {
    public static void main(String[] args)
    {
        String url="https://www.zhihu.com/explore/recommendations";
        String source= Spider.sendGet(url);

        System.out.println(source);

        ArrayList<Zhihu> results;

        results=Spider.regexString(source);


        System.out.println(results);


    }
}
