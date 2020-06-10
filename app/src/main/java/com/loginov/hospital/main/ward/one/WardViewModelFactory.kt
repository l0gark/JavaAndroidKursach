package com.loginov.hospital.main.ward.one

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.loginov.hospital.model.Diagnosis
import com.loginov.hospital.model.Person
import com.loginov.hospital.model.Ward

class WardViewModelFactory(private val wardId : Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(Long::class.java)
            .newInstance(wardId)
    }

}
