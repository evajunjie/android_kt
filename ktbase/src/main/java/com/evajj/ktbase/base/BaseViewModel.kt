package com.evajj.ktbase.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * Author:wenjunjie
 * Date:2023/2/6
 * Time:下午5:55
 * Description:
 **/
open class BaseViewModel <M : BaseModel>(application: Application) : AndroidViewModel(application),IBaseViewModel {
    protected lateinit var model : M ;


    constructor(application: Application,model: M) : this(application) {
        this.model = model
    }
    override fun onCleared() {
        super.onCleared()

    }

    override fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event?) {

    }

    override fun onCreate() {
    }

    override fun onDestroy() {
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    override fun registerRxBus() {
    }

    override fun removeRxBus() {
    }



}