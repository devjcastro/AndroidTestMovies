package com.devjcastro.androidtestmovies.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.ContextWrapper
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.os.Build
import android.text.TextUtils
import android.util.Log
import com.google.gson.GsonBuilder
import java.util.LinkedHashSet

object Prefs {

    private val DEFAULT_SUFFIX = "_preferences"
    private val LENGTH = "#LENGTH"
    private var mPrefs: SharedPreferences? = null

    /**
     * Returns the underlying SharedPreference instance
     *
     * @return an instance of the SharedPreference
     * @throws RuntimeException if SharedPreference instance has not been instantiated yet.
     */
    val preferences: SharedPreferences
        get() {
            if (mPrefs != null) {
                return mPrefs as SharedPreferences
            }
            throw RuntimeException(
                    "Prefs class not correctly instantiated. Please call Builder.setContext().build() in the Application class onCreate.")
        }

    /**
     * @return Returns a map containing a list of pairs key/value representing
     * the preferences.
     * @see SharedPreferences.getAll
     */
    val all: Map<String, *>
        get() = preferences.all

    /**
     * Initialize the Prefs helper class to keep a reference to the SharedPreference for this
     * application the SharedPreference will use the package name of the application as the Key.
     * This method is deprecated please us the new builder.
     *
     * @param context the Application context.
     */
    @Deprecated("")
    fun initPrefs(context: Context) {
        Builder().setContext(context).build()
    }

    private fun initPrefs(context: Context, prefsName: String, mode: Int) {
        mPrefs = context.getSharedPreferences(prefsName, mode)
    }

    /**
     * Retrieves a stored int value.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue.
     * @throws ClassCastException if there is a preference with this name that is not
     * an int.
     * @see SharedPreferences.getInt
     */
    fun getInt(key: String, defaultValue: Int): Int {
        return preferences.getInt(key, defaultValue)
    }

    /**
     * Retrieves a stored boolean value.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue.
     * @throws ClassCastException if there is a preference with this name that is not a boolean.
     * @see SharedPreferences.getBoolean
     */
    fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return preferences.getBoolean(key, defaultValue)
    }

    /**
     * Retrieves a stored long value.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue.
     * @throws ClassCastException if there is a preference with this name that is not a long.
     * @see SharedPreferences.getLong
     */
    fun getLong(key: String, defaultValue: Long): Long {
        return preferences.getLong(key, defaultValue)
    }

    /**
     * Returns the double that has been saved as a long raw bits value in the long preferences.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue the double Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue.
     * @throws ClassCastException if there is a preference with this name that is not a long.
     * @see SharedPreferences.getLong
     */
    fun getDouble(key: String, defaultValue: Double): Double {
        return java.lang.Double.longBitsToDouble(preferences.getLong(key, java.lang.Double.doubleToLongBits(defaultValue)))
    }

    /**
     * Retrieves a stored float value.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue.
     * @throws ClassCastException if there is a preference with this name that is not a float.
     * @see SharedPreferences.getFloat
     */
    fun getFloat(key: String, defaultValue: Float): Float {
        return preferences.getFloat(key, defaultValue)
    }

    /**
     * Retrieves a stored String value.
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValue.
     * @throws ClassCastException if there is a preference with this name that is not a String.
     * @see SharedPreferences.getString
     */
    fun getString(key: String, defaultValue: String): String? {
        return preferences.getString(key, defaultValue)
    }

    /**
     * Retrieves a Set of Strings as stored by [.putStringSet]. On Honeycomb and
     * later this will call the native implementation in SharedPreferences, on older SDKs this will
     * call [.getOrderedStringSet].
     * **Note that the native implementation of [SharedPreferences.getStringSet] does not reliably preserve the order of the Strings in the Set.**
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference values if they exist, or defaultValues otherwise.
     * @throws ClassCastException if there is a preference with this name that is not a Set.
     * @see SharedPreferences.getStringSet
     * @see .getOrderedStringSet
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun getStringSet(key: String, defaultValue: Set<String>): Set<String>? {
        val prefs = preferences
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            prefs.getStringSet(key, defaultValue)
        } else {
            // Workaround for pre-HC's missing getStringSet
            getOrderedStringSet(key, defaultValue)
        }
    }

    /**
     * Retrieves a Set of Strings as stored by [.putOrderedStringSet],
     * preserving the original order. Note that this implementation is heavier than the native
     * [.getStringSet] method (which does not guarantee to preserve order).
     *
     * @param key          The name of the preference to retrieve.
     * @param defaultValue Value to return if this preference does not exist.
     * @return Returns the preference value if it exists, or defaultValues otherwise.
     * @throws ClassCastException if there is a preference with this name that is not a Set of
     * Strings.
     * @see .getStringSet
     */
    fun getOrderedStringSet(key: String, defaultValue: Set<String>): Set<String> {
        val prefs = preferences
        if (prefs.contains(key + LENGTH)) {
            val set = LinkedHashSet<String>()
            val stringSetLength = prefs.getInt(key + LENGTH, -1)
            if (stringSetLength >= 0) {
                for (i in 0 until stringSetLength) {
                    set.add(prefs.getString("$key[$i]", null))
                }
            }
            return set
        }
        return defaultValue
    }

    /**
     * Stores a long value.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @see Editor.putLong
     */
    fun putLong(key: String, value: Long) {
        val editor = preferences.edit()
        editor.putLong(key, value)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    /**
     * Stores an integer value.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @see Editor.putInt
     */
    fun putInt(key: String, value: Int) {
        val editor = preferences.edit()
        editor.putInt(key, value)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    /**
     * Converts an object to String using Gson, and then saves to preferences.
     *
     * @param key         The name of the preference to modify.
     * @param valueObject The object (value) to be serialized and saved in preferences.
     */

    fun putObjectGson(key: String, valueObject: Any) {
        val editor = preferences.edit()
        val gson = GsonBuilder().create()
        val value = gson.toJson(valueObject)
        Log.i("Prefs", "putGsonString: key = $key value = $value")

        editor.putString(key, value)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    fun getObjectGson(key: String, defaultValue: String, objectClass: Class<*>): Any? {
        val gson = GsonBuilder().create()
        var `object`: Any? = null
        val serializedString = getString(key, defaultValue)

        if (serializedString != defaultValue) {
            `object` = gson.fromJson(serializedString, objectClass)
        }
        return `object`
    }

    /**
     * Stores a double value as a long raw bits value.
     *
     * @param key   The name of the preference to modify.
     * @param value The double value to be save in the preferences.
     * @see Editor.putLong
     */
    fun putDouble(key: String, value: Double) {
        val editor = preferences.edit()
        editor.putLong(key, java.lang.Double.doubleToRawLongBits(value))
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    /**
     * Stores a float value.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @see Editor.putFloat
     */
    fun putFloat(key: String, value: Float) {
        val editor = preferences.edit()
        editor.putFloat(key, value)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    /**
     * Stores a boolean value.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @see Editor.putBoolean
     */
    fun putBoolean(key: String, value: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(key, value)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    /**
     * Stores a String value.
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @see Editor.putString
     */
    fun putString(key: String, value: String) {
        val editor = preferences.edit()
        editor.putString(key, value)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    /**
     * Stores a Set of Strings. On Honeycomb and later this will call the native implementation in
     * SharedPreferences.Editor, on older SDKs this will call [.putOrderedStringSet].
     * **Note that the native implementation of [Editor.putStringSet] does not reliably preserve the order of the Strings in the Set.**
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @see Editor.putStringSet
     * @see .putOrderedStringSet
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    fun putStringSet(key: String, value: Set<String>) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            val editor = preferences.edit()
            editor.putStringSet(key, value)
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
                editor.commit()
            } else {
                editor.apply()
            }
        } else {
            // Workaround for pre-HC's lack of StringSets
            putOrderedStringSet(key, value)
        }
    }

    /**
     * Stores a Set of Strings, preserving the order.
     * Note that this method is heavier that the native implementation [.putStringSet] (which does not reliably preserve the order of the Set). To preserve the order of the
     * items in the Set, the Set implementation must be one that as an iterator with predictable
     * order, such as [LinkedHashSet].
     *
     * @param key   The name of the preference to modify.
     * @param value The new value for the preference.
     * @see .putStringSet
     * @see .getOrderedStringSet
     */
    fun putOrderedStringSet(key: String, value: Set<String>) {
        val editor = preferences.edit()
        var stringSetLength = 0
        if (mPrefs!!.contains(key + LENGTH)) {
            // First read what the value was
            stringSetLength = mPrefs!!.getInt(key + LENGTH, -1)
        }
        editor.putInt(key + LENGTH, value.size)
        var i = 0
        for (aValue in value) {
            editor.putString("$key[$i]", aValue)
            i++
        }
        while (i < stringSetLength) {
            // Remove any remaining values
            editor.remove("$key[$i]")
            i++
        }
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    /**
     * Removes a preference value.
     *
     * @param key The name of the preference to remove.
     * @see Editor.remove
     */
    fun remove(key: String) {
        val prefs = preferences
        val editor = prefs.edit()
        if (prefs.contains(key + LENGTH)) {
            // Workaround for pre-HC's lack of StringSets
            val stringSetLength = prefs.getInt(key + LENGTH, -1)
            if (stringSetLength >= 0) {
                editor.remove(key + LENGTH)
                for (i in 0 until stringSetLength) {
                    editor.remove("$key[$i]")
                }
            }
        }
        editor.remove(key)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            editor.commit()
        } else {
            editor.apply()
        }
    }

    /**
     * Checks if a value is stored for the given key.
     *
     * @param key The name of the preference to check.
     * @return `true` if the storage contains this key value, `false` otherwise.
     * @see SharedPreferences.contains
     */
    operator fun contains(key: String): Boolean {
        return preferences.contains(key)
    }

    /**
     * Removed all the stored keys and values.
     *
     * @return the [Editor] for chaining. The changes have already been committed/applied
     * through the execution of this method.
     * @see Editor.clear
     */
    fun clear(): Editor {
        val editor = preferences.edit().clear()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            editor.commit()
        } else {
            editor.apply()
        }
        return editor
    }

    /**
     * Returns the Editor of the underlying SharedPreferences instance.
     *
     * @return An Editor
     */
    fun edit(): Editor {
        return preferences.edit()
    }

    /**
     * Builder class for the EasyPrefs instance. You only have to call this once in the Application
     * onCreate. And in the rest of the code base you can call Prefs.method name.
     */
    class Builder {

        private var mKey: String? = null
        private var mContext: Context? = null
        private var mMode = -1
        private var mUseDefault = false

        fun setPrefsName(prefsName: String): Builder {
            mKey = prefsName
            return this
        }

        fun setContext(context: Context): Builder {
            mContext = context
            return this
        }

        fun setMode(mode: Int): Builder {
            if (mode == ContextWrapper.MODE_PRIVATE || mode == ContextWrapper.MODE_WORLD_READABLE || mode == ContextWrapper.MODE_WORLD_WRITEABLE || mode == ContextWrapper.MODE_MULTI_PROCESS) {
                mMode = mode
            } else {
                throw RuntimeException("The mode in the SharedPreference can only be set too ContextWrapper.MODE_PRIVATE, ContextWrapper.MODE_WORLD_READABLE, ContextWrapper.MODE_WORLD_WRITEABLE or ContextWrapper.MODE_MULTI_PROCESS")
            }

            return this
        }

        fun setUseDefaultSharedPreference(defaultSharedPreference: Boolean): Builder {
            mUseDefault = defaultSharedPreference
            return this
        }

        /**
         * Initialize the SharedPreference instance to used in the application.
         *
         * @throws RuntimeException if Context has not been set.
         */
        fun build() {
            if (mContext == null) {
                throw RuntimeException("Context not set, please set context before building the Prefs instance.")
            }

            if (TextUtils.isEmpty(mKey)) {
                mKey = mContext!!.packageName
            }

            if (mUseDefault) {
                mKey += DEFAULT_SUFFIX
            }

            if (mMode == -1) {
                mMode = ContextWrapper.MODE_PRIVATE
            }

            Prefs.initPrefs(mContext!!, mKey!!, mMode)
        }
    }
}