package com.intergrupo.pruebaintergrupo.base.mvp

interface IBasePresenter<V> {
    fun attach(view: V?)
    fun detach()
}