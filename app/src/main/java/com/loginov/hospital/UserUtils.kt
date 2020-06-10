package com.loginov.hospital

import okhttp3.Credentials

object UserUtils {
    var isAdmin = false
    private const val ADMIN_LOGIN = "admin"
    private const val ADMIN_PASS = "admin"
    private const val DOCTOR_LOGIN = "user"
    private const val DOCTOR_PASS = "user"

    var credentials =
        Credentials.basic(DOCTOR_LOGIN, DOCTOR_PASS)
        private set

    fun setDoctorRole() {
        credentials = Credentials.basic(DOCTOR_LOGIN, DOCTOR_PASS)
        isAdmin = false
    }

    fun setAdminRole() {
        credentials =
            Credentials.basic(ADMIN_LOGIN, ADMIN_PASS)
        isAdmin = true
    }

}