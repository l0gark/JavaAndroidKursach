package com.loginov.hospital

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class MessageViewModel() : ViewModel() {
    private val messageData = MutableLiveData<String>()

    fun getSystemMessage(): LiveData<String> {
        return messageData
    }

    fun showMessage(text : String){
        messageData.postValue(text)
    }
}