package com.carpool.general.common.api.exception

import com.carpool.general.common.api.NlsBundleApplicationRoot

/**
 * This exception is thrown if an [entity][com.carpool.general.common.api.ApplicationEntity] has
 * a specific state that is illegal for the current operation and caused it to fail.

 */
class IllegalEntityStateException : ApplicationBusinessException {

    /**
     * The constructor.

     * @param entity the entity that caused this error.
     * *
     * @param state the state of the entity that is illegal for the failed operation.
     */
    constructor(entity: Any, state: Any) : this(null as Throwable, entity, state) {
    }

    /**
     * The constructor.

     * @param entity the entity that caused this error.
     * *
     * @param currentState the state of the carpoolEntity entity.
     * *
     * @param newState is the new state for the entity that is illegal.
     */
    constructor(entity: Any, currentState: Any, newState: Any) : this(null, entity, currentState, newState) {
    }

    /**
     * The constructor.

     * @param cause the [cause][.getCause] of this error.
     * *
     * @param entity the entity that caused this error.
     * *
     * @param state the state of the entity that is illegal for the failed operation.
     */
    constructor(cause: Throwable, entity: Any, state: Any) : super(cause,createBundle(NlsBundleApplicationRoot::class.java).errorIllegalEntityState(entity, state)) {
    }

    /**
     * The constructor.

     * @param cause the [cause][.getCause] of this error.
     * *
     * @param entity the entity that caused this error.
     * *
     * @param currentState the state of the entity.
     * *
     * @param newState is the new state for the entity that is illegal.
     */
    constructor(cause: Throwable?, entity: Any, currentState: Any, newState: Any) : super(cause!!, createBundle(NlsBundleApplicationRoot::class.java).errorIllegalEntityStateChange(entity, currentState,
            newState)) {
    }

    companion object {

        /** UID for serialization.  */
        private val serialVersionUID = 1L
    }

}
