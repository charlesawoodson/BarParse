package com.charlesawoodson.barparse.contents.extensions

import androidx.fragment.app.Fragment
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Fragment argument delegate that makes it possible to set fragment args without
 * creating a key for each one.
 *
 * To create arguments, define a property in your fragment like:
 *     `private val listingId by arg<MyArgs>()`
 *
 * Each fragment can only have a single argument with the key [Mvi.KEY_ARG]
 */
fun <V : Any> args() = object : ReadOnlyProperty<Fragment, V> {
    var value: V? = null

    override fun getValue(thisRef: Fragment, property: KProperty<*>): V {
        if (value == null) {
            val args = thisRef.arguments ?: throw IllegalArgumentException("There are no fragment arguments!")
            val argUntyped = args.get(Mvi.KEY_ARG)
            argUntyped ?: throw IllegalArgumentException("MvRx arguments not found at key MvRx.KEY_ARG!")
            @Suppress("UNCHECKED_CAST")
            value = argUntyped as V
        }
        return value ?: throw IllegalArgumentException("")
    }
}

object Mvi {
    const val KEY_ARG = "mvi:arg"
}