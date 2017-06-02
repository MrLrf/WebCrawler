package github.mrlrf.dao.interfaces;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ZhihuQuestionMapper {

    List<Map<String, String>> loadZhihuQuestion();
}