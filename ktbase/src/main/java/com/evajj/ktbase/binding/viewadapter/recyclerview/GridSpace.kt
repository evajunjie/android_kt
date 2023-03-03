package com.evajj.ktbase.binding.viewadapter.recyclerview

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.evajj.ktbase.util.LogUtil

/**
 * Author:wenjunjie
 * Date:2023/2/28
 * Time:上午9:32
 * Description:
 **/
class GridSpace (var mSpanCount :Int ,var mRowSpacing :Int ,var mColumnSpacing :Int) : ItemDecoration() {
    private val TAG :String = GridSpace::class.java.name

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view) // 获取view 在adapter中的位置。
        val column = position % mSpanCount // view 所在的列
        outRect.left = column * mColumnSpacing / mSpanCount // column * (列间距 * (1f / 列数))
        outRect.right =
            mColumnSpacing - (column + 1) * mColumnSpacing / mSpanCount // 列间距 - (column + 1) * (列间距 * (1f /列数))
        LogUtil.e(
            TAG, "position:" + position
                    + "    columnIndex: " + column
                    + "    left,right ->" + outRect.left + "," + outRect.right
        )

        // 如果position > 行数，说明不是在第一行，则不指定行高，其他行的上间距为 top=mRowSpacing
        if (position >= mSpanCount) {
            outRect.top = mRowSpacing // item top
        }
    }

}