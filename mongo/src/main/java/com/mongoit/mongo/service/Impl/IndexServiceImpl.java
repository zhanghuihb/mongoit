package com.mongoit.mongo.service.Impl;

import com.mongoit.common.base.Constant;
import com.mongoit.common.base.Result;
import com.mongoit.common.response.index.GoodsResponse;
import com.mongoit.common.response.index.IndexCarouselResponse;
import com.mongoit.common.response.index.IndexHotNewsResponse;
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
        response.add(IndexCarouselResponse.builder().id(3l).goodsId(3l).sort(3).isShow(Constant.YES).goodsImage("https://img1.jinghanit.com/group1/M00/0C/09/ag8oll-rg0-ARJ10AAU-bNR5Eys908.png").build());
        return Result.success(response);
    }

    @Override
    public Result<List<GoodsResponse>> getIndexLatestPublish() {
        List<GoodsResponse> response = new ArrayList<>();
        response.add(GoodsResponse.builder().goodsId(8l).goodsName("以善为本，以德为用").wantToBuyCount(55).publishTime("2020-09-18 12:43:26").goodsImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605096837180&di=13395427c4f74070f385df1c14640f61&imgtype=0&src=http%3A%2F%2Fwww.szthks.com%2Flocalimg%2F687474703a2f2f6777312e616c6963646e2e636f6d2f62616f2f75706c6f616465642f69362f54314763393558686c615858614a71586a615f3132303030312e6a7067.jpg").build());
        response.add(GoodsResponse.builder().goodsId(7l).goodsName("直升机").wantToBuyCount(163).publishTime("2020-09-18 23:12:36").goodsImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605096736970&di=7ef5963369030a29b87065012d1de425&imgtype=0&src=http%3A%2F%2Fwww.szthks.com%2Flocalimg%2F687474703a2f2f6777332e616c6963646e2e636f6d2f62616f2f75706c6f616465642f69332f54316c413569586f747758586335787267545f3031303732372e6a7067.jpg").build());
        response.add(GoodsResponse.builder().goodsId(6l).goodsName("戴尔键盘").wantToBuyCount(34).publishTime("2020-09-23 18:32:46").goodsImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605080798680&di=57f47a5d0b6a06b3aa558e5a3e25698f&imgtype=0&src=http%3A%2F%2Fwww.szthks.com%2Flocalimg%2F687474703a2f2f6777312e616c6963646e2e636f6d2f62616f2f75706c6f616465642f69362f54313833564d5858586c585863554e6a76615f3132313630312e6a7067.jpg").build());
        response.add(GoodsResponse.builder().goodsId(5l).goodsName("山地自行车").wantToBuyCount(78).publishTime("2020-10-16 09:26:51").goodsImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605080798683&di=514c21ef11d69521b053abbcdf964e59&imgtype=0&src=http%3A%2F%2Fwww.szthks.com%2Flocalimg%2F687474703a2f2f6777312e616c6963646e2e636f6d2f62616f2f75706c6f616465642f69382f543174704e68466f706558585858585858585f2121302d6974656d5f7069632e6a7067.jpg").build());
        response.add(GoodsResponse.builder().goodsId(4l).goodsName("海星抱枕").wantToBuyCount(298).publishTime("2020-11-11 11:44:38").goodsImage("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1605080798662&di=f4e5a56c2c08bcd980b4d25b7cc69b22&imgtype=0&src=http%3A%2F%2Fwww.szthks.com%2Flocalimg%2F687474703a2f2f6777312e616c6963646e2e636f6d2f62616f2f75706c6f616465642f69362f543173497539586c4e6458586367736c62615f3039313831302e6a7067.jpg").build());
        return Result.success(response);
    }

    @Override
    public Result<List<IndexHotNewsResponse>> getIndexHotNews() {
        List<IndexHotNewsResponse> response = new ArrayList<>();
        response.add(IndexHotNewsResponse.builder().newsId(1l).newsName("特朗普出售其私人直升机").watchUserCount(55).newsPoster("http://n.sinaimg.cn/news/transform/195/w105h90/20201112/9dde-kcunqze0990722.jpg").newsUrl("https://edge.ivideo.sina.com.cn/36134595201.mp4?KID=sina,viask&Expires=1605283200&ssig=wkgWyoKjOo&reqid=").build());
        response.add(IndexHotNewsResponse.builder().newsId(2l).newsName("湖人问鼎NBA总冠军 詹姆斯荣膺FMVP").watchUserCount(163).newsPoster("http://k.sinaimg.cn/n/front20201013ac/400/w1280h720/20201013/6bb7-kakmcxe4189333.jpg/w220h125z1l0t0q100c51.jpg").newsUrl("http://edge.ivideo.sina.com.cn/35688450403.mp4?KID=sina,viask&Expires=1605283200&ssig=iZ7EX5p9Jc&reqid=").build());
            response.add(IndexHotNewsResponse.builder().newsId(3l).newsName("于谦教育方式套路满满 巧妙正向引导孩子获赞").watchUserCount(34).newsPoster("http://p.ivideo.sina.com.cn/video/360/208/745/360208745_432_243.jpg").newsUrl("https://edge.ivideo.sina.com.cn/36020874501.mp4?KID=sina,viask&Expires=1605283200&ssig=HqpM0mTL1z&reqid=").build());
        response.add(IndexHotNewsResponse.builder().newsId(4l).newsName("马恒昌：杰出的中国工运活动家").watchUserCount(78).newsPoster("http://n.sinaimg.cn/sinakd20201112ac/766/w1002h564/20201112/9cb3-kcunqze2131853.jpg").newsUrl("http://edge.ivideo.sina.com.cn/36140881701.mp4?KID=sina,viask&Expires=1605283200&ssig=7cPK5TjxPw&reqid=").build());
        return Result.success(response);
    }
}
