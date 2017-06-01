package com.lirf.mybatis.mapper;

import com.lirf.model.Paper;

import java.util.List;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/27 16:16
 */
public interface DblpPaperMapper {

    List<Paper> getPapers() throws Exception;

    int insertPaper(Paper dblpPaper) throws Exception;
}
