package com.shangjie.itop.ui.main.login;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.shangjie.baselibs.base.BaseMvpActivity;
import com.shangjie.baselibs.utils.GsonUtil;
import com.shangjie.baselibs.utils.RegularUtils;
import com.shangjie.baselibs.utils.SpUtil;
import com.shangjie.baselibs.utils.StringUtil;
import com.shangjie.baselibs.utils.ToastUtils;
import com.shangjie.itop.constants.SpKeyConstant;
import com.shangjie.itop.data.entity.LoginEntity;
import com.shangjie.itop.ui.main.MainActivity;
import com.shangjie1.itop.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.wechat.friends.Wechat;

/**
 * @author 小强
 * @time 2018/7/31  14:53
 * @desc 登录页面
 */
public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.View {


    //账号
    @BindView(R.id.iv_login_phone) ImageView mIvLoginPhone;
    @BindView(R.id.login_phone_ed) TextInputEditText mLoginPhoneEd;

    //密码
    @BindView(R.id.iv_login_pwd) ImageView mIvLoginPwd;
    @BindView(R.id.login_pwd_ed) TextInputEditText mLoginPwdEd;

    @BindView(R.id.look_pwd) ImageView mLookPwd;
    @BindView(R.id.tvbtn_to_resetpwd) TextView mTvbtnToResetpwd;
    @BindView(R.id.tvbtn_to_reg) TextView mTvbtnToReg;

    @BindView(R.id.tvbtn_login) TextView mTvbtnLogin;

    @BindView(R.id.WXlogin) ImageView mWXlogin;

    /**
     * 获取布局 Id
     */
    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    /**
     * 获取 Intent 数据
     **/
    @Override
    protected void getIntent(Intent intent) {
    }

    /** 初始化View的代码写在这个方法中 */
    @Override
    protected void initView() {
        setSwipeBackEnable(false);//禁止滑动退出
        //        mImmersionBar.statusBarColor(R.color.color_61bef4).fullScreen(true).init();
    }

    /** 初始化监听器的代码写在这个方法中 */
    @Override
    protected void initListener() {
        mWXlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showShare(Wechat.NAME);

                Platform platform = ShareSDK.getPlatform(Wechat.NAME);

                //回调信息，可以在这里获取基本的授权返回的信息，但是注意如果做提示和UI操作要传到主线程handler里去执行
                platform.setPlatformActionListener(new PlatformActionListener() {

                    @Override
                    public void onError(Platform arg0, int arg1, Throwable arg2) {
                        // TODO Auto-generated method stub
                        arg2.printStackTrace();

                        Logger.d("onError--->:" + arg2.getMessage());
                    }

                    @Override
                    public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
                        // TODO Auto-generated method stub
                        //输出所有授权信息
                        arg0.getDb().exportData();
                        Logger.d("onComplete--->:" + arg0.getDb().exportData());
                        Logger.d("onComplete--->:" + arg2.toString());
                    }

                    @Override
                    public void onCancel(Platform arg0, int arg1) {
                        // TODO Auto-generated method stub

                    }
                });
                //authorize与showUser单独调用一个即可
                platform.authorize();//要功能不要数据，在监听oncomplete中不会返回用户数据
//
//                //想要移除授权状态，在想移除的地方执行下面的方法即可
//                platform.removeAccount(true);

            }
        });
    }


    private void showShare(String platform) {
        final OnekeyShare oks = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        if (platform != null) {
            oks.setPlatform(platform);
        }
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("标题");
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl("http://m.creatby.com/v2/manage/book/ydcnxx/");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("我是分享文本");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl("https://www.baidu.com/img/bd_logo1.png");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://m.creatby.com/v2/manage/book/ydcnxx/");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite("ShareSDK");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://m.creatby.com/v2/manage/book/ydcnxx/");

        //启动分享
        oks.show(this);
    }



    private void showShare() {

        Platform.ShareParams sp = new Platform.ShareParams();
        sp.setTitle("测试分享的标题");
        sp.setTitleUrl("http://m.creatby.com/v2/manage/book/ydcnxx/"); // 标题的超链接
        sp.setText("测试分享的文本");
        sp.setImageUrl("https://www.baidu.com/img/bd_logo1.png");
//        sp.setSite("发布分享的网站名称");
//        sp.setSiteUrl("http://m.creatby.com/v2/manage/book/ydcnxx/");

        Platform qzone = ShareSDK.getPlatform (SinaWeibo.NAME);
        // 设置分享事件回调（注：回调放在不能保证在主线程调用，不可以在里面直接处理UI操作）
        qzone.setPlatformActionListener (new PlatformActionListener() {
            public void onError(Platform arg0, int arg1, Throwable arg2) {
                //失败的回调，arg:平台对象，arg1:表示当前的动作，arg2:异常信息
                ToastUtils.showShort("分享失败");
            }
            public void onComplete(Platform arg0, int arg1, HashMap arg2) {
                //分享成功的回调
                ToastUtils.showShort("分享成功");
            }
            public void onCancel(Platform arg0, int arg1) {
                //取消分享的回调
                ToastUtils.showShort("取消分享");

            }
        });
        // 执行图文分享
        qzone.share(sp);

//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//        // title标题，微信、QQ和QQ空间等平台使用
//        oks.setTitle(getString(R.string.app_name));
//        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("https://www.baidu.com/img/bd_logo1.png");//确保SDcard下面存在此张图片
//        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
//        // 启动分享GUI
//        oks.show(this);
    }


    /** 初始数据的代码写在这个方法中，用于从服务器获取数据 */
    @Override
    protected void initData() {
        setToolbarTitle("登录");

        //回显登录账号密码

        String user = SpUtil.getInstance().getString(SpKeyConstant.LOGIN_USER);
        String pwd = SpUtil.getInstance().getString(SpKeyConstant.LOGIN_PWD);
        mLoginPhoneEd.setText(user);
        mLoginPwdEd.setText(pwd);

    }

    /**
     * 请求网络
     */
    @Override
    protected void networkRequest() {

    }

    @Override
    protected boolean isImmersionBarEnabled() {
        return true;
    }


    /**
     * 创建Presenter控制层
     */
    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter();
    }

    /**
     * @param data 返回登录成功后的数据
     */
    @Override
    public void showLoginData(LoginEntity data) {


        //保存登录账号密码
        String user = mLoginPhoneEd.getText().toString().trim();
        String pwd = mLoginPwdEd.getText().toString().trim();
        SpUtil.getInstance().putString(SpKeyConstant.LOGIN_USER, user);
        SpUtil.getInstance().putString(SpKeyConstant.LOGIN_PWD, pwd);


        //json字符串
        String jsonBody = GsonUtil.toJson(data);

        SpUtil.getInstance().putString(SpKeyConstant.LOGIN_MSG, jsonBody);
        startActivity(MainActivity.class);
        finishActivity();
    }


    @OnClick({R.id.tvbtn_login, R.id.toolbar_left_img})
    public void onClick(View view) {

        switch (view.getId()) {
            //登录
            case R.id.tvbtn_login:
                login();
                break;

            //返回
            case R.id.toolbar_left_img:
                startActivity(MainActivity.class);
                finishActivity();
                break;
        }
    }


    //登录操作
    private void login() {
        String phone = mLoginPhoneEd.getText().toString().trim();
        String pwd = mLoginPwdEd.getText().toString().trim();

        //        if(! StringUtil.isPhone(phone)) {
        //            ToastUtils.showShort("请输入手机号码");
        //            return;
        //        }


        if (StringUtil.isEmpty(phone)) {
            ToastUtils.showShort("手机号码不能为空");
            mLoginPhoneEd.setError("手机号码不能为空");
            return;
        }

        if (phone.length() != 11) {
            ToastUtils.showShort("手机号码长度不正确");
            mLoginPhoneEd.setError("手机号码长度不正确");

            return;
        }

        if (StringUtil.isEmpty(pwd)) {
            ToastUtils.showShort("密码不能为空");
            mLoginPwdEd.setError("密码不能为空");
            return;
        }

        if (!RegularUtils.isPattern(pwd)) {
            ToastUtils.showShort("密码必须为8-12位的字母加数字组合");
            mLoginPwdEd.setError("密码必须为8-12位的字母加数字组合");

            return;
        }

        Map<String, String> map = new HashMap<>();
        map.put("username", phone);
        map.put("password", pwd);
        mPresenter.requestLogin(map);

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
        ToastUtils.showShort(msg);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            startActivity(MainActivity.class);
            finishActivity();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
