package com.evajj.ktbase.base

/**
 * Author:wenjunjie
 * Date:2023/2/7
 * Time:上午9:48
 * Description:
 **/
interface IBaseView {
    /**
     * 初始化界面传递参数
     */
    fun initParam()

    /**
     * 初始化数据
     */
    fun initData()

    /**
     * 初始化界面观察者的监听
     */
    fun initViewObservable()
}