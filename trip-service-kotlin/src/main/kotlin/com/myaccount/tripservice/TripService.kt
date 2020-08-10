package com.myaccount.tripservice

import com.myaccount.tripservice.exception.UserNotLoggedInException


open class TripService(
    private val tripRepository: TripRepository,
    private val userSessionService: UserSessionService
) {

    @Throws(UserNotLoggedInException::class)
    fun getFriendTrips(user: User): List<Trip> {
        val loggedUser = userSessionService.getLoggedUser() ?: throw UserNotLoggedInException()
        if (loggedUser.areFriends(user)) {
            return tripRepository.findTripsByUser(user)
        }
        return emptyList()
    }
}