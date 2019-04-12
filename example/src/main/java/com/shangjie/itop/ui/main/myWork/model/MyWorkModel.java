package com.shangjie.itop.ui.main.myWork.model;

import com.shangjie.baselibs.mvp.BaseModel;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.itop.data.entity.MyProductEntity;
import com.shangjie.itop.data.repository.RetrofitUtils;
import com.shangjie.itop.ui.main.myWork.contract.MyWorkContract;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/11/2  11:35
 * @desc
 */
public class MyWorkModel extends BaseModel implements MyWorkContract.Model {
    /**
     * 我的作品
     *
     * @param map 请求参数
     */
    @Override
    public Observable<BaseHttpResult<MyProductEntity>> requestMyWorktData(Map<String, String> map) {
        return RetrofitUtils.getHttpService().requestMyWorktData(map);
    }
}
