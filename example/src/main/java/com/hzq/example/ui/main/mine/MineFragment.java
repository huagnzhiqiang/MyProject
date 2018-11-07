package com.hzq.example.ui.main.mine;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hzq.baselibs.base.BaseFragment;
import com.hzq.baselibs.glide.GlideUtils;
import com.hzq.baselibs.utils.StringUtil;
import com.hzq.baselibs.utils.ToastUtils;
import com.hzq.example.R;
import com.hzq.example.adapter.MineMenuAdapter;
import com.hzq.example.constants.Constant;
import com.hzq.example.data.Login.LoginMsgHelper;
import com.hzq.example.data.entity.LoginEntity;
import com.hzq.example.data.entity.MenuEntity;
import com.hzq.example.data.entity.MineEntity;
import com.hzq.example.ui.main.login.LoginActivity;
import com.hzq.example.ui.main.mine.activity.EnterpriseInfoEditActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * @author 小强
 * @time 2018/6/12 22:57
 * @desc 我的
 */
public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View {

    @BindView(R.id.iv_user_head) ImageView mIvUserHead; //头像
    @BindView(R.id.username) TextView mUsername;//名字
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;//我的view

    @BindView(R.id.tv_share) TextView mTvShare;//分享
    @BindView(R.id.tv_admission) TextView mTvAdmission;//入驻申请
    @BindView(R.id.tv_feedback) TextView mTvFeedback;//意见反馈icon_anli.png
    @BindView(R.id.tv_setting) TextView mTvSetting;//设置


    //一般用户
    public static final int[] defaultlMine = {R.drawable.me_icon_production, R.drawable.me_icon_purse, R.drawable.me_icon_liuzi, R.drawable.me_icon_watch,};
    public static final String[] defaultMineName = {"作品", "钱包", "留资", "关注",};

    //一般用户0
    public static final int[] personalMine = {R.drawable.me_icon_production, R.drawable.material, R.drawable.me_icon_liuzi, R.drawable.me_icon_watch, R.drawable.me_icon_remind, R.drawable.me_icon_comment};
    public static final String[] personalMineName = {"作品", "素材", "钱包", "关注", "通知", "评论"};

    //设计师1
    public static final int[] designerMine = {R.drawable.me_icon_production, R.drawable.icon_anli, R.drawable.icon_dingzhi, R.drawable.material, R.drawable.me_icon_hot, R.drawable.me_icon_purse, R.drawable.me_icon_data, R.drawable.me_icon_watch, R.drawable.me_icon_remind, R.drawable.me_icon_comment};
    public static final String[] designerMineName = {"作品", "我的案例", "定制管理", "素材", "热点", "钱包", "数据", "关注", "通知", "评论"};

    //  企业2
    public static final int[] qiyeMine = {R.drawable.me_icon_production, R.drawable.icon_dingzhi, R.drawable.me_icon_hot, R.drawable.material, R.drawable.me_icon_purse, R.drawable.me_icon_extend, R.drawable.me_icon_data, R.drawable.me_icon_watch, R.drawable.me_icon_remind, R.drawable.me_icon_comment};
    public static final String[] qiyeMineName = {"作品", "定制管理", "热点", "素材", "钱包", "推广", "数据", "关注", "通知", "评论"};


    //自营销3
    public static final int[] marketMine = {R.drawable.me_icon_ordermanage, R.drawable.me_icon_hot, R.drawable.me_icon_purse, R.drawable.me_icon_data, R.drawable.me_icon_watch, R.drawable.me_icon_remind, R.drawable.me_icon_comment};
    public static final String[] marketMineName = {"订单", "热点", "钱包", "数据", "关注", "通知", "评论"};


    public static MineFragment newInstance() {
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected MinePresenter createPresenter() {
        return new MinePresenter();
    }

    @Override
    public void onResume() {
        super.onResume();
        setinitUI();
    }

    /**
     * 根据用户类型判断显示不同Ui
     */
    private void setinitUI() {

        List<MenuEntity> mineList = new ArrayList<>();

        //没有登录 默认的我的菜单
        mTvAdmission.setVisibility(View.VISIBLE);
        for (int i = 0; i < defaultlMine.length; i++) {
            MenuEntity menuBean = new MenuEntity();
            menuBean.setIconId(defaultlMine[i]);
            menuBean.setName(defaultMineName[i]);
            mineList.add(menuBean);
        }

        LoginEntity result = LoginMsgHelper.getResult();

        if (result != null) {

            //设置头像
            GlideUtils.loadCircleImage(getContext(), result.getUser_info().getHead_img(), mIvUserHead, R.drawable.user_moren);

            //设置名称
            if (!StringUtil.isEmpty(result.getUser_info().getNickname())) {
                mUsername.setText(result.getUser_info().getNickname());
            } else {
                mUsername.setText(result.getOther_info().getName());
            }


            /**----------------根据不同的用户类型去判断显示不同的UI----  length - 1屏蔽评论功能------------*/
            int userType = result.getUser_type();
            switch (userType) {

                //普通用户
                case Constant.UserType.USER_TYPE_0:
                    //显示入驻申请
                    mTvAdmission.setVisibility(View.VISIBLE);
                    mineList.clear();
                    for (int i = 0; i < personalMine.length; i++) {
                        MenuEntity menuBean = new MenuEntity();
                        menuBean.setIconId(personalMine[i]);
                        menuBean.setName(personalMineName[i]);
                        mineList.add(menuBean);
                    }

                    break;

                //设计师
                case Constant.UserType.USER_TYPE_1:
                    mTvAdmission.setVisibility(View.GONE);
                    mineList.clear();
                    for (int i = 0; i < designerMine.length - 1; i++) {
                        MenuEntity menuBean = new MenuEntity();
                        menuBean.setIconId(designerMine[i]);
                        menuBean.setName(designerMineName[i]);
                        mineList.add(menuBean);
                    }
                    break;

                //企业
                case Constant.UserType.USER_TYPE_2:
                    mUsername.setText(LoginMsgHelper.getResult().getName());
                    mTvAdmission.setVisibility(View.GONE);
                    mineList.clear();
                    for (int i = 0; i < qiyeMine.length - 1; i++) {
                        MenuEntity menuBean = new MenuEntity();
                        menuBean.setIconId(qiyeMine[i]);
                        menuBean.setName(qiyeMineName[i]);
                        mineList.add(menuBean);
                    }
                    //企业名称
                    break;
                //自营销
                case Constant.UserType.USER_TYPE_3:
                    mTvAdmission.setVisibility(View.GONE);
                    mineList.clear();
                    for (int i = 0; i < marketMine.length - 1; i++) {
                        MenuEntity menuBean = new MenuEntity();
                        menuBean.setIconId(marketMine[i]);
                        menuBean.setName(marketMineName[i]);
                        mineList.add(menuBean);
                    }
                    break;

            }

        }

        MineMenuAdapter adapter = new MineMenuAdapter(mineList);

        //由于使用弹性布局 ScrollView和RecycleView互动冲突 所以禁止RecycleView滑动
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 4) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void showData(MineEntity testNews) {

    }

    @Override
    public void showLoginData(LoginEntity data) {

    }

    /**
     * 初始化View的代码写在这个方法中
     */
    @Override
    protected void initView(View viwe) {

    }

    /**
     * 请求网络
     */
    @Override
    public void onLazyLoad() {

    }

    /**
     * 显示错误
     *
     * @param msg  错误信息
     * @param code 错误code
     */
    @Override
    public void showError(String msg, int code) {
        ToastUtils.showShort(msg);

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
     * 点击事件
     */
    @OnClick({R.id.iv_user_head, R.id.username})
    public void onClick(View view) {
        switch (view.getId()) {

            //点击头像和名称
            case R.id.iv_user_head:
            case R.id.username:
                EditUserInfo();
                break;
        }
    }


    //编辑信息
    private void EditUserInfo() {
        LoginEntity result = LoginMsgHelper.getResult();
        if (result == null) {
            startActivity(LoginActivity.class);
            finishActivity();
        } else {
            int userType = result.getUser_type();

            switch (userType) {

                //普通用户
                case Constant.UserType.USER_TYPE_0:

                    break;

                //设计师
                case Constant.UserType.USER_TYPE_1:
                    break;

                //企业
                case Constant.UserType.USER_TYPE_2:
                    startActivity(EnterpriseInfoEditActivity.class);
                    break;

                //自营销人
                case Constant.UserType.USER_TYPE_3:
                    break;

            }
        }
    }


}
