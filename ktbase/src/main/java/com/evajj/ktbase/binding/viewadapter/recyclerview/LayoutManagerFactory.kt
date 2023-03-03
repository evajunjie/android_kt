package com.evajj.ktbase.binding.viewadapter.recyclerview

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

/**
 * Author:wenjunjie
 * Date:2023/2/27
 * Time:下午5:10
 * Description:
 **/
interface LayoutManagerFactory {
    fun create(recyclerView: RecyclerView): LayoutManager
}