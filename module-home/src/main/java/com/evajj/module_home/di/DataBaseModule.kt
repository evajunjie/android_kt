package com.evajj.module_home.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.evajj.module_home.data.HomeDataBase
import com.evajj.module_home.data.dao.HomeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Author:wenjunjie
 * Date:2023/3/13
 * Time:下午2:41
 * Description:
 **/
@Module
@InstallIn(SingletonComponent::class)
class DataBaseModule {

    //fallbackToDestructiveMigration 如果 Room 无法找到将设备上的现有数据库升级到当前版本的迁移路径，就会发生 IllegalStateException。
    // 在迁移路径缺失的情况下，如果丢失现有数据可以接受，请在创建数据库时调用 fallbackToDestructiveMigration
    //此方法会指示 Room 在需要执行没有定义迁移路径的增量迁移时，破坏性地重新创建应用的数据库表

    //如果特定版本的架构历史记录导致迁移路径出现无法解决的问题，请改用 fallbackToDestructiveMigrationFrom()。
    // 此方法表示您仅在从特定版本迁移时才希望 Room 回退到破坏性重新创建。

    //如果您仅在从较高数据库版本迁移到较低数据库版本时才希望 Room 回退到破坏性重新创建，
    // 请改用 fallbackToDestructiveMigrationOnDowngrade()。

    @Provides
    @Singleton
    fun provideHomeDataBase(@ApplicationContext applicationContext: Context): HomeDataBase = Room
        .databaseBuilder(applicationContext, HomeDataBase::class.java, "Home.db")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideHomeDao(database: HomeDataBase): HomeDao = database.getHomeDao()

}