package com.shangjie.itop.ui.main.market.recoment;


import com.shangjie.baselibs.mvp.BaseModel;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.itop.data.entity.RecomentEntity;
import com.shangjie.itop.data.repository.RetrofitUtils;

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
