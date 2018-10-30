package com.hzq.baselibs.glide;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;

/**
 * @author 小强
 * @time 2018/6/11 17:36
 * @desc 图片加载工具类
 */
public class GlideUtils {

    /**
     * 加载图片
     *
     * @param context  context
     * @param iv       imageView
     * @param url      图片地址
     * @param emptyImg 默认展位图
     */
    public static void loadImage(Context context, String url, ImageView iv, int emptyImg) {
        if (!TextUtils.isEmpty(url)) {

            GlideApp.with(context).
                    load(url).
                    error(emptyImg).
                    placeholder(emptyImg).
                    transition(new DrawableTransitionOptions().crossFade()).
                    diskCacheStrategy(DiskCacheStrategy.ALL)//图片缓存模式
                    .into(iv);
        } else {
            loadImage(context, iv, emptyImg, emptyImg);
        }
    }
    /**
     * 加载图片 不管图片多大都显示在中间
     *
     * @param context  context
     * @param iv       imageView
     * @param url      图片地址
     * @param emptyImg 默认展位图
     */
    public static void loadFitCenterImage(Context context, String url, ImageView iv, int emptyImg) {

        RequestOptions options = new RequestOptions().placeholder(emptyImg)  //加载成功之前占位图
                .error(emptyImg)    //加载错误之后的错误图
                .fitCenter() //指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
                //                .centerCrop() //指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
                .skipMemoryCache(true)  //跳过内存缓存
                //                .diskCacheStrategy(DiskCacheStrategy.ALL)   //缓存所有版本的图像
                //                .diskCacheStrategy(DiskCacheStrategy.NONE)  //跳过磁盘缓存
                //                .diskCacheStrategy(DiskCacheStrategy.DATA)  //只缓存原来分辨率的图片
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE) ; //只缓存最终的图片

        GlideApp.with(context).
                load(url).
                apply(options).
                into(iv);

    }


    /**
     * 加载圆角图片
     *
     * @param context  context
     * @param iv       imageView
     * @param url      图片地址
     * @param emptyImg 默认展位图
     */
    public static void loadRoundImage(Context context, ImageView iv, String url, int emptyImg) {
        if (!TextUtils.isEmpty(url)) {
            GlideApp.with(context).
                    load(url).
                    error(emptyImg).
                    placeholder(emptyImg).
                    transition(new DrawableTransitionOptions().crossFade()).
                    diskCacheStrategy(DiskCacheStrategy.ALL).//图片缓存模式
                    transform(new RoundedCorners(20)).
                    into(iv);
        } else {
            loadRoundImage(context, iv, emptyImg, emptyImg);
        }
    }

    /**
     * 加载圆形图片
     *
     * @param context  context
     * @param iv       imageView
     * @param url      图片地址
     * @param emptyImg 默认展位图
     */
    public static void loadCircleImage(Context context, String url, ImageView iv, int emptyImg) {

        RequestOptions options = new RequestOptions().placeholder(emptyImg)  //加载成功之前占位图
                .error(emptyImg)    //加载错误之后的错误图
                .fitCenter() //指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
//                .centerCrop() //指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
                .circleCrop()//指定图片的缩放类型为centerCrop （圆形）
                .skipMemoryCache(true)  //跳过内存缓存
//                .diskCacheStrategy(DiskCacheStrategy.ALL)   //缓存所有版本的图像
//                .diskCacheStrategy(DiskCacheStrategy.NONE)  //跳过磁盘缓存
//                .diskCacheStrategy(DiskCacheStrategy.DATA)  //只缓存原来分辨率的图片
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)  //只缓存最终的图片
                ;

        if (!TextUtils.isEmpty(url)) {
            Glide.with(context).load(url).apply(options).into(iv);
        } else {
            loadCircleImage(context, iv, emptyImg, emptyImg);
        }
    }

    /**
     * 加载drawable中的图片资源
     *
     * @param context  context
     * @param iv       imageView
     * @param resId    图片地址
     * @param emptyImg 默认展位图
     */
    public static void loadImage(Context context, ImageView iv, int resId, int emptyImg) {
        GlideApp.with(context).load(resId).placeholder(emptyImg).into(iv);
    }

    /**
     * 加载drawable中的图片资源 圆角
     *
     * @param context  context
     * @param iv       imageView
     * @param resId    图片地址
     * @param emptyImg 默认展位图
     */
    public static void loadRoundImage(Context context, ImageView iv, int resId, int emptyImg) {
        GlideApp.with(context).
                load(emptyImg).
                placeholder(emptyImg).
                transform(new RoundedCorners(20)).
                into(iv);
    }

    /**
     * 加载drawable中的图片资源 圆形
     *
     * @param context  context
     * @param iv       imageView
     * @param resId    图片地址
     * @param emptyImg 默认展位图
     */
    public static void loadCircleImage(Context context, ImageView iv, int resId, int emptyImg) {

        RequestOptions options = new RequestOptions().placeholder(emptyImg)  //加载成功之前占位图
                .error(emptyImg)    //加载错误之后的错误图
                .fitCenter() //指定图片的缩放类型为fitCenter （等比例缩放图片，宽或者是高等于ImageView的宽或者是高。）
                //                .centerCrop() //指定图片的缩放类型为centerCrop （等比例缩放图片，直到图片的狂高都大于等于ImageView的宽度，然后截取中间的显示。）
                .circleCrop()//指定图片的缩放类型为centerCrop （圆形）
                .skipMemoryCache(true)  //跳过内存缓存
                //                .diskCacheStrategy(DiskCacheStrategy.ALL)   //缓存所有版本的图像
                //                .diskCacheStrategy(DiskCacheStrategy.NONE)  //跳过磁盘缓存
                //                .diskCacheStrategy(DiskCacheStrategy.DATA)  //只缓存原来分辨率的图片
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE) ; //只缓存最终的图片

        GlideApp.with(context).
                load(resId).
                apply(options).
                into(iv);
    }


}
