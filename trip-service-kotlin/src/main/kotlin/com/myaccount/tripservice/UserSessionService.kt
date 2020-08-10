package com.myaccount.tripservice

interface UserSessionService {
    fun getLoggedUser(): User?
}