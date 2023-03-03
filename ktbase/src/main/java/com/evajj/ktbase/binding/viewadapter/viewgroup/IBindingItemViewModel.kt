package com.evajj.ktbase.binding.viewadapter.viewgroup

import androidx.databinding.ViewDataBinding

/**
 * Author:wenjunjie
 * Date:2023/2/28
 * Time:下午3:18
 * Description:
 **/
interface IBindingItemViewModel<V : ViewDataBinding> {
    fun injecDataBinding(binding: V)
}