package com.evajj.ktbase.binding.command

/**
 * Author:wenjunjie
 * Date:2023/2/23
 * Time:下午3:15
 * Description:
 **/
class BindingCommand<T> {
    private  var execute: (() -> Unit)? = null
    private  var consumer: (T.() -> Unit)? = null
    private  var canExecute0: (() -> Boolean)? = null

    constructor(execute: () -> Unit) {
        this.execute = execute
    }


    /**
     * @param consumer 带泛型参数的命令绑定
     */
    constructor(consumer: T.() -> Unit) {
        this.consumer = consumer
    }

    /**
     * @param execute     触发命令
     * @param canExecute0 true则执行,反之不执行
     */
    constructor(execute: () -> Unit, canExecute0: () -> Boolean) {
        this.execute = execute
        this.canExecute0 = canExecute0
    }


    /**
     * @param execute     带泛型参数触发命令
     * @param canExecute0 true则执行,反之不执行
     */
    constructor(consumer: (T) -> Unit,canExecute0: () -> Boolean) {
        this.consumer = consumer
        this.canExecute0 = canExecute0
    }

    /**
     * 执行BindingAction命令
     */
    fun execute(){
        var canExecute = canExecute0?.invoke()?:true

        if(canExecute) execute?.let { it() }

    }

    /**
     * 执行带泛型参数的命令
     *
     * @param parameter 泛型参数
     */
    open fun execute(parameter: T) {
        var canExecute = canExecute0?.invoke()?:true

        if(canExecute) consumer?.let { it(parameter) }
    }

}