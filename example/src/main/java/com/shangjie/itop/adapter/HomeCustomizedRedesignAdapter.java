package com.shangjie.itop.adapter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.orhanobut.logger.Logger;
import com.shangjie.baselibs.glide.GlideUtils;
import com.shangjie.baselibs.utils.HzqUtils;
import com.shangjie.baselibs.utils.ToastUtils;
import com.shangjie.itop.data.entity.HomeCustomizeEntity;
import com.shangjie1.itop.R;

import static com.shangjie.itop.constants.Constant.CustomOrderStatus.COMPLETION;

/**
 * @author 小强
 * @time 2018/10/16  15:03
 * @desc 首页定制页面适配器
 */
public class HomeCustomizedRedesignAdapter extends BaseQuickAdapter<HomeCustomizeEntity.RowsBean, BaseViewHolder> {

    public HomeCustomizedRedesignAdapter() {
        super(R.layout.item_home_customized);
    }


    @Override
    protected void convert(BaseViewHolder helper, HomeCustomizeEntity.RowsBean item) {

        //头像
        GlideUtils.loadCircleImage(mContext, item.getHead_img(), (ImageView) helper.getView(R.id.iv_home_customized_redesign_head), R.drawable.load);

        //标题
        helper.setText(R.id.tv_home_customized_redesign_title, item.getTitle());
        helper.setText(R.id.iv_home_customized_redesign_name, item.getEnterprise_nickname());

        GlideUtils.loadFitCenterImage(mContext, item.getReference_img(), (ImageView) helper.getView(R.id.iv_home_customized_redesign_cover), R.drawable.load);

        //描述
        helper.setText(R.id.tv_home_customized_redesign_describe, item.getDescription());

        //预算
        helper.setText(R.id.tv_home_customized_redesign_price, HzqUtils.getDecimalFormatTow(Double.parseDouble(item.getPrice())));

        //地址
        String cityName = item.getCity_name();
        if (TextUtils.isEmpty(cityName)) {
            helper.getView(R.id.tv_home_customized_redesign_address).setVisibility(View.GONE);
        } else {
            helper.getView(R.id.tv_home_customized_redesign_address).setVisibility(View.VISIBLE);
            helper.setText(R.id.tv_home_customized_redesign_address, item.getCity_name());
        }

        //时间
        helper.setText(R.id.tv_home_customized_redesign_time, item.getCreate_datetime().substring(0, item.getCreate_datetime().indexOf(" ")));

        //浏览
        helper.setText(R.id.tv_home_customized_redesign_browse, HzqUtils.ReadSize(item.getBrowse_count()));

        //投标人数
        helper.setText(R.id.tv_home_customized_redesign_count, item.getDesigner_count() + "人投标");


        //显示该定制案例是否在进行中或者完成
        TextView tvHomeCustomizedRedesignTender = helper.getView(R.id.tv_home_customized_redesign_tender);
        TextView demandTender = helper.getView(R.id.tv_home_customized_redesign_tag);

        switch (item.getDemand_status()) {
            //9 已完成
            case COMPLETION:
                demandTender.setBackgroundResource(R.drawable.custom_complete);
                tvHomeCustomizedRedesignTender.setVisibility(View.GONE);
                break;
            default:
                demandTender.setBackgroundResource(R.drawable.custom_for);
                tvHomeCustomizedRedesignTender.setVisibility(View.VISIBLE);
                break;
        }

        TextView tvRedesign = helper.getView(R.id.tv_home_customized_redesign_btn);//去抢个沙发
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);//投标头像集合


        //加载设计师头像的RecyclerView
        if (item.getDemand_designer_list().size() <= 0) {

            recyclerView.setVisibility(View.GONE);
            tvRedesign.setVisibility(View.VISIBLE);

            tvRedesign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //去抢个沙发 跳转到需求详细页面
                    Bundle bundle = new Bundle();
                    bundle.putString("demand_id", String.valueOf(item.getId()));
                    //                    CommonUtils.goActivity(mContext, DemandDetilActivity.class, bundle);
                    ToastUtils.showShort("设计师id-->" + item.getId());
                }
            });


        } else {
            //表示已有人投标
            recyclerView.setVisibility(View.VISIBLE);
            tvRedesign.setVisibility(View.GONE);

            Logger.d("表示已有人投标--->:" + item.getDemand_designer_list());
            HomeCoustomizedRedesignListAdapter adapter = new HomeCoustomizedRedesignListAdapter(mContext,item.getDemand_designer_list());
            adapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_RIGHT);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayout.HORIZONTAL,false));
            recyclerView.setAdapter(adapter);

        }

    }
}
