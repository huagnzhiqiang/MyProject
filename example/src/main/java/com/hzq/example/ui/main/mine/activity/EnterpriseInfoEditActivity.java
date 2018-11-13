package com.hzq.example.ui.main.mine.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzq.baselibs.base.BaseMvpActivity;
import com.hzq.baselibs.glide.GlideUtils;
import com.hzq.baselibs.net.BaseHttpResult;
import com.hzq.baselibs.utils.HzqUtils;
import com.hzq.baselibs.utils.StringUtil;
import com.hzq.example.R;
import com.hzq.example.constants.Constant;
import com.hzq.example.data.Login.LoginMsgHelper;
import com.hzq.example.data.entity.LoginEntity;
import com.hzq.example.ui.main.mine.contract.EnterpriseContract;
import com.hzq.example.ui.main.mine.presenter.EnterprisePresenter;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author 小强
 * @time 2018/11/6  17:53
 * @desc 企业信息修改页面
 */
public class EnterpriseInfoEditActivity extends BaseMvpActivity<EnterprisePresenter> implements EnterpriseContract.View {


    @BindView(R.id.user_img) ImageView mUserImg;//头像

    @BindView(R.id.qiye_name_ed) TextView mQiyeNameEd;//企业名称

    @BindView(R.id.qiye_good_ed) TextView mQiyeGoodEd;//所属行业

    @BindView(R.id.qiye_mode_ed) TextView mQiyeModeEd;//企业规模

    @BindView(R.id.qiye_vip_tv) TextView mQiyeVipTv;//会员

    @BindView(R.id.qiye_city_ed) TextView mQiyeCityEd;//城市

    @BindView(R.id.qiye_introduction_name_ed) TextView mQiyeIntroductionNameEd;//企业简称

    @BindView(R.id.qiye_type_tv) TextView mQiyeTypeTv;//用户类型

    @BindView(R.id.user_img_layout) RelativeLayout mUserImgLayout;
    @BindView(R.id.user_type_layout) LinearLayout mUserTypeLayout;
    @BindView(R.id.qiye_vip) TextView mQiyeVip;
    @BindView(R.id.arrow_vip1) ImageView mArrowVip1;
    @BindView(R.id.qiye_vip_layout) RelativeLayout mQiyeVipLayout;
    @BindView(R.id.tv_name) TextView mTvName;
    @BindView(R.id.arrow_name) ImageView mArrowName;
    @BindView(R.id.qiye_name_layout) RelativeLayout mQiyeNameLayout;
    @BindView(R.id.tv_introduction_name) TextView mTvIntroductionName;
    @BindView(R.id.qiye_introduction_layout) RelativeLayout mQiyeIntroductionLayout;
    @BindView(R.id.tv_good) TextView mTvGood;
    @BindView(R.id.arrow_good) ImageView mArrowGood;
    @BindView(R.id.qiye_good_layout) RelativeLayout mQiyeGoodLayout;
    @BindView(R.id.tv_mode) TextView mTvMode;
    @BindView(R.id.arrow) ImageView mArrow;
    @BindView(R.id.qiye_mode_layout) RelativeLayout mQiyeModeLayout;
    @BindView(R.id.tv_city) TextView mTvCity;
    @BindView(R.id.arrow_city) ImageView mArrowCity;
    @BindView(R.id.qiye_city_layout) RelativeLayout mQiyeCityLayout;

    private String mHeadImgUrl;//头像路径

    /**
     * 获取布局 Id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_enterperise_info_edit;
    }

    /**
     * 创建Presenter
     *
     * @return 返回当前的Presenter
     */
    @Override
    protected EnterprisePresenter createPresenter() {
        return new EnterprisePresenter();
    }

    /**
     * 请求网络
     */
    @Override
    protected void networkRequest() {

    }


    /**
     * 初始化View的代码写在这个方法中
     */
    @Override
    protected void initView() {

        setToolbarTitle("个人信息");
        setToolRightTitle("修改");

        LoginEntity result = LoginMsgHelper.getResult();

        if (result != null) {

            //企业的信息
            LoginEntity.OtherInfoBean otherInfo = result.getOther_info();

            if (!StringUtil.isEmpty(result.getUser_info().getHead_img())) {

                mHeadImgUrl = result.getUser_info().getHead_img();
                GlideUtils.loadCircleImage(this, mHeadImgUrl, mUserImg, R.drawable.user_moren);

            }
            if (!StringUtil.isEmpty(result.getName())) {
                mQiyeNameEd.setText(result.getName());
            }

            //所属企业
            if (StringUtil.HasDigit(result.getOther_info().getTrade())) {
                //                mQiyeGoodEd.setText(modifyInformationUtil.getDefaultValue(loginMsg.getOther_info().getTrade()) + "");
            } else {
                mQiyeGoodEd.setText(otherInfo.getTrade());
            }

            //企业规模
            mQiyeModeEd.setText(otherInfo.getScale());

            mQiyeTypeTv.setText("企业");

            //会员
            switch (otherInfo.getVersion()) {

                case Constant.VipType.VIP_0:
                    mQiyeVipTv.setText("普通用户");
                    break;
                case Constant.VipType.VIP_1:
                    mQiyeVipTv.setText("粉钻版");
                    break;
                case Constant.VipType.VIP_2:
                    mQiyeVipTv.setText("蓝钻版");
                    break;
                case Constant.VipType.VIP_3:
                    mQiyeVipTv.setText("黑钻版");
                    break;
            }

            if (!StringUtil.isEmpty(otherInfo.getShort_name())) {
                mQiyeIntroductionNameEd.setText(otherInfo.getShort_name());
            }
            if (!StringUtil.isEmpty(otherInfo.getProvince())) {
                if (StringUtil.HasDigit(otherInfo.getProvince())) {
                    //                    mQiyeCityEd.setText(jsonAddressUtil.getDefaultProvinceValue(otherInfo.getProvince()) + "," + jsonAddressUtil.getDefaultCityValue(loginMsg.getOther_info().getProvince(), loginMsg.getOther_info().getCity()));
                } else {
                    mQiyeCityEd.setText(otherInfo.getProvince() + "," + otherInfo.getCity());
                }
            } else {
                if (StringUtil.HasDigit(result.getUser_info().getProvince())) {
                    //                    mQiyeCityEd.setText(jsonAddressUtil.getDefaultProvinceValue(otherInfo.getProvince()) + "," + jsonAddressUtil.getDefaultCityValue(loginMsg.getUser_info().getProvince(), loginMsg.getUser_info().getCity()));
                } else {
                    mQiyeCityEd.setText(otherInfo.getProvince() + "," + otherInfo.getCity());
                }
                //                qiyeCityEd.setText(jsonAddressUtil.getDefaultProvinceValue(loginMsg.getUser_info().getProvince()) + "," + jsonAddressUtil.getDefaultCityValue(loginMsg.getUser_info().getProvince(), loginMsg.getUser_info().getCity()));
            }

        }
    }


    @OnClick({R.id.tv_toolbar_right, R.id.user_img, R.id.qiye_name_ed, R.id.qiye_introduction_name_ed, R.id.qiye_good_ed, R.id.qiye_mode_layout, R.id.qiye_city_ed})
    public void onClick(View view) {
        switch (view.getId()) {

            //修改
            case R.id.tv_toolbar_right:
                break;

            //头像
            case R.id.user_img:


                // 单独拍照
                PictureSelector.create(EnterpriseInfoEditActivity.this).openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                        .minSelectNum(1)// 最小选择数量
                        //                        .selectionMode( PictureConfig.MULTIPLE )// 多选 or 单选
                        .previewImage(true)// 是否可预览图片
                        .isCamera(true)// 是否显示拍照按钮
                        .enableCrop(false)// 是否裁剪
                        //                        .compress(cb_compress.isChecked())// 是否压缩
                        .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
                        .withAspectRatio(3, 2)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                        .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                        .freeStyleCropEnabled(false)// 裁剪框是否可拖拽
                        .circleDimmedLayer(true)// 是否圆形裁剪
                        .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                        .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                        .openClickSound(true)// 是否开启点击声音
                        .previewEggs(true)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                        //.cropCompressQuality(90)// 裁剪压缩质量 默认为100
                        .minimumCompressSize(100)// 小于100kb的图片不压缩
                        //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                        .rotateEnabled(false) // 裁剪是否可旋转图片
                        .scaleEnabled(true)// 裁剪是否可放大缩小图片
                        //.videoQuality()// 视频录制质量 0 or 1
                        //.videoSecond()////显示多少秒以内的视频or音频也可适用
                        .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code


                break;
            case R.id.qiye_name_ed:
                break;
            case R.id.qiye_introduction_name_ed:
                break;
            case R.id.qiye_good_ed:
                break;
            case R.id.qiye_mode_layout:
                break;
            case R.id.qiye_city_ed:
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片、视频、音频选择结果回调
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    // 例如 LocalMedia 里面返回三种path
                    // 1.media.getPath(); 为原图path
                    // 2.media.getCutPath();为裁剪后path，需判断media.isCut();是否为true  注意：音视频除外
                    // 3.media.getCompressPath();为压缩后path，需判断media.isCompressed();是否为true  注意：音视频除外
                    // 如果裁剪并压缩了，以取压缩路径为准，因为是先裁剪后压缩的

                    String path = selectList.get(0).getPath();

                    if (path != null) {

                        GlideUtils.loadCircleImage(this,path,mUserImg,R.drawable.user_moren);
                        Uri ImgUri = Uri.fromFile(new File(path));
                        Bitmap bitmap = HzqUtils.getBitmapFromUri(this, ImgUri);
                        String base64 = HzqUtils.bitmapToBase64(bitmap);

                        Map<String, String> map = new HashMap<>();
                        map.put("imageBase64", base64);
                        map.put("fileName", ".png");

                        mPresenter.requistUpImager(map);
                        showLoadingDialog("图片上传中...");
                    }

                    break;
            }
        }
    }


    /**
     * 显示错误
     *
     * @param msg  错误信息
     * @param code 错误code
     */
    @Override
    public void showError(String msg, int code) {

    }

    /**
     * 显示网络错误
     *
     * @param msg  错误信息
     * @param code 错误code
     */
    @Override
    public void showNetworkError(String msg, int code) {

    }

    /**
     * 上传图片返回数据
     * <p>
     * result 返回结果
     */
    @Override
    public void showUpImagerData(BaseHttpResult<String> result) {
        Logger.d("showUpImagerData--->:" + result.getData());
    }
}
