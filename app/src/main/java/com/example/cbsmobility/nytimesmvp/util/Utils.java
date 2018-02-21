package com.example.cbsmobility.nytimesmvp.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;

import com.example.cbsmobility.nytimesmvp.R;

/**
 * Created by Vipin Vasu on 21/02/18.
 */

public class Utils {

    public final static RecyclerView.ItemDecoration getListDivider(Context context) {
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(context,
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(context, R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        return horizontalDecoration;
    }

}
