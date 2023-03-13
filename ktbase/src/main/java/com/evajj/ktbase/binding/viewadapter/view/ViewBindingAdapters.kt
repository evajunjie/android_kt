package com.evajj.ktbase.binding.viewadapter.view

import android.graphics.Outline
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewOutlineProvider
import androidx.databinding.BindingAdapter
import com.evajj.ktbase.binding.command.BindingCommand
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.view.longClicks
import io.reactivex.functions.Consumer
import java.util.concurrent.TimeUnit

/**
 * Author:wenjunjie
 * Date:2023/2/28
 * Time:下午2:17
 * Description:
 **/
val CLICK_INTERVAL: Long = 1

/**
 * requireAll 是意思是是否需要绑定全部参数, false为否
 * View的onClick事件绑定
 * onClickCommand 绑定的命令,
 * isThrottleFirst 是否开启防止过快点击
 */
@BindingAdapter(value = ["onClickCommand", "isThrottleFirst"], requireAll = false)
fun onClickCommand(view: View?, clickCommand: BindingCommand<*>, isThrottleFirst: Boolean) {
    if (isThrottleFirst) {
        var result = view?.run {
            clicks().throttleFirst(
                CLICK_INTERVAL,
                TimeUnit.SECONDS
            ) //1秒钟内只允许点击1次
                .subscribe(Consumer<Any?> {
                    clickCommand.execute()
                })
        }


    } else {
        var result = view?.run {
            clicks()
                .subscribe(Consumer<Any?> {
                    clickCommand.execute()
                })
        }
    }
}


/**
 * view的onLongClick事件绑定
 */
@BindingAdapter(value = ["onLongClickCommand"], requireAll = false)
fun onLongClickCommand(view: View?, clickCommand: BindingCommand<Any?>) {
    view?.run {
        longClicks()
            .subscribe(Consumer<Any?> {
                clickCommand.execute()
            })
    }

}

/**
 * view的onLongClick事件绑定
 */
@BindingAdapter(value = ["onViewLongClickCommand"], requireAll = false)
fun onViewLongClickCommand(view: View?, clickCommand: BindingCommand<View?>?) {
    view?.run{
        longClicks()
            .subscribe(Consumer<Any?> { clickCommand?.execute(view) })

    }

}

/**
 * 回调控件本身
 *
 * @param currentView
 * @param bindingCommand
 */
@BindingAdapter(value = ["currentView"], requireAll = false)
fun replyCurrentView(currentView: View?, bindingCommand: BindingCommand<Any?>?) {
    bindingCommand?.execute(currentView)
}

/**
 * view是否需要获取焦点
 */
@BindingAdapter("requestFocus")
fun requestFocusCommand(view: View, needRequestFocus: Boolean) {
    if (needRequestFocus) {
        view.isFocusableInTouchMode = true
        view.requestFocus()
    } else {
        view.clearFocus()
    }
}

/**
 * view的焦点发生变化的事件绑定
 */
@BindingAdapter("onFocusChangeCommand")
fun onFocusChangeCommand(view: View, onFocusChangeCommand: BindingCommand<Boolean?>?) {
    view.onFocusChangeListener =  OnFocusChangeListener{ v, hasFocus -> onFocusChangeCommand?.execute(hasFocus) }
}

/**
 * view的焦点发生变化的事件绑定
 */
@BindingAdapter("viewProvider")
fun viewProvider(view: View, radius: Float) {
    view.clipToOutline = true
    view.outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View, outline: Outline) {
            outline.setRoundRect(0, 0, view.width, view.height, radius)
        }
    }
}

/**
 * view的显示隐藏
 */
@BindingAdapter(value = ["isVisible"], requireAll = false)
fun isVisible(view: View, visibility: Boolean) {
    if (visibility) {
        view.visibility = View.VISIBLE
    } else {
        view.visibility = View.GONE
    }
}