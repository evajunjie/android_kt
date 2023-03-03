package com.evajj.ktbase.binding.viewadapter.viewgroup

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import me.tatarka.bindingcollectionadapter2.ItemBinding

/**
 * Author:wenjunjie
 * Date:2023/2/28
 * Time:下午3:19
 * Description:
 **/

@BindingAdapter("itemView", "observableList")
fun addViews(
    viewGroup: ViewGroup,
    itemBinding: ItemBinding<Any?>,
    viewModelList: ObservableList<IBindingItemViewModel<ViewDataBinding>>
) {
    if (viewModelList != null && !viewModelList.isEmpty()) {
        viewGroup.removeAllViews()
        for (viewModel in viewModelList) {
            val binding: ViewDataBinding = DataBindingUtil.inflate(
                LayoutInflater.from(viewGroup.context),
                itemBinding.layoutRes(), viewGroup, true
            )
            binding.setVariable(itemBinding.variableId(), viewModel)
            viewModel.injecDataBinding(binding)
        }
    }
}

