package com.carpool.visitormanagement.dataaccess.api.dao

import com.carpool.general.dataaccess.api.dao.ApplicationDao
import com.carpool.visitormanagement.dataaccess.api.AccessCodeEntity
import com.carpool.visitormanagement.logic.api.to.AccessCodeSearchCriteriaTo

import io.oasp.module.jpa.common.api.to.PaginatedListTo

/**
 * Data access interface for AccessCode entities
 */
interface AccessCodeDao : ApplicationDao<AccessCodeEntity> {

    /**
     * Finds the [accesscodes][AccessCodeEntity] matching the given [AccessCodeSearchCriteriaTo].

     * @param criteria is the [AccessCodeSearchCriteriaTo].
     * *
     * @return the [PaginatedListTo] with the matching [AccessCodeEntity] objects.
     */
    fun findAccessCodes(criteria: AccessCodeSearchCriteriaTo): PaginatedListTo<AccessCodeEntity>
}
