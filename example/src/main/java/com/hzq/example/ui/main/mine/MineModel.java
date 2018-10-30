package com.hzq.example.ui.main.mine;

import com.hzq.example.data.entity.LoginEntity;
import com.hzq.example.data.entity.MineEntity;
import com.hzq.example.data.repository.RetrofitUtils;
import com.hzq.baselibs.mvp.BaseModel;
import com.hzq.baselibs.net.BaseHttpResult;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time  2018/6/13 15:35
 * @desc
 */
public class MineModel extends BaseModel implements MineContract.Model {


    @Override
    public Observable<BaseHttpResult<MineEntity>> getMineData() {
        return RetrofitUtils.getHttpService().getMineData();
    }

    @Override
    public Observable<BaseHttpResult<LoginEntity>> getLoginData(Map<String,String> map) {
        return RetrofitUtils.getHttpService().requestLoginData(map);
    }

}
