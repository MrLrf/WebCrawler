package github.mrlrf.Services.interfaces;

import github.mrlrf.model.ZhihuQuestion;

import java.util.List;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/6/2 10:07
 */
public interface ZhihuQuestionService {
    List<ZhihuQuestion> loadZhQuestion ();

    //int insertZhQuestion (ZhihuQuestion zhihuQuestion);
}
