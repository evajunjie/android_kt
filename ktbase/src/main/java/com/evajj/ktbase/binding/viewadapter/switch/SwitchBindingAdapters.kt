package com.evajj.ktbase.binding.viewadapter.switch

import android.widget.Switch
import androidx.databinding.BindingAdapter
import com.evajj.ktbase.binding.command.BindingCommand

/**
 * Author:wenjunjie
 * Date:2023/2/27
 * Time:下午5:01
 * Description:
 **/

/**
 * 设置开关状态
 *
 * @param mSwitch Switch控件
 */
@BindingAdapter("switchState")
fun setSwitchState(mSwitch: Switch, isChecked: Boolean) {
    mSwitch.isChecked = isChecked
}

/**
 * Switch的状态改变监听
 *
 * @param mSwitch        Switch控件
 * @param changeListener 事件绑定命令
 */
@BindingAdapter("onCheckedChangeCommand")
fun onCheckedChangeCommand(mSwitch: Switch, changeListener: BindingCommand<Boolean?>?) {
    changeListener?.let {
        mSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            it.execute(
                isChecked
            )
        }
    }
}