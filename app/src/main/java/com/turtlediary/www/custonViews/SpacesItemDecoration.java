package com.turtlediary.www.custonViews;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by arvind on 1/11/17.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int mItemOffset;

    public SpacesItemDecoration(int itemOffset) {
        mItemOffset = itemOffset;
    }

    public SpacesItemDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
        this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
    }
}
