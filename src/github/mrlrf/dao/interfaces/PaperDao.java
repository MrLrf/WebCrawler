package github.mrlrf.dao.interfaces;

import github.mrlrf.model.Paper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/27 16:16
 */
@Repository
public interface PaperDao {

    List<Map<String, String>> getPaper();

    int insertPaper(Paper paper);
}
