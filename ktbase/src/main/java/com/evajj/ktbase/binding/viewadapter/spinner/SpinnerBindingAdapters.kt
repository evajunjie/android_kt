package com.evajj.ktbase.binding.viewadapter.spinner

import android.R
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import com.evajj.ktbase.binding.command.BindingCommand

/**
 * Author:wenjunjie
 * Date:2023/2/28
 * Time:上午11:30
 * Description:
 **/

/**
 * 双向的SpinnerViewAdapter, 可以监听选中的条目,也可以回显选中的值
 *
 * @param spinner        控件本身
 * @param itemDatas      下拉条目的集合
 * @param valueReply     回显的value
 * @param bindingCommand 条目点击的监听
 */
@BindingAdapter(
    value = ["itemDatas", "valueReply", "resource", "dropDownResource", "onItemSelectedCommand"],
    requireAll = false
)
fun onItemSelectedCommand(
    spinner: Spinner,
    itemDatas: List<IKeyAndValue>,
    valueReply: String,
     resource: Int,
    dropDownResource: Int,
    bindingCommand: BindingCommand<IKeyAndValue>
) {
    var _resource = resource
    var _dropDownResource = dropDownResource

    checkNotNull(itemDatas) {"this itemDatas parameter is null"}
    val lists: MutableList<String?> = ArrayList()
    for (iKeyAndValue in itemDatas) {
        lists.add(iKeyAndValue.getKey())
    }
    if (resource == 0) {
        _resource = R.layout.simple_spinner_item
    }
    if (dropDownResource == 0) {
        _dropDownResource = R.layout.simple_spinner_dropdown_item
    }

    val adapter: ArrayAdapter<String?> = ArrayAdapter(spinner.context, resource, lists)
    adapter.setDropDownViewResource(_dropDownResource)
    spinner.adapter = adapter
    spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
            val iKeyAndValue = itemDatas[position]
            //将IKeyAndValue对象交给ViewModel
            bindingCommand.execute(iKeyAndValue)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }
    //回显选中的值
    if (!TextUtils.isEmpty(valueReply)) {
        for (i in itemDatas.indices) {
            val iKeyAndValue = itemDatas[i]
            if (valueReply == iKeyAndValue.getValue()) {
                spinner.setSelection(i)
                return
            }
        }
    }

}