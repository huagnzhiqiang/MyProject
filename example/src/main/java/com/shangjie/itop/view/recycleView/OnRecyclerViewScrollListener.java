package com.shangjie.itop.view.recycleView;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

/**
 * @author 小强
 * @time 2018/11/13  10:46
 * @desc recycleView滑动监听
 */


import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.OnScrollListener;

public abstract class OnRecyclerViewScrollListener extends OnScrollListener {
    private String TAG = this.getClass().getSimpleName();
    protected OnRecyclerViewScrollListener.LayoutManagerType mLayoutManagerType;
    private int[] mLastPositions;
    private int mLastVisibleItemPosition;
    private static final int HIDE_THRESHOLD = 20;
    private int mDistance = 0;
    private boolean mIsScrollDown = true;
    private int mScrolledYDistance = 0;
    private int mScrolledXDistance = 0;
    private int preLoadNumber = 5;

    public OnRecyclerViewScrollListener() {
    }

    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int firstVisibleItemPosition = 0;
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        this.judgeLayoutManager(layoutManager);
        firstVisibleItemPosition = this.calculateFirstVisibleItemPos(layoutManager, firstVisibleItemPosition);
        this.calculateScrollUpOrDown(firstVisibleItemPosition, dy);
        this.mScrolledXDistance += dx;
        this.mScrolledYDistance += dy;
        this.mScrolledXDistance = this.mScrolledXDistance < 0 ? 0 : this.mScrolledXDistance;
        this.mScrolledYDistance = this.mScrolledYDistance < 0 ? 0 : this.mScrolledYDistance;
        this.onMoved(this.mScrolledXDistance, this.mScrolledYDistance);
    }

    private void judgeLayoutManager(LayoutManager layoutManager) {
        if (layoutManager instanceof LinearLayoutManager) {
            this.mLayoutManagerType = OnRecyclerViewScrollListener.LayoutManagerType.LINEAR;
        } else if (layoutManager instanceof GridLayoutManager) {
            this.mLayoutManagerType = OnRecyclerViewScrollListener.LayoutManagerType.GRID;
        } else {
            if (!(layoutManager instanceof StaggeredGridLayoutManager)) {
                throw new RuntimeException("Unsupported LayoutManager used. Valid ones are LinearLayoutManager, GridLayoutManager and StaggeredGridLayoutManager");
            }

            this.mLayoutManagerType = OnRecyclerViewScrollListener.LayoutManagerType.STAGGERED_GRID;
        }

    }

    private int calculateFirstVisibleItemPos(LayoutManager layoutManager, int firstVisibleItemPosition) {
        switch(this.mLayoutManagerType) {
            case LINEAR:
                this.mLastVisibleItemPosition = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
                firstVisibleItemPosition = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
                break;
            case GRID:
                this.mLastVisibleItemPosition = ((GridLayoutManager)layoutManager).findLastVisibleItemPosition();
                firstVisibleItemPosition = ((GridLayoutManager)layoutManager).findFirstVisibleItemPosition();
                break;
            case STAGGERED_GRID:
                StaggeredGridLayoutManager staggeredGridLayoutManager = (StaggeredGridLayoutManager)layoutManager;
                if (this.mLastPositions == null) {
                    this.mLastPositions = new int[staggeredGridLayoutManager.getSpanCount()];
                }

                this.mLastPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(this.mLastPositions);
                this.mLastVisibleItemPosition = this.findMax(this.mLastPositions);
                staggeredGridLayoutManager.findFirstCompletelyVisibleItemPositions(this.mLastPositions);
                firstVisibleItemPosition = this.findMax(this.mLastPositions);
        }

        return firstVisibleItemPosition;
    }

    private void calculateScrollUpOrDown(int firstVisibleItemPosition, int dy) {
        if (firstVisibleItemPosition == 0) {
            if (!this.mIsScrollDown) {
                this.onScrollDown();
                this.mIsScrollDown = true;
            }
        } else if (this.mDistance > 20 && this.mIsScrollDown) {
            this.onScrollUp();
            this.mIsScrollDown = false;
            this.mDistance = 0;
        } else if (this.mDistance < -20 && !this.mIsScrollDown) {
            this.onScrollDown();
            this.mIsScrollDown = true;
            this.mDistance = 0;
        }

        if (this.mIsScrollDown && dy > 0 || !this.mIsScrollDown && dy < 0) {
            this.mDistance += dy;
        }

    }

    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LayoutManager layoutManager = recyclerView.getLayoutManager();
        int visibleItemCount = layoutManager.getChildCount();
        int totalItemCount = layoutManager.getItemCount();
        if (visibleItemCount > 0 && newState == 0 && this.mLastVisibleItemPosition >= totalItemCount - this.preLoadNumber) {
            this.onBottom();
        }

    }

    public abstract void onScrollUp();

    public abstract void onScrollDown();

    public abstract void onBottom();

    public abstract void onMoved(int var1, int var2);

    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        int[] arr$ = lastPositions;
        int len$ = lastPositions.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            int value = arr$[i$];
            if (value > max) {
                max = value;
            }
        }

        return max;
    }

    private static enum LayoutManagerType {
        LINEAR,
        GRID,
        STAGGERED_GRID;

        private LayoutManagerType() {
        }
    }
}
