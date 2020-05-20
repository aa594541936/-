package com.sxt.sys.service;

import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.LogInfoVo;

public interface LogInfoService {

    DataGridView queryAllLogInfo(LogInfoVo logInfoVo);

    void addLogInfo(LogInfoVo logInfoVo);

    void deleteLogInfo(Integer logInfoid);
}
