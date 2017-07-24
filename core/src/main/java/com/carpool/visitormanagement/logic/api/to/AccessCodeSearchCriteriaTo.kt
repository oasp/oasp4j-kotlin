package com.carpool.visitormanagement.logic.api.to

import java.sql.Timestamp

import io.oasp.module.jpa.common.api.to.SearchCriteriaTo

/**
 * This is the [search criteria][SearchCriteriaTo] [TO][net.sf.mmm.util.transferobject.api.TransferObject]
 * used to find [com.carpool.visitormanagement.common.api.AccessCode]s.

 */
/**
 * The constructor.
 */
class AccessCodeSearchCriteriaTo : SearchCriteriaTo() {

    var code: String? = null

    var dateAndTime: Timestamp? = null

    var visitorId: Long? = null

    companion object {

        private val serialVersionUID = 1L
    }

}
