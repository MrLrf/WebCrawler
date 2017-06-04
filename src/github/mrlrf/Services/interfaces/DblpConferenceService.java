package github.mrlrf.Services.interfaces;

import github.mrlrf.model.DblpConference;

import java.util.List;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/6/3 21:43
 */
public interface DblpConferenceService {

    List<DblpConference> getConference();

    int insertConference(DblpConference dblpConference);
}
