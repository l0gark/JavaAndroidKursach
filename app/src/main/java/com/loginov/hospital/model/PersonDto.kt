package com.loginov.hospital.model

data class PersonDto(
    val firstName: String,
    val lastName: String,
    val fatherName: String,
    val diagnosisName: String,
    var wardId: Long = 0
)
