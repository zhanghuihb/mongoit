package com.mongoit.mongo.service;

import com.mongoit.common.base.Result;
import com.mongoit.common.response.index.GoodsResponse;
import com.mongoit.common.response.index.IndexCarouselResponse;
import com.mongoit.common.response.index.IndexHotNewsResponse;

import java.util.List;

/**
 * @author tainy
 * @date 2020/11/11 0011 10:46
 */
public interface IndexService {

    /**
     * 获取首页轮播图
     *
     * @author  tainy
     * @date 2020/11/11 0011
     * @time 12:53
     */
    Result<List<IndexCarouselResponse>> getIndexCarousel();

    /**
     * 查询首页最新发布
     *
     * @author  tainy
     * @date 2020/11/11 0011
     * @time 17:21
     */
    Result<List<GoodsResponse>> getIndexLatestPublish();

    /**
     * 查询首页热门新闻
     *
     * @author  tainy
     * @date 2020/11/12 0012
     * @time 14:05
     */
    Result<List<IndexHotNewsResponse>> getIndexHotNews();
}
