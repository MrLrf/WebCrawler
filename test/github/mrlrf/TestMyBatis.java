package github.mrlrf;

import github.mrlrf.Services.interfaces.ZhihuQuestionService;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/6/2 16:34
 */
public class TestMyBatis {
    @Autowired
    private ZhihuQuestionService zhihuQuestionService;

    private static Logger logger = Logger.getLogger(TestMyBatis.class);
    private ApplicationContext ac = null;

    @Before
    public void before() {
        ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        zhihuQuestionService = (ZhihuQuestionService) ac.getBean("zhihuQuestionService");
    }

    @Test
    public void test1() {
        //List<ZhihuQuestion> result = zhihuQuestionService.loadZhQuestion();
        // System.out.println(user.getUserName());
        // logger.info("值："+user.getUserName());
        //logger.info(result);
    }
}
