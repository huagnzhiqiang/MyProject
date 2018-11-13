package com.hzq.baselibs.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.channels.FileChannel;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS;
import static java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS;
import static java.lang.Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT;
import static java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS;
import static java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A;
import static java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B;

/**
 * @author 小强
 * @time 2018/4/18  20:26
 * @desc 判短日期格式是否正确
 */
public class HzqUtils {

    /**
     * 判断日期格式和范围
     */
    public static boolean isDate(String date) {

        String rexp = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))" +
                "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "" + "|(" + "((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";

        Pattern pat = Pattern.compile(rexp);

        Matcher mat = pat.matcher(date);

        boolean dateType = mat.matches();

        return dateType;
    }

    /**
     * 判断是否正常的网址
     */
    public static boolean isUrl(String url) {
        //        String regex = "^([hH][tT]{2}[pP]:/*|[hH][tT]{2}[pP][sS]:/*|[fF][tT][pP]:/*)(([A-Za-z0-9-~]+).)+([A-Za-z0-9-~\\/])+(\\?{0,1}(([A-Za-z0-9-~]+\\={0,1})([A-Za-z0-9-~]*)\\&{0,1})*)$";
        String regex = "(?<=//|)((\\w)+\\.)+\\w+(:\\d*)?";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(url).matches();
    }

    /**
     * ==================判断是否是中文=====================
     */
    //    public static boolean isContainChinese(String str) {
    //
    //        Pattern p = Pattern.compile("[\\u4e00-\\u9fa5]");
    //        Matcher m = p.matcher(str);
    //        if (m.find()) {
    //            return true;
    //        }
    //        return false;
    //    }
    public static boolean isContainChinese(String checkStr) {
        if (!TextUtils.isEmpty(checkStr)) {
            char[] checkChars = checkStr.toCharArray();
            for (int i = 0; i < checkChars.length; i++) {
                char checkChar = checkChars[i];
                if (checkCharContainChinese(checkChar)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkCharContainChinese(char checkChar) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(checkChar);
        if (CJK_UNIFIED_IDEOGRAPHS == ub || CJK_COMPATIBILITY_IDEOGRAPHS == ub || CJK_COMPATIBILITY_FORMS == ub || CJK_RADICALS_SUPPLEMENT == ub || CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A == ub ||
                CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B == ub) {
            return true;
        }
        return false;
    }
    /** ==================判断是否是中文===================== */


    /**
     * 获取图片/视频大小
     *
     * @param path 传入视频路径
     * @return 视频大小
     */
    public static String ReadPictureVideoSize(String path) {

        File file = new File(path);
        FileChannel fc = null;
        String size = "";
        try {
            @SuppressWarnings("resource") FileInputStream fis = new FileInputStream(file);
            fc = fis.getChannel();
            BigDecimal fileSize = new BigDecimal(fc.size());
            size = fileSize.divide(new BigDecimal(1048576), 2, RoundingMode.HALF_UP) + "MB";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fc) {
                try {
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return size;
    }

    /**
     * 获取图片/视频大小
     *
     * @param path 传入视频路径
     * @return 视频大小
     */
    public static String ReadPictureSize(String path) {

        File file = new File(path);
        FileChannel fc = null;
        String size = "";
        try {
            @SuppressWarnings("resource") FileInputStream fis = new FileInputStream(file);
            fc = fis.getChannel();
            BigDecimal fileSize = new BigDecimal(fc.size());
            size = fileSize + "";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != fc) {
                try {
                    fc.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return size;
    }

    /**
     * 截取空格前面的数据
     *
     * @param str 2018/4/28 11:08:22
     * @return 2018-4-28
     */

    public static String getNewData(String str) {


        if (!TextUtils.isEmpty(str) && str.contains(" ")) {
            if (str.indexOf(" ") != -1) {
                return str.substring(0, str.indexOf(" "));
            }
        }

        return "";
    }

    /**
     * 将double类型 转换成 保留小数点后面2位小数
     *
     * @param data 需要转换的数值
     * @return 保留小数点后面1位小数
     */
    public static String getDecimalFormatTow(double data) {
        String size = "";
        BigDecimal fileSize = new BigDecimal(data);
        if (data < 10000 && data > 0) {
            if ((data + "").contains(".")) {
                String s_int = (data + "").substring(0, (data + "").indexOf(".")); // 取整数部分
                String s_radix = (data + "").substring((data + "").indexOf(".") + 1, (data + "").length()); //取小数部分
                if (s_radix.equals("00")) {
                    return s_int;
                } else if (s_radix.equals("0")) {
                    return s_int + "";
                } else {
                    return data + "";
                }
            } else {
                return data + "";
            }
        } else if (data == 0) {
            return "免费";
        } else {
            size = fileSize.divide(new BigDecimal(10000), 1, RoundingMode.DOWN) + "w";
        }
        return size;
    }

    /**
     * 将城市区域最后2位替换成0
     *
     * @param adCode 需要转换的城市区域code
     * @return 如果不为空将返回城市(如 : 广州市)的code
     */
    public static String getSbAdCode(String adCode) {

        if (!adCode.isEmpty()) {
            StringBuilder sb = new StringBuilder(adCode);
            //将城市区域最后2位替换成0
            sb.replace(adCode.length() - 2, adCode.length(), "00");
            return sb.toString();
        }
        return "";
    }



    /**
     * 数字转换
     * 1000 = 1K
     * 10000 = 10K
     *
     * @param data 输入数字
     * @return 输出数字
     */
    public static String ReadSize(int data) {
        String size = "";
        BigDecimal fileSize = new BigDecimal(data);
        if (data < 1000) {
            return data + "";
        } else if (data < 10000) {
            size = fileSize.divide(new BigDecimal(1000), 1, RoundingMode.HALF_UP) + "k";
        } else {
            size = fileSize.divide(new BigDecimal(10000), 1, RoundingMode.HALF_UP) + "w";
        }
        return size;
    }


    /**
     * 通过uri获取bitmap
     */
    public static Bitmap getBitmapFromUri(Context mContext, Uri uri) {
        try {
            // 读取uri所在的图片
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), uri);
            return bitmap;
        } catch (Exception e) {
            //            Log.e(TAG, e.getMessage());
            //            Log.e(TAG, "目录为：" + uri);
            e.printStackTrace();
            return null;
        }
    }


    /**
     * @param @param  bitmap
     * @param @return 设定文件
     * @return String    返回类型

     */

    /**
     * 将bitmap转换成base64
     * @param bitmap
     * @return base64
     */
    @SuppressLint("NewApi")
    public static String bitmapToBase64(Bitmap bitmap) {

        // 要返回的字符串
        String reslut = null;

        ByteArrayOutputStream baos = null;

        try {

            if (bitmap != null) {

                baos = new ByteArrayOutputStream();
                /**
                 * 压缩只对保存有效果bitmap还是原来的大小
                 */
                bitmap.compress(Bitmap.CompressFormat.JPEG, 30, baos);

                baos.flush();
                baos.close();
                // 转换为字节数组
                byte[] byteArray = baos.toByteArray();

                // 转换为字符串
                reslut = Base64.encodeToString(byteArray, Base64.DEFAULT);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return reslut;
    }

}
