package com.carpool.visitormanagement.logic.api.to

import io.oasp.module.jpa.common.api.to.SearchCriteriaTo

/**
 * This is the [search criteria][SearchCriteriaTo] [TO][net.sf.mmm.util.transferobject.api.TransferObject]
 * used to find [com.carpool.visitormanagement.common.api.Visitor]s.

 */
/**
 * The constructor.
 */
class VisitorSearchCriteriaTo : SearchCriteriaTo() {

    var name: String? = null

    var email: String? = null

    var phone: String? = null

    var codeId: Long? = null

    companion object {

        private val serialVersionUID = 1L
    }

}
