package com.shangjie.itop.ui.main.mine;

import com.shangjie.itop.data.entity.LoginEntity;
import com.shangjie.itop.data.entity.MineEntity;
import com.shangjie.baselibs.mvp.IModel;
import com.shangjie.baselibs.mvp.IView;
import com.shangjie.baselibs.net.BaseHttpResult;

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
