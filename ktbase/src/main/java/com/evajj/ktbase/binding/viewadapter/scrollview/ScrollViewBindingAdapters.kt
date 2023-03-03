package com.evajj.ktbase.binding.viewadapter.scrollview

import android.widget.ScrollView
import androidx.core.widget.NestedScrollView
import androidx.databinding.BindingAdapter
import com.evajj.ktbase.binding.command.BindingCommand
import com.evajj.ktbase.binding.viewadapter.recyclerview.ScrollDataWrapper

/**
 * Author:wenjunjie
 * Date:2023/2/28
 * Time:上午10:26
 * Description:
 **/
@BindingAdapter("onScrollChangeCommand")
fun onScrollChangeCommand(nestedScrollView: NestedScrollView,onScrollChangeCommand:BindingCommand<NestScrollDataWrapper>){
    nestedScrollView.setOnScrollChangeListener { _, scrollX, scrollY, oldScrollX, oldScrollY ->
        onScrollChangeCommand.execute(NestScrollDataWrapper(scrollX,scrollY,oldScrollX,oldScrollY))
    }
}

@BindingAdapter("onScrollChangeCommand")
fun onScrollChangeCommand(
    scrollView: ScrollView,
    onScrollChangeCommand: BindingCommand<ScrollViewDataWrapper>
) {
    scrollView.viewTreeObserver.addOnScrollChangedListener {
        onScrollChangeCommand?.execute(
            ScrollViewDataWrapper(scrollView.scrollX.toFloat(), scrollView.scrollY.toFloat())
        )
    }
}
class ScrollViewDataWrapper(var scrollX: Float, var scrollY: Float)

class NestScrollDataWrapper(
    var scrollX: Int,
    var scrollY: Int,
    var oldScrollX: Int,
    var oldScrollY: Int
)