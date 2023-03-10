package com.evajj.module_home.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.evajj.ktbase.base.BaseActivity
import com.evajj.module_home.BR
import com.evajj.module_home.R
import com.evajj.module_home.databinding.HomeActivityLayoutBinding
import com.evajj.module_home.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeActivityLayoutBinding,HomeViewModel> (){


    override fun initContentView(): Int  = R.layout.home_activity_layout

    override fun initVariableId(): Int = BR.viewModel

    override fun initParam() {

    }

    override fun initData() {
        viewModel.loadData()
    }

    override fun initViewObservable() {
    }

}