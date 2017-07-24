package com.carpool.visitormanagement.dataaccess.api.dao

import com.carpool.general.dataaccess.api.dao.ApplicationDao
import com.carpool.visitormanagement.dataaccess.api.VisitorEntity
import com.carpool.visitormanagement.logic.api.to.VisitorSearchCriteriaTo

import io.oasp.module.jpa.common.api.to.PaginatedListTo

/**
 * Data access interface for Visitor entities
 */
interface VisitorDao : ApplicationDao<VisitorEntity> {

    /**
     * Finds the [visitors][VisitorEntity] matching the given [VisitorSearchCriteriaTo].

     * @param criteria is the [VisitorSearchCriteriaTo].
     * *
     * @return the [PaginatedListTo] with the matching [VisitorEntity] objects.
     */
    fun findVisitors(criteria: VisitorSearchCriteriaTo): PaginatedListTo<VisitorEntity>
}
