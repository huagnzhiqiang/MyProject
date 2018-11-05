package com.hzq.example.adapter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzq.baselibs.app.BaseApplication;
import com.hzq.baselibs.glide.GlideUtils;
import com.hzq.baselibs.utils.DisplayUtils;
import com.hzq.baselibs.utils.ToastUtils;
import com.hzq.example.R;
import com.hzq.example.data.entity.HomeCustomizeEntity;

import java.util.List;

/**
 * @author 小强
 * @time 2018/10/17  17:26
 * @desc 设计师投票的适配器
 */
public class HomeCoustomizedRedesignListAdapter extends BaseQuickAdapter<HomeCustomizeEntity.RowsBean.DemandDesignerListBean, BaseViewHolder> {


    private  int mWidth;

    public HomeCoustomizedRedesignListAdapter(@Nullable List<HomeCustomizeEntity.RowsBean.DemandDesignerListBean> data) {
        super(R.layout.home_customized_redesign_designer_item, data);

        int screenWidth = DisplayUtils.getScreenWidth(BaseApplication.getContext());
        mWidth = (screenWidth - 16) / 10;

    }


    @Override
    protected void convert(BaseViewHolder helper, HomeCustomizeEntity.RowsBean.DemandDesignerListBean item) {


        ImageView ivHead = helper.getView(R.id.iv_customized_redesign_head);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(mWidth,mWidth);
        params.rightMargin = DisplayUtils.dip2px(mContext, 6);
        ivHead.setLayoutParams(params);

        GlideUtils.loadCircleImage(mContext, item.getHead_img(), ivHead, R.drawable.load);

        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳到设计师详情页
                Bundle bundle = new Bundle();
                //                bundle.putString(DesignerDetilRedesignActivity.DESIGNER_ID, item.getDesigner_id() + "");
                //                CommonUtils.goActivity(mContext, DesignerDetilRedesignActivity.class, bundle);
                ToastUtils.showShort("跳到设计师详情页 id-->" + item.getDesigner_id());


            }
        });
    }
}
