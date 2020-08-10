package com.myaccount

import com.myaccount.tripservice.*
import com.myaccount.tripservice.exception.UserNotLoggedInException
import com.nhaarman.mockitokotlin2.mock
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.assertj.core.api.BDDAssertions.then
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


class TripServiceTest {

    private val userSessionService: UserSessionService = mock()
    private val tripRepository: TripRepository = mock()
    private lateinit var tripService: TripService

    private val loggedUser = User()
    private val fried = User()

    @Before
    fun setUp() {
        tripService = TripService(tripRepository, userSessionService)
    }

    @Test
    fun `when an user has not a related friends then should return nothing`() {
        // given
        givenALoggedUserWithoutFriends()

        // when
        val friendTrips = tripService.getFriendTrips(fried)

        // then
        thenNoTrips(friendTrips)
    }

    @Test
    fun `when an user with trips has a related friend then it should return a list of trips`() {
        // given
        givenALoggedUserWithOneFried()
        givenSomeFriendTrips()

        // when
        val friendTrips = tripService.getFriendTrips(fried)

        // then
        thenTripsAreNotEmpty(friendTrips)
    }

    @Test
    fun `when a friend is the logged user then it should not return any trips`() {
        // given
        givenALoggedUserWithOneFried()
        givenSomeFriendTrips()

        // when
        val friendTrips = tripService.getFriendTrips(loggedUser)

        // then
        thenNoTrips(friendTrips)
    }

    @Test
    fun `when an user is not logged in then it should return an exception`() {
        // given
        val userNotLoggedIn = givenAnUSerNotLoggedIn()

        // then
        assertThatExceptionOfType(UserNotLoggedInException::class.java)
            .isThrownBy {
                tripService.getFriendTrips(userNotLoggedIn)
            }
    }

    private fun givenAnUSerNotLoggedIn(): User {
        `when`(userSessionService.getLoggedUser()).thenReturn(null)
        return User()
    }

    private fun thenTripsAreNotEmpty(friendTrips: List<Trip>) {
        then(friendTrips.isEmpty()).isFalse()
    }

    private fun createAnUserWithOneFriend(): User {
        loggedUser.addFriend(fried)
        return loggedUser
    }

    private fun createATripTo(city: String): Trip {
        return Trip(city)
    }

    private fun thenNoTrips(friendTrips: List<Trip>) {
        then(friendTrips.isEmpty()).isTrue()
    }

    private fun givenALoggedUserWithoutFriends(): User {
        `when`(userSessionService.getLoggedUser()).thenReturn(loggedUser)
        return loggedUser
    }


    private fun givenSomeFriendTrips() {
        val trips = listOf(createATripTo("Ibiza"))
        `when`(tripRepository.findTripsByUser(fried)).thenReturn(trips)
    }

    private fun givenALoggedUserWithOneFried(): User {
        val user = createAnUserWithOneFriend()
        `when`(userSessionService.getLoggedUser()).thenReturn(user)
        return user
    }
}