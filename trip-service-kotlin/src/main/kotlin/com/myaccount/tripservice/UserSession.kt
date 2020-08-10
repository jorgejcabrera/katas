package com.myaccount.tripservice

import com.myaccount.tripservice.exception.CollaboratorCallException

class UserSession {

    fun getLoggedUser(): User? {
        throw CollaboratorCallException("UserSession.getLoggedUser() should not be called in an unit test")
    }

    companion object {
        fun getInstance(): UserSession {
            return UserSession()
        }
    }
}