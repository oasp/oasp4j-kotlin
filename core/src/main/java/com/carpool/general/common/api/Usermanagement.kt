package com.carpool.general.common.api

/**
 * Interface to get a user from its login.

 */
interface Usermanagement {

    /**
     * @param login The login of the requested user.
     * *
     * @return The [UserProfile] with the given `login` or `null` if no such object exists.
     */
    fun findUserProfileByLogin(login: String): UserProfile

}
