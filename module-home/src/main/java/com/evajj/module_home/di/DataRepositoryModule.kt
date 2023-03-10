package com.evajj.module_home.di

import com.evajj.ktnetwork.custom.HomeNetWorkApi
import com.evajj.module_home.data.source.HttpDataSource
import com.evajj.module_home.data.source.LocalDataSource
import com.evajj.module_home.data.source.http.HttpDataSourceImpl
import com.evajj.module_home.data.source.http.service.HomeServiceApi
import com.evajj.module_home.data.source.local.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Author:wenjunjie
 * Date:2023/2/21
 * Time:下午5:58
 * Description:
 **/
@Module
@InstallIn(SingletonComponent::class)
class DataRepositoryModule {
    @Provides
    @Singleton
    fun provideLocalSource () :LocalDataSource = LocalDataSourceImpl()

    @Provides
    @Singleton
    fun provideNetWorkSource () :HttpDataSource
        = HttpDataSourceImpl(HomeNetWorkApi.getService(HomeServiceApi::class.java))


}