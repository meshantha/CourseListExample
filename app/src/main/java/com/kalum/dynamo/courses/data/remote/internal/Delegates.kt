package com.kalum.dynamo.courses.data.remote.internal

/**
 * Created by Kalum Fernando on 12/10/2018.
 */

import kotlinx.coroutines.*

fun <T> lazyDeferred (block: suspend CoroutineScope.() -> T) : Lazy<Deferred<T>> {
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}