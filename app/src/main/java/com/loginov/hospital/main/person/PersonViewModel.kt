package com.loginov.hospital.main.person

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loginov.hospital.api.person.PersonApi
import com.loginov.hospital.model.Diagnosis
import com.loginov.hospital.model.Person
import com.loginov.hospital.model.Ward

class PersonViewModel : ViewModel() {

    private lateinit var personsData: MutableLiveData<List<Person>>

    fun getPersons(): LiveData<List<Person>> {
        if (!this::personsData.isInitialized) {
            personsData = MutableLiveData()
            loadPersons()
        }
        return personsData
    }

    private fun loadPersons() {

        PersonApi.getAllPersons {
            personsData.postValue(it)
        }
    }


    fun deletePerson(id: Long) {
        PersonApi.delete(id) { isSuccessful ->
            if (isSuccessful) {
                val persons = personsData.value ?: mutableListOf()
                personsData.postValue(persons.filter { person -> person.id != id })
            }
        }
    }
}