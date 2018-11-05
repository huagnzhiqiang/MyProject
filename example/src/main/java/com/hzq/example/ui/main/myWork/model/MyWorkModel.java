package com.hzq.example.ui.main.myWork.model;

import com.hzq.baselibs.mvp.BaseModel;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.example.data.entity.MyProductEntity;
import com.hzq.example.data.repository.RetrofitUtils;
import com.hzq.example.ui.main.myWork.contract.MyWorkContract;

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
