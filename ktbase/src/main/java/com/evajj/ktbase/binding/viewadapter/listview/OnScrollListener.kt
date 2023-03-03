package com.evajj.ktbase.binding.viewadapter.listview

import android.widget.AbsListView
import android.widget.ListView
import com.evajj.ktbase.binding.command.BindingCommand
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * Author:wenjunjie
 * Date:2023/2/23
 * Time:下午4:51
 * Description:
 **/
class OnScrollListener : AbsListView.OnScrollListener {
    private val methodInvoke = PublishSubject.create<Int>()
    var onLoadMoreCommand: BindingCommand<Int>? = null
    var listView: ListView? = null

    constructor(listView: ListView, onLoadMoreCommand: BindingCommand<Int>) {
        this.onLoadMoreCommand = onLoadMoreCommand
        this.listView = listView

        methodInvoke.throttleFirst(1, TimeUnit.SECONDS)
            .subscribe(object : Consumer<Int> {
                @Throws(Exception::class)
                override fun accept(integer: Int) {
                    onLoadMoreCommand.execute(integer)
                }
            })
    }

    override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {}

    override fun onScroll(
        view: AbsListView?,
        firstVisibleItem: Int,
        visibleItemCount: Int,
        totalItemCount: Int
    ) {
        if (firstVisibleItem + visibleItemCount >= totalItemCount && totalItemCount != 0 && (totalItemCount != listView!!.headerViewsCount
                    + listView!!.footerViewsCount)
        ) {
            if (onLoadMoreCommand != null) {
                 methodInvoke.onNext(totalItemCount)
            }
        }
    }
}