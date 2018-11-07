package com.hzq.example.ui.main.mine.activity;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hzq.baselibs.base.BaseActivity;
import com.hzq.baselibs.glide.GlideUtils;
import com.hzq.baselibs.utils.StringUtil;
import com.hzq.example.R;
import com.hzq.example.constants.Constant;
import com.hzq.example.data.Login.LoginMsgHelper;
import com.hzq.example.data.entity.LoginEntity;

import butterknife.BindView;

/**
 * @author 小强
 * @time 2018/11/6  17:53
 * @desc 企业信息修改页面
 */
public class EnterpriseInfoEditActivity extends BaseActivity {


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


}
