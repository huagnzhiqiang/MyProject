package com.hzq.example.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzq.baselibs.glide.GlideUtils;
import com.hzq.baselibs.utils.HzqUtils;
import com.hzq.example.R;
import com.hzq.example.data.entity.MyProductEntity;

/**
 * @author 小强
 * @time 2018/11/2  16:58
 * @desc 我的作品适配器
 */
public class MyWorkAdapter extends BaseQuickAdapter<MyProductEntity.RowsBean, BaseViewHolder> {


    public MyWorkAdapter() {
        super(R.layout.item_my_work);
    }


    @Override
    protected void convert(BaseViewHolder helper, MyProductEntity.RowsBean item) {

        GlideUtils.loadFitCenterImage(mContext, item.getCover_img(), helper.getView(R.id.iv_product_img), R.drawable.load);

        helper.setText(R.id.tv_product_title,item.getTitle());
        helper.setText(R.id.tv_product_price, HzqUtils.getDecimalFormatTow(item.getPrice()));


    }
}
