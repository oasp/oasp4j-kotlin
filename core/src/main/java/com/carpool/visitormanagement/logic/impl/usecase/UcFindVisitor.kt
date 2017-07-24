package com.carpool.visitormanagement.logic.impl.usecase

import com.carpool.visitormanagement.logic.api.to.VisitorEto
import com.carpool.visitormanagement.logic.api.to.VisitorSearchCriteriaTo
import io.oasp.module.jpa.common.api.to.PaginatedListTo

interface UcFindVisitor {
    /**
     * Returns a Visitor by its id 'id'.

     * @param id The id 'id' of the Visitor.
     * *
     * @return The [VisitorEto] with id 'id'
     */
    fun findVisitor(id: Long?): VisitorEto

    /**
     * Returns a paginated list of Visitors matching the search criteria.

     * @param criteria the [VisitorSearchCriteriaTo].
     * *
     * @return the [List] of matching [VisitorEto]s.
     */
    fun findVisitorEtos(criteria: VisitorSearchCriteriaTo): PaginatedListTo<VisitorEto>
}