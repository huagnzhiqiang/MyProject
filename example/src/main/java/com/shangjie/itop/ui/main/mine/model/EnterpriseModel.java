package com.shangjie.itop.ui.main.mine.model;

import com.shangjie.baselibs.mvp.BaseModel;
import com.shangjie.baselibs.net.BaseHttpResult;
import com.shangjie.itop.data.repository.RetrofitUtils;
import com.shangjie.itop.ui.main.mine.contract.EnterpriseContract;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/11/7  16:22
 * @desc 企业信息修改页面model
 */
public class EnterpriseModel extends BaseModel implements EnterpriseContract.Model{

    /**
     * 上传图片
     *
     * @param map 请求参数
     */
    @Override
    public Observable<BaseHttpResult<String>> requistUpImager(Map<String, String> map) {
        return RetrofitUtils.getHttpService().requistUpImager(map);
    }
}
