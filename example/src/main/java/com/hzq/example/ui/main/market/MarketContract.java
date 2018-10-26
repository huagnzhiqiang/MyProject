package com.hzq.example.ui.main.market;


import com.hzq.baselibs.mvp.IModel;
import com.hzq.baselibs.mvp.IView;

/**
 * @author 小强
 * @time 2018/6/12 22:57
 * @desc 契约类
 */
public interface MarketContract {


    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends IView {

    }

    interface Model extends IModel {

    }

}
