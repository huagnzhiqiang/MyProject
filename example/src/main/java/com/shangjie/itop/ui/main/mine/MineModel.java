package com.shangjie.itop.ui.main.mine;

import com.shangjie.itop.data.entity.LoginEntity;
import com.shangjie.itop.data.entity.MineEntity;
import com.shangjie.itop.data.repository.RetrofitUtils;
import com.shangjie.baselibs.mvp.BaseModel;
import com.shangjie.baselibs.net.BaseHttpResult;

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
