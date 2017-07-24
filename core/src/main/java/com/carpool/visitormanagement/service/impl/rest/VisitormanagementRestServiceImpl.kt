package com.carpool.visitormanagement.service.impl.rest

import javax.inject.Inject
import javax.inject.Named

import com.carpool.visitormanagement.logic.api.Visitormanagement
import com.carpool.visitormanagement.logic.api.to.AccessCodeEto
import com.carpool.visitormanagement.logic.api.to.AccessCodeSearchCriteriaTo
import com.carpool.visitormanagement.logic.api.to.VisitorEto
import com.carpool.visitormanagement.logic.api.to.VisitorSearchCriteriaTo
import com.carpool.visitormanagement.service.api.rest.VisitormanagementRestService

import io.oasp.module.jpa.common.api.to.PaginatedListTo

/**
 * The service implementation for REST calls in order to execute the logic of component [Visitormanagement].
 */
@Named("VisitormanagementRestService")
class VisitormanagementRestServiceImpl(val visitormanagement: Visitormanagement) : VisitormanagementRestService {

    
     

    override fun getVisitor(id: Long): VisitorEto {

        return this.visitormanagement!!.findVisitor(id)
    }

    override fun saveVisitor(visitor: VisitorEto): VisitorEto {

        return this.visitormanagement!!.saveVisitor(visitor)
    }

    override fun deleteVisitor(id: Long) {

        this.visitormanagement!!.deleteVisitor(id)
    }

    override fun findVisitorsByPost(searchCriteriaTo: VisitorSearchCriteriaTo): PaginatedListTo<VisitorEto> {

        return this.visitormanagement!!.findVisitorEtos(searchCriteriaTo)
    }

    override fun getAccessCode(id: Long): AccessCodeEto {

        return this.visitormanagement!!.findAccessCode(id)
    }

    override fun saveAccessCode(accesscode: AccessCodeEto): AccessCodeEto {

        return this.visitormanagement!!.saveAccessCode(accesscode)
    }

    override fun deleteAccessCode(id: Long) {

        this.visitormanagement!!.deleteAccessCode(id)
    }

    override fun findAccessCodesByPost(searchCriteriaTo: AccessCodeSearchCriteriaTo): PaginatedListTo<AccessCodeEto> {

        return this.visitormanagement!!.findAccessCodeEtos(searchCriteriaTo)
    }

}
