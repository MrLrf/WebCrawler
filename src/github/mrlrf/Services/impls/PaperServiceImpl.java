package github.mrlrf.Services.impls;

import github.mrlrf.Services.interfaces.PaperService;
import github.mrlrf.dao.interfaces.PaperDao;
import github.mrlrf.model.Paper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/6/11 15:53
 */
@Service
public class PaperServiceImpl implements PaperService {
    @Autowired
    private PaperDao paperDao;

    @Override
    public List<Map<String, String>> getPaper() {
        return paperDao.getPaper();
    }

    @Override
    public int insertPaper(Paper paper) {
        return paperDao.insertPaper(paper);
    }


}
