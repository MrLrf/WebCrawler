package com.lirf.test;

import com.lirf.crawlercore.Spider;
import com.lirf.model.DblpConference;
import com.lirf.mybatis.mapper.DblpConferenceMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/27 14:11
 */
public class DBLPPapersSpider {
    public static boolean conferenceSpider (List<String> urls) throws Exception {
        //mybatis的配置文件
        String resource = "dbconfig.xml";
        InputStream is = MybatisTest.class.getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
        SqlSession session = sessionFactory.openSession();
        DblpConferenceMapper dblpConferenceMapper = session.getMapper(DblpConferenceMapper.class);
        for (String url : urls) {
            String source= Spider.sendGet(url);
            List<DblpConference> conferences = Spider.regexConference("sigmod", source);

            for (DblpConference dblpConference : conferences) {
                int result = dblpConferenceMapper.insertConference(dblpConference);
                if (result < 1) {
                    session.close();
                    return false;
                }
            }
        }
        session.commit();
        session.close();
        return true;
    }

    public static void main(String[] args) throws Exception {
        //TODO:应该传一个map对应会议名和url
        List<String> urls = new ArrayList<String>();
        urls.add("http://dblp.uni-trier.de/db/conf/recsys/");
        urls.add("");
    }
}
