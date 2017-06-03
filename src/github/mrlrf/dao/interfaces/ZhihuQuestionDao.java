package github.mrlrf.dao.interfaces;

import github.mrlrf.model.ZhihuQuestion;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/6/2 10:00
 */
@Repository
public interface ZhihuQuestionDao {
    List<ZhihuQuestion> loadZhQuestion();

    //int insertZhQuestion(ZhihuQuestion zhihuQuestion);
}
