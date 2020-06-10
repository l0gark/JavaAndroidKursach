package com.loginov.hospital.api.person

import com.loginov.hospital.model.Person
import com.loginov.hospital.model.PersonDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface PersonService {

    @GET("/person/all")
    fun getAllPersons(): Call<List<Person>>

    @GET("/person/ward/{wardId}")
    fun getAllPersons(@Path("wardId") wardId : Long): Call<List<Person>>

    @POST("/person")
    fun createPerson(@Body person: PersonDto) : Call<Person>

    @DELETE("/person/{id}")
    fun deletePerson(@Path("id") id : Long) : Call<ResponseBody>
}