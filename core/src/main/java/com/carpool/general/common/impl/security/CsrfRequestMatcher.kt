package com.carpool.general.common.impl.security

import java.util.regex.Pattern

import javax.servlet.http.HttpServletRequest

import org.springframework.security.web.util.matcher.RequestMatcher

/**
 * This is the implementation of [RequestMatcher], which decides which [Requests][HttpServletRequest] require
 * a correct CSRF token.

 * @see [Cross-site request forgery](https://en.wikipedia.org/wiki/Cross-site_request_forgery)
 */
class CsrfRequestMatcher : RequestMatcher {

    override fun matches(request: HttpServletRequest): Boolean {

        // GET requests are read-only and therefore do not need CSRF protection
        if (HTTP_METHOD_PATTERN.matcher(request.method).matches()) {

            return false
        }

        // There are specific URLs which can not be protected from CSRF. For example, in case of the the login page,
        // the CSRF token can only be accessed after a successful authentication ("login").
        val requestPath = getRequestPath(request)
        for (path in PATH_PREFIXES_WITHOUT_CSRF_PROTECTION) {
            if (requestPath.startsWith(path)) {
                return false
            }
        }

        return true
    }

    private fun getRequestPath(request: HttpServletRequest): String {

        var url = request.servletPath
        val pathInfo = request.pathInfo

        if (pathInfo != null) {
            url += pathInfo
        }

        return url
    }

    companion object {

        private val HTTP_METHOD_PATTERN = Pattern.compile("^GET$")

        private val PATH_PREFIXES_WITHOUT_CSRF_PROTECTION = arrayOf("/login", "/logout", "/services/rest/login", "/websocket")
    }
}
