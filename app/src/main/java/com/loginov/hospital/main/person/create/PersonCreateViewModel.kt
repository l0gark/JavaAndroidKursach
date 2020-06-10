package com.loginov.hospital.main.person.create

import com.loginov.hospital.api.person.PersonApi
import com.loginov.hospital.main.diagnosis.DiagnosisViewModel
import com.loginov.hospital.model.PersonDto

class PersonCreateViewModel : DiagnosisViewModel() {

    fun postPerson(person: PersonDto, onComplete: () -> Unit) {
        PersonApi.postPerson(person) {
            onComplete()
        }
    }
}