package com.evajj.ktbase.binding.viewadapter.listview

import android.widget.AbsListView
import android.widget.AdapterView
import android.widget.ListView
import androidx.databinding.BindingAdapter
import com.evajj.ktbase.binding.command.BindingCommand
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.callbackFlow

/**
 * Author:wenjunjie
 * Date:2023/2/23
 * Time:下午4:47
 * Description:
 **/
@BindingAdapter(
    value = ["onScrollChangeCommand", "onScrollStateChangedCommand"],
    requireAll = false
)
fun onScrollChangeCommand(
    listView: ListView,
    onScrollChangeCommand: BindingCommand<ListViewScrollDataWrapper?>?,
    onScrollStateChangedCommand: BindingCommand<Int?>?
) {


    listView.setOnScrollListener(object : AbsListView.OnScrollListener {
        private var scrollState = 0
        override fun onScrollStateChanged(view: AbsListView, scrollState: Int) {
            this.scrollState = scrollState
            onScrollStateChangedCommand?.execute(scrollState)
        }

        override fun onScroll(
            view: AbsListView,
            firstVisibleItem: Int,
            visibleItemCount: Int,
            totalItemCount: Int
        ) {
            onScrollChangeCommand?.execute(
                ListViewScrollDataWrapper(
                    scrollState,
                    firstVisibleItem,
                    visibleItemCount,
                    totalItemCount
                )
            )
        }
    })
}


@BindingAdapter(value = ["onItemClickCommand"], requireAll = false)
fun onItemClickCommand(listView: ListView, onItemClickCommand: BindingCommand<Int?>?) {
    listView.onItemClickListener =
        AdapterView.OnItemClickListener { _, _, position, _ ->
            onItemClickCommand?.execute(
                position
            )
        }
}


@BindingAdapter("onLoadMoreCommand")
fun onLoadMoreCommand(listView: ListView, onLoadMoreCommand: BindingCommand<Int>) {
    listView.setOnScrollListener(
       OnScrollListener(
            listView,
            onLoadMoreCommand
        )
    )
}




