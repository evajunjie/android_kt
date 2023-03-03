package com.evajj.ktbase.binding.viewadapter.recyclerview

import androidx.annotation.IntDef
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

/**
 * Author:wenjunjie
 * Date:2023/2/27
 * Time:下午5:13
 * Description:
 **/
object LayoutManagers {

    @IntDef(LinearLayoutManager.HORIZONTAL, LinearLayoutManager.VERTICAL)
    @Retention(
        RetentionPolicy.SOURCE
    )
    annotation class Orientation

    /**
     * A {@link LinearLayoutManager}.
     * make recyclerView cannot scroll
     */
    fun staticLinear(@LayoutManagers.Orientation orientation : Int) = object : LayoutManagerFactory{
        override fun create(recyclerView: RecyclerView): RecyclerView.LayoutManager  = object  : LinearLayoutManager(recyclerView.context,orientation,false){
            override fun canScrollHorizontally(): Boolean {
                return false
            }

            override fun canScrollVertically(): Boolean {
                return false
            }
        }

    }


    /**
     * A [LinearLayoutManager].
     */
    fun linear(): LayoutManagerFactory {
        return object : LayoutManagerFactory {
            override fun create(recyclerView: RecyclerView): LayoutManager {
                return LinearLayoutManager(recyclerView.context)
            }
        }
    }


    /**
     * A [LinearLayoutManager] with the given orientation and reverseLayout.
     */
    fun linear(@Orientation orientation: Int, reverseLayout: Boolean): LayoutManagerFactory {
        return object : LayoutManagerFactory {
            override fun create(recyclerView: RecyclerView): LayoutManager {
                return LinearLayoutManager(recyclerView.context, orientation, reverseLayout)
            }
        }
    }

    /**
     * A [GridLayoutManager] with the given spanCount.
     */
    fun grid(spanCount: Int): LayoutManagerFactory {
        return object : LayoutManagerFactory {
            override fun create(recyclerView: RecyclerView): LayoutManager {
                return GridLayoutManager(recyclerView.context, spanCount)
            }
        }
    }

    /**
     * A [GridLayoutManager] with the given spanCount, orientation and reverseLayout.
     */
    fun grid(
        spanCount: Int,
        @Orientation orientation: Int,
        reverseLayout: Boolean
    ): LayoutManagerFactory {
        return object : LayoutManagerFactory {
            override fun create(recyclerView: RecyclerView): LayoutManager {
                return GridLayoutManager(
                    recyclerView.context,
                    spanCount,
                    orientation,
                    reverseLayout
                )
            }
        }
    }

    /**
     * A [StaggeredGridLayoutManager] with the given spanCount and orientation.
     */
    fun staggeredGrid(spanCount: Int, @Orientation orientation: Int): LayoutManagerFactory {
        return object : LayoutManagerFactory {
            override fun create(recyclerView: RecyclerView): LayoutManager {
                return StaggeredGridLayoutManager(spanCount, orientation)
            }
        }
    }
}