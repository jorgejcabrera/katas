package com.myaccount.tripservice

import com.myaccount.tripservice.exception.CollaboratorCallException

object TripDAO {
    fun findTripsByUser(user: User): List<Trip> {
        throw CollaboratorCallException(
            "TripDAO should not be invoked on an unit test."
        )
    }
}