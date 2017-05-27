package com.lirf.mybatis.mapper;

import com.lirf.mybatis.model.ZhihuAnswer;

import java.util.List;

/**
 * 类的描述
 *
 * @Author lirf
 * @Date 2017/5/27 13:39
 */
public interface ZhihuAnswerMapper {
    List<ZhihuAnswer> getAnswer () throws Exception;

    int insertAnswer (ZhihuAnswer zhihuAnswer) throws Exception;
}
