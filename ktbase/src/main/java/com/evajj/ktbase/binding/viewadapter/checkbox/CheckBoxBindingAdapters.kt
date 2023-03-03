package com.evajj.ktbase.binding.viewadapter.checkbox

import android.widget.CheckBox
import androidx.databinding.BindingAdapter
import com.evajj.ktbase.binding.command.BindingCommand

/**
 * Author:wenjunjie
 * Date:2023/2/23
 * Time:下午4:00
 * Description:
 **/
@BindingAdapter(value = ["onCheckedChangedCommand"], requireAll = false)
fun setCheckedChanged(checkBox: CheckBox, bindingCommand: BindingCommand<Boolean?>) {
    checkBox.setOnCheckedChangeListener { compoundButton, isChecked -> bindingCommand.execute(isChecked) }
}