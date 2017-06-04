package github.mrlrf.Services.impls;

import github.mrlrf.Services.interfaces.DblpConferenceService;
import github.mrlrf.dao.interfaces.DblpConferenceDao;
import github.mrlrf.model.DblpConference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/6/3 21:44
 */
@Service
public class DblpConferenceServiceImpl implements DblpConferenceService {
    @Autowired
    private DblpConferenceDao dblpConferenceDao;

    @Override
    public List<DblpConference> getConference() {
        return dblpConferenceDao.getConference();
    }

    @Override
    public int insertConference(DblpConference dblpConference) {
        return dblpConferenceDao.insertConference(dblpConference);
    }
}
