package com.carpool.visitormanagement.logic.api.to

import com.carpool.general.common.api.to.AbstractCto

/**
 * Composite transport object of AccessCode
 */
class AccessCodeCto : AbstractCto() {

    var accessCode: AccessCodeEto? = null

    var visitor: VisitorEto? = null

    companion object {

        private val serialVersionUID = 1L
    }

}
