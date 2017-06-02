package github.mrlrf.dao.interfaces;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/6/2 10:00
 */
@Repository
public interface ZhihuQuestionDao {
    List<Map<String, String>> loadZhQuestion();
}
