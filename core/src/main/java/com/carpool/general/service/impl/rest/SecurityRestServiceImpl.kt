package com.carpool.general.service.impl.rest

import com.carpool.general.common.api.exception.NoActiveUserException
import com.carpool.general.common.api.to.UserDetailsClientTo

import javax.annotation.security.PermitAll
import javax.inject.Inject
import javax.inject.Named
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.transaction.Transactional
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.web.csrf.CsrfToken
import org.springframework.security.web.csrf.CsrfTokenRepository

/**
 * The security REST service provides access to the csrf token, the authenticated user's meta-data. Furthermore, it
 * provides functionality to check permissions and roles of the authenticated user.

 */
@Path("/security/v1")
@Named("SecurityRestService")
@Transactional
open class SecurityRestServiceImpl {

  /**
   * Use [CsrfTokenRepository] for CSRF protection.
   */
  private var csrfTokenRepository: CsrfTokenRepository? = null

  /**
   * Retrieves the CSRF token from the server session.

   * @param request [HttpServletRequest] to retrieve the current session from
   * *
   * @param response [HttpServletResponse] to send additional information
   * *
   * @return the Spring Security [CsrfToken]
   */
  @Produces(APPLICATION_JSON)
  @GET
  @Path("/csrftoken/")
  @PermitAll
  open fun getCsrfToken(@Context request: HttpServletRequest, @Context response: HttpServletResponse): CsrfToken {

    // return (CsrfToken) request.getSession().getAttribute(
    // HttpSessionCsrfTokenRepository.class.getName().concat(".CSRF_TOKEN"));
    var token: CsrfToken = this.csrfTokenRepository!!.loadToken(request)
    if (token == null) {
      LOG.warn("No CsrfToken could be found - instanciating a new Token")
      token = this.csrfTokenRepository!!.generateToken(request)
      this.csrfTokenRepository!!.saveToken(token, request, response)
    }
    return token
  }

  /**
   * Gets the profile of the user being currently logged in.

   * @param request provided by the RS-Context
   * *
   * @return the [] taken from the Spring Security context
   */
  @Produces(APPLICATION_JSON)
  @GET
  @Path("/currentuser/")
  @PermitAll
  open fun getCurrentUser(@Context request: HttpServletRequest): UserDetailsClientTo {

    if (request.remoteUser == null) {
      // throw NoActiveUserException()
    }
    return com.carpool.general.common.api.security.UserData.get().toClientTo()
  }

  /**
   * @param csrfTokenRepository the csrfTokenRepository to set
   */
  @Inject
  open fun setCsrfTokenRepository(csrfTokenRepository: CsrfTokenRepository) {

    this.csrfTokenRepository = csrfTokenRepository
  }

  companion object {
    const val APPLICATION_JSON = "application/json"
    /** Logger instance.  */
    private val LOG = LoggerFactory.getLogger(SecurityRestServiceImpl::class.java)
  }
}
