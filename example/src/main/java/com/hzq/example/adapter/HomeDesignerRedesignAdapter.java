package com.hzq.example.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzq.example.R;
import com.hzq.example.data.entity.TemplateReadesignEntity;

import java.util.List;

/**
 * @author 小强
 * @time 2018/8/2  17:49
 * @desc 首页设计师适配器
 */
public class HomeDesignerRedesignAdapter extends BaseQuickAdapter<TemplateReadesignEntity,BaseViewHolder> {

    public HomeDesignerRedesignAdapter(int layoutResId, @Nullable List<TemplateReadesignEntity> data) {
        super(R.layout.item_home_designer_redesign, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, TemplateReadesignEntity item) {
    }
}
