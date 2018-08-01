package com.hzq.example.ui.main.video;


import com.hzq.baselibs.mvp.BasePresenter;

/**
 * @author 小强
 * @time  2018/6/12 22:57
 * @desc
 */
public class VideoPresenter extends BasePresenter<VideoContract.Model,VideoContract.View> {
    @Override
    protected VideoContract.Model createModel() {
        return new VideoModel();
    }


    public void requestData(){

    }
}
