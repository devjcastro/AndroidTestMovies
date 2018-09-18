package com.devjcastro.androidtestmovies.data.db

import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmQuery
import io.realm.RealmResults

class DBHelper {

    var realm: Realm? = null

    constructor(){
        realm = Realm.getDefaultInstance()
    }

    fun <T : RealmModel?>insertRow(item: T) {
        realm?.beginTransaction()
        realm?.insertOrUpdate(item)
        realm?.commitTransaction()
        close()
    }

    fun <T : RealmModel?>insertRows(items: List<T>) {
        realm?.beginTransaction()
        items.forEach {
            realm?.insertOrUpdate(it)
        }
        realm?.commitTransaction()
        close()
    }

    fun <T : RealmModel?>getRows(classType: Class<T>): RealmResults<T>? {
        val query: RealmQuery<T>? = realm?.where(classType)
        return query?.findAll()
    }

    fun <T: RealmModel?>getRowBy(id: Int, classType: Class<T>): RealmResults<T>? {
        val query: RealmQuery<T>? = realm?.where(classType)?.equalTo("id", id)
        return query?.findAll()
    }

    fun <T: RealmModel?>getRowBy(id: String, classType: Class<T>): RealmResults<T>? {
        val query: RealmQuery<T>? = realm?.where(classType)?.equalTo("id", id)
        return query?.findAll()
    }

    fun <T: RealmModel?>getRowsByStringColumn(column: String, valueColumn: String, classType: Class<T>): RealmResults<T>? {
        val query: RealmQuery<T>? = realm?.where(classType)?.equalTo(column, valueColumn)
        return query?.findAll()
    }

    fun <T: RealmModel?>getRowsByIntColumn(column: String, valueColumn: Int, classType: Class<T>): RealmResults<T>? {
        val query: RealmQuery<T>? = realm?.where(classType)?.equalTo(column, valueColumn)
        return query?.findAll()
    }

    fun <T: RealmModel?>getRowsByLongColumn(column: String, valueColumn: Long, classType: Class<T>): RealmResults<T>? {
        val query: RealmQuery<T>? = realm?.where(classType)?.equalTo(column, valueColumn)
        return query?.findAll()
    }

    fun <T: RealmModel?>delete(id: String, classType: Class<T>) {
        val entity = realm?.where(classType)?.equalTo("id", id)?.findAll()
        realm?.beginTransaction()
        entity?.deleteAllFromRealm()
        realm?.commitTransaction()
        close()
    }

    fun <T: RealmModel?>deleteAll(classType: Class<T>) {
        val entity = realm?.where(classType)?.findAll()
        realm?.beginTransaction()
        entity?.deleteAllFromRealm()
        realm?.commitTransaction()
        close()
    }


    fun <T: RealmModel?>isEmptyTable(classType: Class<T>, isCloseConnection: Boolean = false): Boolean {
        val isEmpty = realm?.where(classType)?.findAll()!!.isEmpty()

        if(isCloseConnection)
            close()

        return isEmpty
    }

    fun <T: RealmModel?>isNotEmptyTable(classType: Class<T>, isCloseConnection: Boolean = false): Boolean {
        val isNotEmpty = realm?.where(classType)?.findAll()!!.isNotEmpty()

        if(isCloseConnection)
            close()

        return isNotEmpty
    }

    fun close() {
        realm?.let {
            if (!it.isClosed) {
                it.close()
            }
        }
    }
}