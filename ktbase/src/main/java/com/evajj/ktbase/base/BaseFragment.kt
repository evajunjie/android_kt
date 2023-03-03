package com.evajj.ktbase.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import java.lang.reflect.ParameterizedType

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午1:57
 * Description:
 **/
abstract class BaseFragment<V : ViewDataBinding,VM : BaseViewModel<*>> : Fragment(), IBaseView {
    private lateinit var binding: V

    private val viewModelId: Int by lazy(mode = LazyThreadSafetyMode.NONE) {
        initVariableId()
    }

    protected val viewModel: VM by lazy(mode = LazyThreadSafetyMode.NONE) {
        initViewModel() ?: createViewModel()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initParam()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            initContentView(inflater, container, savedInstanceState),
            container,
            false
        )
        return binding.root
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding()
        //私有的ViewModel与View的契约事件回调逻辑
       // registorUIChangeLiveDataCallBack()
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
            lifecycleOwner = this.lifecycleOwner
        }

        //让ViewModel拥有View的生命周期感应
        lifecycle.addObserver(viewModel)
        //注入RxLifecycle生命周期
        //viewModel.injectLifecycleProvider(this)
    }


    override fun initParam() {
    }

    override fun initData() {
    }

    override fun initViewObservable() {
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    abstract fun initContentView(
        inflater: LayoutInflater?,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): Int

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
    fun initViewModel(): VM? {
        return null
    }

    private fun createViewModel(): VM {
        val clazz = this@BaseFragment.javaClass
        return clazz.run {
            val type = clazz.genericSuperclass
            val viewModelClazz: Class<*>
            when (type) {
                is ParameterizedType -> {
                    viewModelClazz = type.actualTypeArguments[0] as Class<*>
                }
                else -> {
                    viewModelClazz = BaseViewModel::class.java
                }
            }
            createViewModel(this@BaseFragment, viewModelClazz as Class<VM>)
        }

    }

    /**
     * 创建ViewModel
     *
     * @param cls
     * @param <T>
     * @return
    </T> */
    open fun <T : ViewModel> createViewModel(fragment: Fragment, cls: Class<T>): T {
        return ViewModelProvider(fragment)[cls]
    }
}