package com.hzq.example.ui.main.home;


import com.hzq.example.data.entity.TestNews;
import com.hzq.baselibs.mvp.IModel;
import com.hzq.baselibs.mvp.IView;
import com.hzq.baselibs.net.BaseHttpResult;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author 小强
 * @time 2018/6/12 22:57
 * @desc 契约类
 */
public interface HomeContract {


    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

        void showData(List<TestNews> testNews);
    }

    interface Model extends IModel{

        Observable<BaseHttpResult<List<TestNews>>> getGankData();
    }

}
