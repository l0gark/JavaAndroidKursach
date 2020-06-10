package com.loginov.hospital.main.ward.one

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.loginov.hospital.MessageViewModel
import com.loginov.hospital.api.person.PersonApi
import com.loginov.hospital.model.Person
import com.loginov.hospital.model.PersonDto

class WardViewModel(private val wardId: Long) : MessageViewModel() {
    private lateinit var personsData: MutableLiveData<List<Person>>

    fun getPersons(): LiveData<List<Person>> {
        if (!this::personsData.isInitialized) {
            personsData = MutableLiveData()
            loadPersons()
        }
        return personsData
    }

    private fun loadPersons() {

        PersonApi.getPersons(wardId) {
            personsData.postValue(it)
        }
//        personsData.postValue(
//            listOf(
//                Person("Ахмедов", "Абдулла", "Аркадьевич", Ward(name = "Палата 6"), Diagnosis(name = "Простатит")),
//                Person("Ахмедов", "Абдулла", "Аркадьевич", Ward(name = "Палата 6"), Diagnosis(name = "Простатит")),
//                Person("Ахмедов", "Абдулла", "Аркадьевич", Ward(name = "Палата 6"), Diagnosis(name = "Простатит")),
//                Person("Ахмедов", "Абдулла", "Аркадьевич", Ward(name = "Палата 6"), Diagnosis(name = "Простатит")),
//                Person("Ахмедов", "Абдулла", "Аркадьевич", Ward(name = "Палата 6"), Diagnosis(name = "Простатит"))
//            )
//        )
    }

    fun postPerson(person: PersonDto) {
        PersonApi.postPerson(person) { res ->
            if (res != null) {
                val persons = mutableListOf<Person>()
                persons.addAll(personsData.value ?: listOf())
                persons.add(res)
                personsData.postValue(persons)
            } else {
                showMessage("Палата уже заполнена!")
            }
        }
    }

    fun delete(id: Long) {
        PersonApi.delete(id) { isSuccessful ->
            if (isSuccessful) {
                val persons = personsData.value ?: mutableListOf()
                personsData.postValue(persons.filter { person -> person.id != id })
            }
        }
    }
}
