package com.carpool.general.common.api.exception

import com.carpool.general.common.api.NlsBundleApplicationRoot

/**
 * Thrown when an operation is requested that requires a user to be logged in, but no such user exists.

 */
class NoActiveUserException
/**
 * The constructor.

 * @param cause The root cause of this exception.
 */
@JvmOverloads constructor(cause: Throwable) : ApplicationBusinessException(cause, createBundle(NlsBundleApplicationRoot::class.java).errorNoActiveUser()) {
    companion object {

        /** UID for serialization.  */
        private val serialVersionUID = 1L
    }

}
/**
 * The constructor.
 */
