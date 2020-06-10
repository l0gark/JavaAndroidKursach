package com.loginov.hospital.api.person

import com.loginov.hospital.api.CallbackImpl
import com.loginov.hospital.api.RetrofitClient
import com.loginov.hospital.api.diagnosis.DiagnosisService
import com.loginov.hospital.api.diagnosis.DiagnosisApi
import com.loginov.hospital.model.Person
import com.loginov.hospital.model.PersonDto
import okhttp3.ResponseBody

object PersonApi {
    private val tag = PersonApi::class.java.simpleName
    private val service = RetrofitClient.createService(PersonService::class.java)

    fun getAllPersons(onComplete: (List<Person>) -> Unit) {
        service.getAllPersons().enqueue(CallbackImpl<List<Person>>(tag + "__all") { res, _ ->
            onComplete(res ?: mutableListOf())
        })
    }

    fun getPersons(wardId: Long, onComplete: (List<Person>) -> Unit) {
        service.getAllPersons(wardId)
            .enqueue(CallbackImpl<List<Person>>(tag + "__ward_id") { res, _ ->
                onComplete(res ?: mutableListOf())
            })
    }

    fun postPerson(person: PersonDto, onComplete: (Person?) -> Unit) {
        service.createPerson(person).enqueue(CallbackImpl<Person>(tag + "__post") { res, e ->
            onComplete(res)
        })
    }

    fun delete(id: Long, onComplete: (Boolean) -> Unit) {
        service.deletePerson(id).enqueue(CallbackImpl<ResponseBody>(tag + "__delete") { _, e ->
            onComplete(e == null)
        })
    }
}