package com.loginov.hospital.model

fun Person.fullName(): String {
    return "$firstName $lastName $fatherName"
}

data class Person(
    val id: Long = 0,
    val firstName: String,
    val lastName: String,
    val fatherName: String,
    val ward: Ward,
    val diagnosis: Diagnosis
)