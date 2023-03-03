package com.evajj.ktbase.binding.viewadapter.recyclerview

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemAnimator
import androidx.recyclerview.widget.RecyclerView.OnScrollListener
import com.evajj.ktbase.binding.command.BindingCommand
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * Author:wenjunjie
 * Date:2023/2/27
 * Time:下午5:28
 * Description:
 **/
@BindingAdapter("lineManager")
fun setLineManager(
    recyclerView: RecyclerView,
    lineManagerFactory: LineManagerFactory
) {
    recyclerView.addItemDecoration(lineManagerFactory.create(recyclerView))
}

@BindingAdapter("layoutManager")
fun setLayoutManager(recyclerView: RecyclerView, layoutManagerFactory: LayoutManagerFactory) {
    recyclerView.layoutManager = layoutManagerFactory.create(recyclerView)
}

@BindingAdapter(
    value = ["onScrollChangeCommand", "onScrollStateChangedCommand"],
    requireAll = false
)

fun onScrollChangeCommand(recyclerView: RecyclerView , onScrollChangeCommand :BindingCommand<ScrollDataWrapper>, onScrollStateChangedCommand :BindingCommand<Int>){
    recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
        private var state = 0

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            onScrollChangeCommand.execute(ScrollDataWrapper(dx.toFloat(), dy.toFloat(), state))
        }

        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            state = newState
            onScrollStateChangedCommand.execute(newState)
        }
    })
}

@BindingAdapter("onLoadMoreCommand")
fun onLoadMoreCommand(recyclerView: RecyclerView, onLoadMoreCommand: BindingCommand<Int>) {
    recyclerView.addOnScrollListener(OnScrollListener(onLoadMoreCommand))
}

@BindingAdapter("itemAnimator")
fun setItemAnimator(recyclerView: RecyclerView, animator: ItemAnimator?) {
    recyclerView.itemAnimator = animator
}

class ScrollDataWrapper (var scrollX:Float , var scrollY :Float,var state:Int  )


class OnScrollListener(onLoadMoreCommand: BindingCommand<Int>) :
    OnScrollListener() {
    private val methodInvoke: PublishSubject<Int> = PublishSubject.create()
    private val onLoadMoreCommand: BindingCommand<Int>?

    init {
        this.onLoadMoreCommand = onLoadMoreCommand
        methodInvoke.throttleFirst(1, TimeUnit.SECONDS)
            .subscribe(object : Consumer<Int> {
                @Throws(Exception::class)
                override fun accept(t: Int) {
                    onLoadMoreCommand.execute(t)

                }
            })
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        val layoutManager = recyclerView.layoutManager as LinearLayoutManager?
        val visibleItemCount = layoutManager!!.childCount
        val totalItemCount = layoutManager.itemCount
        val pastVisiblesItems = layoutManager.findFirstVisibleItemPosition()
        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
            if (onLoadMoreCommand != null) {
                methodInvoke.onNext(recyclerView.adapter!!.itemCount)
            }
        }
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
    }
}

