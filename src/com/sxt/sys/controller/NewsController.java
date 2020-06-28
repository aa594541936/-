package com.sxt.sys.controller;

import com.sxt.sys.domain.News;
import com.sxt.sys.domain.User;
import com.sxt.sys.service.NewsService;
import com.sxt.sys.utils.DataGridView;
import com.sxt.sys.utils.ResultObj;
import com.sxt.sys.utils.WebUtils;
import com.sxt.sys.vo.NewsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @RequestMapping("/loadAllNews")
    public DataGridView loadAllNews(NewsVo newsVo) {
        return newsService.queryAllNews(newsVo);
    }

    @RequestMapping("/addNews")
    public ResultObj addNews(NewsVo newsVo) {
        try {
            newsVo.setCreatetime(new Date());
            User user = (User) WebUtils.getHttpSession().getAttribute("user");
            newsVo.setOpername(user.getRealname());
            this.newsService.addNews(newsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("/updateNews")
    public ResultObj updateNews(NewsVo newsVo) {
        try {
            this.newsService.updateNews(newsVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    @RequestMapping("/deleteNews")
    public ResultObj deleteNews(NewsVo newsVo) {
        try {
            this.newsService.deleteNews(newsVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    @RequestMapping("/loadNewsById")
    public News loadNewsById(Integer id) {
        return this.newsService.queryNewsById(id);
    }

}
