package github.mrlrf.Services.impls;

import github.mrlrf.Services.interfaces.ZhihuQuestionService;
import github.mrlrf.dao.interfaces.ZhihuQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/6/2 10:08
 */
@Service
public class ZhihuQuestionServiceImpl implements ZhihuQuestionService {
    @Autowired
    private ZhihuQuestionDao zhihuQuestionDao;

    @Override
    public List<Map<String, String>> loadZhQuestion() {
        return zhihuQuestionDao.loadZhQuestion();
    }
}
