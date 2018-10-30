package com.hzq.example.ui.main.market.recoment;


import com.hzq.baselibs.mvp.BaseModel;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.example.data.entity.RecomentEntity;
import com.hzq.example.data.repository.RetrofitUtils;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/10/27  16:06
 * @desc
 */
public class RecomenModel extends BaseModel implements RecomentContract.Model {

    /**
     * 自营销推荐
     *
     * @param map 请求参数
     */
    @Override
    public Observable<BaseHttpResult<RecomentEntity>> requestRecomentData(Map<String, String> map) {
        return RetrofitUtils.getHttpService().requestRecomentData(map);
    }

}
