package com.sxt.sys.service;

import com.sxt.sys.domain.News;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.vo.NewsVo;

public interface NewsService {

    DataGridView queryAllNews(NewsVo newsVo);

    void addNews(NewsVo newsVo);

    void updateNews(NewsVo newsVo);

    void deleteNews(Integer newsid);

    News queryNewsById(Integer id);

}
