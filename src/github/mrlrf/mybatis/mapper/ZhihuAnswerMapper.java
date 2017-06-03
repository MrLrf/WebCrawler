package github.mrlrf.mybatis.mapper;

import github.mrlrf.model.ZhihuAnswer;
import java.util.List;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/27 13:39
 */
public interface ZhihuAnswerMapper {
    List<ZhihuAnswer> getAnswer() throws Exception;

    int insertAnswer(ZhihuAnswer zhihuAnswer) throws Exception;
}
