package com.evajj.ktbase.binding.viewadapter.radiogroup

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.databinding.BindingAdapter
import com.evajj.ktbase.binding.command.BindingCommand

/**
 * Author:wenjunjie
 * Date:2023/2/27
 * Time:下午5:05
 * Description:
 **/
@BindingAdapter(value = ["onCheckedChangedCommand"], requireAll = false)
fun onCheckedChangedCommand(radioGroup: RadioGroup, bindingCommand: BindingCommand<String?>) {
    radioGroup.setOnCheckedChangeListener { group, checkedId ->
        val radioButton = group.findViewById<View>(checkedId) as RadioButton
        bindingCommand.execute(radioButton.text.toString())
    }
}
