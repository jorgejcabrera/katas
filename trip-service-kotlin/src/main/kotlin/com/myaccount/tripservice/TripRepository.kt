package com.myaccount.tripservice

interface TripRepository {
    fun findTripsByUser(user: User): List<Trip>
}