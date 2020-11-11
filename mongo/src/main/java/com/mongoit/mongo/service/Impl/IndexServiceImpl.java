package com.mongoit.mongo.service.Impl;

import com.mongoit.common.base.Constant;
import com.mongoit.common.base.Result;
import com.mongoit.common.response.index.IndexCarouselResponse;
import com.mongoit.mongo.service.IndexService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tainy
 * @date 2020/11/11 0011 10:46
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Override
    public Result<List<IndexCarouselResponse>> getIndexCarousel() {
        List<IndexCarouselResponse> response = new ArrayList<>();
        response.add(IndexCarouselResponse.builder().id(1l).goodsId(1l).sort(1).isShow(Constant.YES).goodsImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605080798673&di=a7ae21607cdbd0bb6326935ec0e34cb4&imgtype=0&src=http%3A%2F%2Fwww.szthks.com%2Flocalimg%2F687474703a2f2f6777322e616c6963646e2e636f6d2f62616f2f75706c6f616465642f69322f5431644338785866706d5858636e327366635f3132353933362e6a7067.jpg").build());
        response.add(IndexCarouselResponse.builder().id(2l).goodsId(2l).sort(2).isShow(Constant.YES).goodsImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605081252041&di=ba11c54dc1016baf91554073fe36898c&imgtype=0&src=http%3A%2F%2Fimg1.imgtn.bdimg.com%2Fit%2Fu%3D1782891596%2C113576256%26fm%3D214%26gp%3D0.jpg").build());
        response.add(IndexCarouselResponse.builder().id(3l).goodsId(3l).sort(3).isShow(Constant.YES).goodsImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605080798663&di=c61ee72bb21cb0a758751978837e179e&imgtype=0&src=http%3A%2F%2Fb.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F7aec54e736d12f2e78d02dfb44c2d56284356850.jpg").build());
        return Result.success(response);
    }
}
