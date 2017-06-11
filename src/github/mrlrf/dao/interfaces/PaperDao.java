package github.mrlrf.dao.interfaces;

import github.mrlrf.model.DblpConference;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/27 16:16
 */
@Repository
public interface DblpConferenceDao {

    List<DblpConference> getConference();

    int insertConference(DblpConference dblpConference);
}
