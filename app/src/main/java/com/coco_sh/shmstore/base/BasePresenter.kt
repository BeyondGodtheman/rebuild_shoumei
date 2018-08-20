package com.coco_sh.shmstore.base

import io.reactivex.disposables.CompositeDisposable

/**
 * Presenter父类
 * Created by zhangye on 2018/8/2.
 */
abstract class BasePresenter<T:IBaseView>(var view: T?) {

    val composites = CompositeDisposable()

    abstract fun onCreate()

    abstract fun onDistroy()

}