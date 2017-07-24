package com.carpool.general.common.api

import javax.inject.Named

import net.sf.mmm.util.nls.api.NlsBundle
import net.sf.mmm.util.nls.api.NlsBundleMessage
import net.sf.mmm.util.nls.api.NlsMessage

/**
 * This is the [NlsBundle] for this application.

 */
interface NlsBundleApplicationRoot : NlsBundle {

    /**
     * @see com.carpool.general.common.api.exception.IllegalEntityStateException


     * @param entity is the entity relevant for the error.
     * *
     * @param state is the state of the entity that caused the operation to fail.
     * *
     * @return the [NlsMessage].
     */
    @NlsBundleMessage("The entity {entity} is in state {state}!")
    fun errorIllegalEntityState(@Named("entity") entity: Any, @Named("state") state: Any): NlsMessage

    /**
     * @see com.carpool.general.common.api.exception.IllegalEntityStateException


     * @param entity is the entity relevant for the error.
     * *
     * @param currentState is the current state of the entity.
     * *
     * @param newState is the new state for the entity that is illegal.
     * *
     * @return the [NlsMessage].
     */
    @NlsBundleMessage("The entity {entity} in state {currentState} can not be changed to state {newState}!")
    fun errorIllegalEntityStateChange(@Named("entity") entity: Any, @Named("currentState") currentState: Any,
                                      @Named("newState") newState: Any): NlsMessage

    /**
     * @see com.carpool.general.common.api.exception.IllegalEntityStateException


     * @param object is the entity relevant for the error.
     * *
     * @param property is the property of the entity that can not be changed.
     * *
     * @return the [NlsMessage].
     */
    @NlsBundleMessage("The property {property} of object {object} can not be changed!")
    fun errorIllegalPropertyChange(@Named("object") `object`: Any, @Named("property") property: Any): NlsMessage

    /**
     * @see com.carpool.general.common.api.exception.NoActiveUserException


     * @return the [NlsMessage].
     */
    @NlsBundleMessage("There is currently no user logged in")
    fun errorNoActiveUser(): NlsMessage


}
