package com.carpool.general.common.api.security

import com.carpool.general.common.api.UserProfile
import com.carpool.general.common.api.to.UserDetailsClientTo

import java.security.Principal

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User

/**
 * Container class for the profile of a user.

 */
class UserData : User, Principal {

    /**
     * @return userProfile
     */
    /**
     * @param userProfile the userProfile to set
     */
    var userProfile: UserProfile? = null

    /**
     * The constructor.

     * @param username sets the username
     * *
     * @param password sets the password
     * *
     * @param enabled check if user is enabled
     * *
     * @param accountNonExpired check if user account is not expired
     * *
     * @param credentialsNonExpired check if user credentials are not expired
     * *
     * @param accountNonLocked check if user account is not locked
     * *
     * @param authorities the authorities/permissions the user has
     */
    constructor(username: String, password: String, enabled: Boolean, accountNonExpired: Boolean,
                credentialsNonExpired: Boolean, accountNonLocked: Boolean, authorities: Collection<GrantedAuthority>) : super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities) {
    }

    /**
     * The constructor.

     * @param username sets the username
     * *
     * @param password sets the password
     * *
     * @param authorities the authorities/permissions the user has
     */
    constructor(username: String, password: String, authorities: Collection<GrantedAuthority>) : super(username, password, authorities) {
    }

    override fun getName(): String {

        return username
    }

    /**
     * @return an instance of [UserDetailsClientTo] with the client side representation of this [UserData]
     * *         instance.
     */
    fun toClientTo(): UserDetailsClientTo {

        val clientTo = UserDetailsClientTo()
        clientTo.id = this.userProfile!!.id
        clientTo.name = this.userProfile!!.name
        clientTo.firstName = this.userProfile!!.firstName
        clientTo.lastName = this.userProfile!!.lastName
        clientTo.role = this.userProfile!!.role
        return clientTo
    }

    override fun toString(): String {

        return name
    }

    companion object {

        private val serialVersionUID = 1L

        /**
         * @param authentication is the [Authentication] where to retrieve the user from.
         * *
         * @return the [UserData] of the logged in user from the given [Authentication].
         */
         @JvmStatic fun get(authentication: Authentication? = SecurityContextHolder.getContext().authentication): UserData {

            if (authentication == null) {
                throw IllegalStateException("Authentication not available!")
            }
            val principal = authentication.principal ?: throw IllegalStateException("Principal not available!")
            try {
                return principal as UserData
            } catch (e: ClassCastException) {
                throw IllegalStateException("Principal ($principal) is not an instance of UserData!", e)
            }

        }
    }
}
/**
 * @return the [UserData] of the user currently logged in.
 */
