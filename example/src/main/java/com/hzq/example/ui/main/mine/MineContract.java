package com.hzq.example.ui.main.mine;

import com.hzq.example.data.entity.LoginEntity;
import com.hzq.example.data.entity.MineEntity;
import com.hzq.baselibs.mvp.IModel;
import com.hzq.baselibs.mvp.IView;
import com.hzq.baselibs.net.BaseHttpResult;

import java.util.Map;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time  2018/6/12 22:57
 * @desc 契约类
 */
public interface MineContract {


    interface View extends IView {
        void showData(MineEntity testNews);
        void showLoginData(LoginEntity data);

    }

    interface Model extends IModel {
        Observable<BaseHttpResult<MineEntity>> getMineData();
        Observable<BaseHttpResult<LoginEntity>> getLoginData(Map<String,String> map);
    }

}
