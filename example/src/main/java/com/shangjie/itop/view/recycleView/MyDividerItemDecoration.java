package com.shangjie.itop.view.recycleView;

import android.content.Context;

import com.yanyusong.y_divideritemdecoration.Y_Divider;
import com.yanyusong.y_divideritemdecoration.Y_DividerBuilder;
import com.yanyusong.y_divideritemdecoration.Y_DividerItemDecoration;

/**
 * @author 小强
 * @time 2018/8/3  10:59
 * @desc recycleView中的分割线 一行3列
 */
public class MyDividerItemDecoration extends Y_DividerItemDecoration {

    public MyDividerItemDecoration(Context context) {
        super(context);
    }

    @Override
    public Y_Divider getDivider(int itemPosition) {
        Y_Divider divider = null;
        switch (itemPosition % 3) {
            case 0:
                divider = new Y_DividerBuilder().setLeftSideLine(true, 0xffffff, 13, 0, 0).
                        setBottomSideLine(true, 0xffffff, 20, 0, 0).
                        create();
                break;
            case 1:
                //
                divider = new Y_DividerBuilder().setLeftSideLine(true, 0xffffff, 10, 0, 0).
                        setBottomSideLine(true, 0xffffff, 20, 0, 0).
                        create();
                break;
            case 2:
                divider = new Y_DividerBuilder().setLeftSideLine(true, 0xffffff, 10, 0, 0).
                        setRightSideLine(true, 0xffffff, 13, 0, 0).
                        setBottomSideLine(true, 0xffffff, 20, 0, 0).
                        create();
                break;

            default:
                divider = new Y_DividerBuilder().setBottomSideLine(true, 0xffffff, 6, 0, 0).
                        setBottomSideLine(true, 0xffffff, 20, 0, 0).
                        create();
                break;
        }

        return divider;
    }
}
