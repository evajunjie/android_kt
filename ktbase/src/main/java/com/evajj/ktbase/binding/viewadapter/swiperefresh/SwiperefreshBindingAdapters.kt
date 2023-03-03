package com.evajj.ktbase.binding.viewadapter.swiperefresh

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.evajj.ktbase.binding.command.BindingCommand

/**
 * Author:wenjunjie
 * Date:2023/2/28
 * Time:下午1:54
 * Description:
 **/
@BindingAdapter("onRefreshCommand")
fun onRefreshCommand(swipeRefreshLayout: SwipeRefreshLayout, onRefreshCommand: BindingCommand<Any?>) {
    swipeRefreshLayout.setOnRefreshListener{
        if (onRefreshCommand != null) {
            onRefreshCommand.execute()
        }
    }
}

//是否刷新中
@BindingAdapter("refreshing")
fun setRefreshing(swipeRefreshLayout: SwipeRefreshLayout, refreshing: Boolean) {
    swipeRefreshLayout.isRefreshing = refreshing
}