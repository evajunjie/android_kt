package com.evajj.dependences.buildsrc

/**
 * 项目相关参数配置
 *
 * @author wenjunjie
 * @since 4/24/21 5:56 PM
 */
object ProjectBuildConfig {
    const val compileSdkVersion = 33
    const val applicationId = "com.evajj.mvvm.base"
    const val minSdkVersion = 24
    const val targetSdkVersion = 32
    const val versionCode = 1
    const val versionName = "1.0"
    const val isAppMode = true

    /**
     * 项目当前的版本状态
     * 该状态直接反映当前App是测试版 还是正式版 或者预览版
     * 正式版:RELEASE、预览版(α)-内部测试版:ALPHA、测试版(β)-公开测试版:BETA
     */
    object Version {

        const val RELEASE = "VERSION_STATUS_RELEASE"

        const val ALPHA = "VERSION_STATUS_ALPHA"

        const val BETA = "VERSION_STATUS_BETA"
    }
}