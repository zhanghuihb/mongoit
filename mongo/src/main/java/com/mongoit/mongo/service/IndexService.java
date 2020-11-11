package com.mongoit.mongo.service;

import com.mongoit.common.base.Result;
import com.mongoit.common.response.index.IndexCarouselResponse;

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
}
