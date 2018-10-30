package com.hzq.example.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hzq.baselibs.glide.GlideUtils;
import com.hzq.example.R;
import com.hzq.example.data.entity.RecomentEntity;

/**
 * @author 小强
 * @time 2018/10/27  16:57
 * @desc
 */
public class RecomentAdapter extends BaseMultiItemQuickAdapter<RecomentEntity.RowsBean, BaseViewHolder> {

    public RecomentAdapter() {
        super(null);

        //0:资讯  1:H5  2:视频 推荐|本地 其他
        addItemType(RecomentEntity.ARTICLE_INFORMATION, R.layout.item_article_information);//资讯
        addItemType(RecomentEntity.ARTICLE_H5, R.layout.item_article_h5);//H5
        addItemType(RecomentEntity.ARTICLE_VIDEO, R.layout.item_text_video);//视频 推荐|本地 其他
    }


    @Override
    protected void convert(BaseViewHolder helper, RecomentEntity.RowsBean item) {
        switch (helper.getItemViewType()) {

            //资讯
            case RecomentEntity.ARTICLE_INFORMATION:

                //标题
                helper.setText(R.id.tv_title, item.getTitle());

                //浏览
                helper.setText(R.id.tv_read, String.valueOf(item.getBrowse_count()));

                //点赞
                helper.setText(R.id.tv_like, String.valueOf(item.getPraise_count()));

                //已读
                helper.setText(R.id.tv_write, String.valueOf(item.getComment_count()));

                //时间
                helper.setText(R.id.tv_time, item.getCreate_datetime());

                //封面
                GlideUtils.loadFitCenterImage(mContext, item.getCover_img(), (ImageView) helper.getView(R.id.iv_cover), R.drawable.img_bg_default);



                break;

            //H5
            case RecomentEntity.ARTICLE_H5:
                //标题
                helper.setText(R.id.tv_title, item.getTitle());

                //浏览
                helper.setText(R.id.tv_read, String.valueOf(item.getBrowse_count()));

                //点赞
                helper.setText(R.id.tv_like, String.valueOf(item.getPraise_count()));

                //已读
                helper.setText(R.id.tv_write, String.valueOf(item.getComment_count()));

                //时间
                helper.setText(R.id.tv_time, item.getCreate_datetime());

                //封面
                GlideUtils.loadFitCenterImage(mContext, item.getCover_img(), (ImageView) helper.getView(R.id.iv_cover), R.drawable.load);


                break;

            //视频
            case RecomentEntity.ARTICLE_VIDEO:
                helper.setText(R.id.iv, "视频");
                break;

        }
    }
}
