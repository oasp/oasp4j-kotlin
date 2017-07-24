package com.carpool.visitormanagement.logic.api.to

import com.carpool.general.common.api.to.AbstractCto

/**
 * Composite transport object of Visitor
 */
class VisitorCto : AbstractCto() {

    var visitor: VisitorEto? = null

    var code: AccessCodeEto? = null

    companion object {

        private val serialVersionUID = 1L
    }

}
