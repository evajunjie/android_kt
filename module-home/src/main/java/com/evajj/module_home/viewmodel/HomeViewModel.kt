package com.evajj.module_home.viewmodel

import android.app.Application
import com.evajj.ktbase.base.BaseViewModel
import com.evajj.module_home.model.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Author:wenjunjie
 * Date:2023/2/22
 * Time:上午11:33
 * Description:
 **/
@HiltViewModel
class HomeViewModel @Inject constructor(application: Application ,private val homeRepository: HomeRepository) :BaseViewModel<HomeRepository>(application ,homeRepository) {
      val test = "1111"
}