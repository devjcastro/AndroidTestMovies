package com.intergrupo.pruebaintergrupo.base.mvp

abstract class BasePresenter<V>: IBasePresenter<V> {

    var view: V? = null

    override fun attach(view: V?) {
        this@BasePresenter.view = view
    }

    override fun detach() {
        this@BasePresenter.view = null
    }
}