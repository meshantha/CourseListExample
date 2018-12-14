package com.kalum.dynamo.courses.ui.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {

    private val mTitle = MutableLiveData<String>()

    private val mSubtitle = MutableLiveData<String>()

    val subtitle: LiveData<String>
        get() = mSubtitle

    val title: LiveData<String>
        get() = mTitle

    fun setTitle(title: String) {
        mTitle.postValue(title)
    }

    fun setSubtitle(subtitle: String) {
        mSubtitle.postValue(subtitle)
    }
}