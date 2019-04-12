package com.shangjie.itop.ui.main;


import com.shangjie.itop.data.entity.TestNews;
import com.shangjie.itop.data.repository.RetrofitUtils;
import com.shangjie.baselibs.mvp.BaseModel;
import com.shangjie.baselibs.net.BaseHttpResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/6/13 13:58
 * @desc 对数据层进行操作（比如，是否需要缓存，调用不同 apiservce，外界无需知道内部怎么实现）
 */
public class MainModel extends BaseModel implements MainContract.Model{

    @Override
    public Observable<BaseHttpResult<List<TestNews>>> getGankData() {
        return RetrofitUtils.getHttpService().getGankData();
    }
}
