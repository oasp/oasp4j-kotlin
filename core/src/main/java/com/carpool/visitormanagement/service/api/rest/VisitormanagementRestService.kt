package com.carpool.visitormanagement.service.api.rest

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

import com.carpool.visitormanagement.logic.api.Visitormanagement
import com.carpool.visitormanagement.logic.api.to.AccessCodeEto
import com.carpool.visitormanagement.logic.api.to.AccessCodeSearchCriteriaTo
import com.carpool.visitormanagement.logic.api.to.VisitorEto
import com.carpool.visitormanagement.logic.api.to.VisitorSearchCriteriaTo

import io.oasp.module.jpa.common.api.to.PaginatedListTo

/**
 * The service interface for REST calls in order to execute the logic of component [Visitormanagement].
 */
@Path("/visitormanagement/v1")
@Consumes("application/json")
@Produces("application/json")
interface VisitormanagementRestService {

    /**
     * Delegates to [Visitormanagement.findVisitor].

     * @param id the ID of the [VisitorEto]
     * *
     * @return the [VisitorEto]
     */
    @GET
    @Path("/visitor/{id}/")
    fun getVisitor(@PathParam("id") id: Long): VisitorEto

    /**
     * Delegates to [Visitormanagement.saveVisitor].

     * @param visitor the [VisitorEto] to be saved
     * *
     * @return the recently created [VisitorEto]
     */
    @POST
    @Path("/visitor/")
    fun saveVisitor(visitor: VisitorEto): VisitorEto

    /**
     * Delegates to [Visitormanagement.deleteVisitor].

     * @param id ID of the [VisitorEto] to be deleted
     */
    @DELETE
    @Path("/visitor/{id}/")
    fun deleteVisitor(@PathParam("id") id: Long)

    /**
     * Delegates to [Visitormanagement.findVisitorEtos].

     * @param searchCriteriaTo the pagination and search criteria to be used for finding visitors.
     * *
     * @return the [list][PaginatedListTo] of matching [VisitorEto]s.
     */
    @Path("/visitor/search")
    @POST
    fun findVisitorsByPost(searchCriteriaTo: VisitorSearchCriteriaTo): PaginatedListTo<VisitorEto>

    /**
     * Delegates to [Visitormanagement.findAccessCode].

     * @param id the ID of the [AccessCodeEto]
     * *
     * @return the [AccessCodeEto]
     */
    @GET
    @Path("/accesscode/{id}/")
    fun getAccessCode(@PathParam("id") id: Long): AccessCodeEto

    /**
     * Delegates to [Visitormanagement.saveAccessCode].

     * @param accesscode the [AccessCodeEto] to be saved
     * *
     * @return the recently created [AccessCodeEto]
     */
    @POST
    @Path("/accesscode/")
    fun saveAccessCode(accesscode: AccessCodeEto): AccessCodeEto

    /**
     * Delegates to [Visitormanagement.deleteAccessCode].

     * @param id ID of the [AccessCodeEto] to be deleted
     */
    @DELETE
    @Path("/accesscode/{id}/")
    fun deleteAccessCode(@PathParam("id") id: Long)

    /**
     * Delegates to [Visitormanagement.findAccessCodeEtos].

     * @param searchCriteriaTo the pagination and search criteria to be used for finding accesscodes.
     * *
     * @return the [list][PaginatedListTo] of matching [AccessCodeEto]s.
     */
    @Path("/accesscode/search")
    @POST
    fun findAccessCodesByPost(searchCriteriaTo: AccessCodeSearchCriteriaTo): PaginatedListTo<AccessCodeEto>

}
