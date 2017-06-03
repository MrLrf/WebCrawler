package github.mrlrf.mybatis.mapper;

import github.mrlrf.model.ZhihuQuestion;
import java.util.List;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/27 11:31
 */
public interface ZhihuQuestionMapper {
    List<ZhihuQuestion> getQuestion() throws Exception;

    int insertQuestion(ZhihuQuestion zhihuquestion) throws Exception;
}
