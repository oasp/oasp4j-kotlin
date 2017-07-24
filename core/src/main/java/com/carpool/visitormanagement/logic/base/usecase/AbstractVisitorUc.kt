package com.carpool.visitormanagement.logic.base.usecase

import javax.inject.Inject

import com.carpool.general.logic.base.AbstractUc
import com.carpool.visitormanagement.dataaccess.api.dao.VisitorDao

/**
 * Abstract use case for Visitors, which provides access to the commonly necessary data access objects.
 */
open class AbstractVisitorUc : AbstractUc() {

    /** @see .getVisitorDao
     */
    /**
     * Returns the field 'visitorDao'.

     * @return the [VisitorDao] instance.
     */
    @Inject
    val visitorDao: VisitorDao? = null

}
