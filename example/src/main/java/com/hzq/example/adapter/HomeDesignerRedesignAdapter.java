package com.hzq.example.adapter;

import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzq.baselibs.glide.GlideUtils;
import com.hzq.baselibs.utils.StringUtils;
import com.hzq.example.R;
import com.hzq.example.data.entity.HomeDesignerEntity;

/**
 * @author 小强
 * @time 2018/8/2  17:49
 * @desc 首页设计师适配器
 */
public class HomeDesignerRedesignAdapter extends BaseQuickAdapter<HomeDesignerEntity.RowsBean, BaseViewHolder> {


    public HomeDesignerRedesignAdapter() {
        super(R.layout.item_home_designer_redesign);
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeDesignerEntity.RowsBean item) {

        //头像
        GlideUtils.loadCircleImage(mContext, item.getHead_img(), helper.getView(R.id.im_designer_head), R.drawable.user_moren);

        //设计师名字
        helper.setText(R.id.iv_designer_name, item.getNickname());

        //地址
        TextView tvCity = helper.getView(R.id.tv_designer_address);

        if (!StringUtils.isEmpty(item.getCityName())){
            tvCity.setText(item.getCityName());
            tvCity.setVisibility(View.VISIBLE);
        }else {
            tvCity.setVisibility(View.GONE);
        }


        TextView goodtag = helper.getView(R.id.tv_designer_goodtag);

        if (!StringUtils.isEmpty(item.getFieldName())) {
            if (item.getFieldName().contains(",")) {
                String designer_tag = item.getFieldName().replace(",", "/");
                goodtag.setText(designer_tag + "");
            } else {
                goodtag.setText(item.getFieldName() + "");
            }
        } else {
            goodtag.setText("");
        }

        helper.setText(R.id.tv_product_count, "作品" + item.getProduct_count());
        helper.setText(R.id.tv_case_count, "案例" + item.getCase_count());
        helper.setText(R.id.tv_fans_count, "粉丝" + item.getFans_count());
    }


}
