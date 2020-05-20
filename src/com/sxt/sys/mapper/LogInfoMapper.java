package com.sxt.sys.mapper;

import com.sxt.sys.domain.LogInfo;

import java.util.List;

public interface LogInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogInfo record);

    int insertSelective(LogInfo record);

    LogInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogInfo record);

    int updateByPrimaryKey(LogInfo record);

    /**
     * 查询日志
     * @param logInfo
     * @return
     */
    List<LogInfo> queryAllLogInfo(LogInfo logInfo);

    void deleteLogInfoById(Integer[] ids);

}