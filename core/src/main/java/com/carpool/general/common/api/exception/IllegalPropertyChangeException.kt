package com.carpool.general.common.api.exception

import com.carpool.general.common.api.NlsBundleApplicationRoot

/**
 * This exception is thrown if an [entity][com.carpool.general.common.api.ApplicationEntity] has
 * a specific state that is illegal for the current operation and caused it to fail.

 */
class IllegalPropertyChangeException
/**
 * The constructor.

 * @param cause the [cause][.getCause] of this error.
 * *
 * @param object the object that caused this error.
 * *
 * @param property is the property that can not be changed (typically a [String]).
 */
(cause: Throwable, `object`: Any, property: Any) : ApplicationBusinessException(cause, createBundle(NlsBundleApplicationRoot::class.java).errorIllegalPropertyChange(`object`, property)) {

    /**
     * The constructor.

     * @param object the object that caused this error.
     * *
     * @param property is the property that can not be changed (typically a [String]).
     */
    constructor(`object`: Any, property: Any) : this(null as Throwable, `object`, property) {
    }

    companion object {

        /** UID for serialization.  */
        private val serialVersionUID = 1L
    }

}
