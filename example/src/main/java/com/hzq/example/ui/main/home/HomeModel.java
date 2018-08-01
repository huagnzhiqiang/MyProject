package com.hzq.example.ui.main.home;

import com.hzq.example.data.entity.TestNews;
import com.hzq.example.data.repository.RetrofitUtils;
import com.hzq.baselibs.mvp.BaseModel;
import com.hzq.baselibs.net.BaseHttpResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/6/13 15:35
 * @desc
 */
public class HomeModel extends BaseModel implements HomeContract.Model {
    @Override
    public Observable<BaseHttpResult<List<TestNews>>> getGankData() {
        return RetrofitUtils.getHttpService().getGankData();
    }
}
