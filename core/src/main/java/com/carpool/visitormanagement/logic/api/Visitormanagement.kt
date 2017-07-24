package com.carpool.visitormanagement.logic.api

import com.carpool.visitormanagement.logic.api.to.AccessCodeEto
import com.carpool.visitormanagement.logic.api.to.AccessCodeSearchCriteriaTo
import com.carpool.visitormanagement.logic.api.to.VisitorEto
import com.carpool.visitormanagement.logic.api.to.VisitorSearchCriteriaTo
import com.carpool.visitormanagement.logic.api.usecase.UcFindVisitor
import com.carpool.visitormanagement.logic.api.usecase.UcManageVisitor

import io.oasp.module.jpa.common.api.to.PaginatedListTo

/**
 * Interface for Visitormanagement component.
 */
interface Visitormanagement : UcFindVisitor, UcManageVisitor {

    /**
     * Returns a Visitor by its id 'id'.

     * @param id The id 'id' of the Visitor.
     * *
     * @return The [VisitorEto] with id 'id'
     */
    override fun findVisitor(id: Long?): VisitorEto

    /**
     * Returns a paginated list of Visitors matching the search criteria.

     * @param criteria the [VisitorSearchCriteriaTo].
     * *
     * @return the [List] of matching [VisitorEto]s.
     */
    override fun findVisitorEtos(criteria: VisitorSearchCriteriaTo): PaginatedListTo<VisitorEto>

    /**
     * Deletes a visitor from the database by its id 'visitorId'.

     * @param visitorId Id of the visitor to delete
     * *
     * @return boolean `true` if the visitor can be deleted, `false` otherwise
     */
    override fun deleteVisitor(visitorId: Long?): Boolean

    /**
     * Saves a visitor and store it in the database.

     * @param visitor the [VisitorEto] to create.
     * *
     * @return the new [VisitorEto] that has been saved with ID and version.
     */
    override fun saveVisitor(visitor: VisitorEto): VisitorEto

    /**
     * Returns a AccessCode by its id 'id'.

     * @param id The id 'id' of the AccessCode.
     * *
     * @return The [AccessCodeEto] with id 'id'
     */
    fun findAccessCode(id: Long?): AccessCodeEto

    /**
     * Returns a paginated list of AccessCodes matching the search criteria.

     * @param criteria the [AccessCodeSearchCriteriaTo].
     * *
     * @return the [List] of matching [AccessCodeEto]s.
     */
    fun findAccessCodeEtos(criteria: AccessCodeSearchCriteriaTo): PaginatedListTo<AccessCodeEto>

    /**
     * Deletes a accessCode from the database by its id 'accessCodeId'.

     * @param accessCodeId Id of the accessCode to delete
     * *
     * @return boolean `true` if the accessCode can be deleted, `false` otherwise
     */
    fun deleteAccessCode(accessCodeId: Long?): Boolean

    /**
     * Saves a accessCode and store it in the database.

     * @param accessCode the [AccessCodeEto] to create.
     * *
     * @return the new [AccessCodeEto] that has been saved with ID and version.
     */
    fun saveAccessCode(accessCode: AccessCodeEto): AccessCodeEto

}
