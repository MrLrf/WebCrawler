package github.mrlrf.Services.impls;

import github.mrlrf.Services.interfaces.ZhihuQuestionService;
import github.mrlrf.dao.interfaces.ZhihuQuestionDao;
import github.mrlrf.model.ZhihuQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<ZhihuQuestion> loadZhQuestion() {
        //return zhihuQuestionDao.loadZhQuestion();
        return zhihuQuestionDao.loadZhQuestion();
    }
    //
    //@Override
    //public int insertZhQuestion(ZhihuQuestion zhihuQuestion) {
    //    return zhihuQuestionDao.insertZhQuestion(zhihuQuestion);
    //}
}
