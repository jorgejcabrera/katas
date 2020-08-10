package com.myaccount.tripservice


class User {
    private val trips = mutableListOf<Trip>()
    private val friends = mutableListOf<User>()

    fun getFriends(): List<User> {
        return friends
    }

    fun addFriend(user: User) {
        friends.add(user)
    }

    fun addTrip(trip: Trip) {
        trips.add(trip)
    }

    fun trips(): List<Trip>? {
        return trips
    }

    fun areFriends(user: User): Boolean {
        return this.friends.contains(user)
    }
}