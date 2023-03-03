package com.evajj.ktbase.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.evajj.ktbase.util.LogUtil
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow

import java.lang.reflect.ParameterizedType

/**
 * Author:wenjunjie
 * Date:2023/2/6
 * Time:下午5:50
 * Description:
 **/
abstract class BaseActivity<V : ViewDataBinding, VM : BaseViewModel<*>> : AppCompatActivity(),
    IBaseView {


    private val binding: V by lazy(mode = LazyThreadSafetyMode.NONE) {
        DataBindingUtil.setContentView(this, initContentView())
    }

    private val viewModelId: Int by lazy(mode = LazyThreadSafetyMode.NONE) {
        initVariableId()
    }

    protected val viewModel: VM by lazy(mode = LazyThreadSafetyMode.NONE) {
        initViewModel() ?: createViewModel()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //页面接受的参数方法
        initParam()
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding()
        //私有的ViewModel与View的契约事件回调逻辑
        registorUIChangeLiveDataCallBack()
        //页面数据初始化方法
        initData()
        //页面事件监听的方法，一般用于ViewModel层转到View层的事件注册
        initViewObservable()
        //注册RxBus
        viewModel.registerRxBus()

    }

    fun initViewDataBinding() {
        binding?.apply {
            setVariable(viewModelId, viewModel)
            //支持LiveData绑定xml，数据改变，UI自动会更新
            lifecycleOwner = this@BaseActivity
        }
        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(viewModel)
        //注入RxLifecycle生命周期
        //viewModel.injectLifecycleProvider(this)
    }




    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(): Int

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    abstract fun initVariableId(): Int

    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    protected fun initViewModel(): VM? {

        return null
    }


    private fun createViewModel(): VM {
        val clazz = this.javaClass
        LogUtil.d("createViewModel+${clazz.canonicalName}")
        return clazz.run {
            val type = clazz.genericSuperclass
            val viewModelClazz: Class<*>
            when (type) {
                is ParameterizedType -> {

                    viewModelClazz = type.actualTypeArguments[1] as Class<*>
                    LogUtil.d("createViewModel1+${viewModelClazz.canonicalName}")

                }
                else -> {

                    viewModelClazz = BaseViewModel::class.java
                    LogUtil.d("createViewModel2+${viewModelClazz.canonicalName}")

                }
            }
            createViewModel(this@BaseActivity, viewModelClazz as Class<VM>)
        }

    }


    protected fun registorUIChangeLiveDataCallBack() = {}


    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
    </T> */
    open fun <T : ViewModel> createViewModel(activity: FragmentActivity, cls: Class<T>): T {
        LogUtil.d("initclass ${cls.canonicalName}")
        return ViewModelProvider(activity)[cls]
    }


}