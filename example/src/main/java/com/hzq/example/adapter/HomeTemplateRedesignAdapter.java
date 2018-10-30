package com.hzq.example.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzq.baselibs.glide.GlideUtils;
import com.hzq.baselibs.utils.HzqUtils;
import com.hzq.example.R;
import com.hzq.example.data.entity.TemplateReadesignEntity;

/**
 * @author 小强
 * @time 2018/8/2  18:03
 * @desc 首页案例
 */
public class HomeTemplateRedesignAdapter extends BaseQuickAdapter<TemplateReadesignEntity.RowsBean, BaseViewHolder> {


    public HomeTemplateRedesignAdapter() {
        super(R.layout.item_home_template_redesign);

    }

    @Override
    protected void convert(BaseViewHolder helper, TemplateReadesignEntity.RowsBean item) {
        helper.setText(R.id.tv_product_title, item.getTitle());
        helper.setText(R.id.tv_product_price, "¥" + item.getPrice());
        //浏览量
        helper.getView(R.id.tv_customized_count).setVisibility(View.VISIBLE);
        helper.setText(R.id.tv_customized_count, HzqUtils.ReadSize(item.getBrowse_count()) + " 浏览");
        ImageView ivProduct = helper.getView(R.id.iv_product_img);//图片
        GlideUtils.loadFitCenterImage(mContext, item.getCover_img(), ivProduct, R.drawable.load);

    }
}
