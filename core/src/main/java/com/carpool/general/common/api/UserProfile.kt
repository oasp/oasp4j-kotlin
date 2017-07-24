package com.carpool.general.common.api

import com.carpool.general.common.api.datatype.Role

import java.security.Principal

/**
 * This is the interface for the profile of a user interacting with this application. Currently this can only be a
 * [com.carpool.staffmanagement.dataaccess.api.StaffMemberEntity] however in the future a
 * customer may login and make a reservation, etc.<br></br>
 * TODO: Also an external system may access the application via some service. Then there would be no user profile or it
 * would be empty...

 */
open interface UserProfile : Principal {
    /**
     * @return the technical ID of the user for calling REST services.
     */
    val id: Long?

    /**
     * @return the unique login of the user for authentication and identification.
     */
    override fun getName(): String

    /**x
     * @return the first name of the users real name.
     */
    val firstName: String

    /**
     * @return the last name of the users real name.
     */
    val lastName: String

    /**
     * @return [Role] of this [UserProfile].
     */
    val role: Role
}
