package com.evajj.ktbase.binding.viewadapter.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.evajj.ktbase.util.dp

/**
 * Author:wenjunjie
 * Date:2023/2/27
 * Time:下午9:31
 * Description:
 **/
class DividerSpace @JvmOverloads constructor(var context: Context, val mode :LineDrawMode = LineDrawMode.HORIZONTAL, var verticalDividerSize :Int = 1,var horizontalDividerSize :Int = 1) :  RecyclerView.ItemDecoration() {
    private val TAG = DividerLine::class.java.name

    //默认分隔线厚度为2dp
    private val DEFAULT_DIVIDER_SIZE = 1

    //控制分隔线的属性,值为一个drawable
    private val ATTRS = intArrayOf(android.R.attr.listDivider)


    //divider对应的drawable
    private var dividerDrawable: Drawable? = null

    private var mMode: LineDrawMode? = null


    //默认为null

    enum class LineDrawMode {
        HORIZONTAL, VERTICAL, BOTH
    }

    init {
        //获取样式中对应的属性值
        val attrArray = context.obtainStyledAttributes(ATTRS)
        dividerDrawable = attrArray.getDrawable(0)
        attrArray.recycle()
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        checkNotNull(mode) { "assign LineDrawMode,please!" }
        when (mode) {
            LineDrawMode.VERTICAL -> drawVertical(c, parent, state)
            LineDrawMode.HORIZONTAL -> drawHorizontal(c, parent, state)
            LineDrawMode.BOTH -> {
                drawHorizontal(c, parent, state)
                drawVertical(c, parent, state)
            }
        }
    }

    /**
     * 绘制垂直分隔线
     *
     * @param c
     * @param parent
     * @param state
     */
    private fun drawVertical(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child
                .layoutParams as RecyclerView.LayoutParams
            val top = child.top - params.topMargin
            val bottom = child.bottom + params.bottomMargin
            val left = child.right + params.rightMargin
            val right: Int = if (verticalDividerSize == 0) left +
                    DEFAULT_DIVIDER_SIZE.dp.toInt()
            else left + verticalDividerSize
            dividerDrawable?.setBounds(left, top, right, bottom)
            dividerDrawable?.draw(c)
        }
    }

    /**
     * 绘制水平分隔线
     *
     * @param c
     * @param parent
     * @param state
     */
    private fun drawHorizontal(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            //分别为每个item绘制分隔线,首先要计算出item的边缘在哪里,给分隔线定位,定界
            val child = parent.getChildAt(i)
            //RecyclerView的LayoutManager继承自ViewGroup,支持了margin
            val params = child.layoutParams as RecyclerView.LayoutParams
            //child的左边缘(也是分隔线的左边)
            val left = child.left - params.leftMargin
            //child的底边缘(恰好是分隔线的顶边)
            val top = child.bottom + params.topMargin
            //child的右边(也是分隔线的右边)
            val right = child.right - params.rightMargin
            //分隔线的底边所在的位置(那就是分隔线的顶边加上分隔线的高度)
            val bottom: Int = if (horizontalDividerSize == 0) top + DEFAULT_DIVIDER_SIZE.dp.toInt()
            else top + horizontalDividerSize
            dividerDrawable!!.setBounds(left, top, right, bottom)
            //画上去
            dividerDrawable!!.draw(c)
        }
    }
}