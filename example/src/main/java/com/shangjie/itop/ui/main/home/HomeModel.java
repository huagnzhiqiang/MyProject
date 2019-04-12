package com.shangjie.itop.ui.main.home;

import com.shangjie.itop.data.entity.TestNews;
import com.shangjie.itop.data.repository.RetrofitUtils;
import com.shangjie.baselibs.mvp.BaseModel;
import com.shangjie.baselibs.net.BaseHttpResult;

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
