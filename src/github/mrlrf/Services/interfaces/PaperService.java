package github.mrlrf.Services.interfaces;

import github.mrlrf.model.Paper;

import java.util.List;
import java.util.Map;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/6/3 21:43
 */
public interface PaperService {

    List<Map<String, String>> getPaperPage(int page, int rows);

    int insertPaper(Paper paper);
}
