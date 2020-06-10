package com.loginov.hospital.main.ward.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loginov.hospital.MessageViewModel
import com.loginov.hospital.api.ward.WardApi
import com.loginov.hospital.model.Ward

class WardListViewModel : MessageViewModel() {

    private lateinit var wardsData: MutableLiveData<List<Ward>>

    fun getWards(): LiveData<List<Ward>> {
        if (!this::wardsData.isInitialized) {
            wardsData = MutableLiveData()
            loadWards()
        }
        return wardsData
    }

    private fun loadWards() {
        WardApi.getAllWards {
            wardsData.postValue(it)
        }
//
//        wardsData.postValue(
//            listOf(
//                Ward(1, "Палата 1", 3),
//                Ward(1, "Палата 2", 5),
//                Ward(1, "Палата 3", 2),
//                Ward(1, "Палата 4", 6),
//                Ward(1, "Палата 5", 1),
//                Ward(1, "Палата 6", 2),
//                Ward(1, "Палата 7", 4),
//                Ward(1, "Палата 8", 5),
//                Ward(1, "Палата 9", 2)
//            )
//        )
    }

    fun postWard(ward: Ward) {
        Log.d("MY_TAG", "ward created - $ward")

        WardApi.postWard(ward) { res ->
            if (res != null) {
                val wards = mutableListOf<Ward>()
                wards.addAll(wardsData.value ?: listOf())
                wards.add(res)
                wardsData.postValue(wards)
            } else {
                showMessage("Ошибка, попробуйте снова")
            }
        }
    }
}
