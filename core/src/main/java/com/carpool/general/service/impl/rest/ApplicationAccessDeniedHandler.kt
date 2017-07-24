package com.carpool.general.service.impl.rest

import io.oasp.module.rest.service.impl.RestServiceExceptionFacade

import java.io.IOException

import javax.inject.Inject
import javax.inject.Named
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.ws.rs.core.Response

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler

/**

 */
@Named("ApplicationAccessDeniedHandler")
class ApplicationAccessDeniedHandler : AccessDeniedHandler {

    private var exceptionFacade: RestServiceExceptionFacade? = null

    @Throws(IOException::class, ServletException::class)
    override fun handle(request: HttpServletRequest, response: HttpServletResponse,
                        accessDeniedException: AccessDeniedException) {

        val restResponse = this.exceptionFacade!!.toResponse(accessDeniedException)
        val entity = restResponse.entity
        response.status = restResponse.status
        if (entity != null) {
            response.writer.write(entity.toString())
        }
    }

    /**
     * @param exceptionFacade the exceptionFacade to set
     */
    @Inject
    fun setExceptionFacade(exceptionFacade: RestServiceExceptionFacade) {

        this.exceptionFacade = exceptionFacade
    }

}
