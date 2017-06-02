package github.mrlrf.mybatis.mapper;

import github.mrlrf.model.DblpConference;

import java.util.List;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/27 16:16
 */
public interface DblpConferenceMapper {

    List<DblpConference> getConference() throws Exception;

    int insertConference(DblpConference dblpConference) throws Exception;
}
