package com.carpool.general.gui.api

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

/**
 * Controller for Login-Page.

 */
@Controller
class LoginController {

    /**
     * Builds the model for the login page---mainly focusing on the error message handling.

     * @param authentication_failed flag for authentication failed
     * *
     * @param invalid_session flag for invalid session
     * *
     * @param access_denied flag for access denied
     * *
     * @param logout flag for successful logout
     * *
     * @return the view model
     */
    @RequestMapping(value = "/login**", method = arrayOf(RequestMethod.GET))
    fun login(
            @RequestParam(value = "authentication_failed", required = false) authentication_failed: Boolean,
            @RequestParam(value = "invalid_session", required = false) invalid_session: Boolean,
            @RequestParam(value = "access_denied", required = false) access_denied: Boolean,
            @RequestParam(value = "logout", required = false) logout: Boolean): ModelAndView {

        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication.principal != "anonymousUser") {
            return ModelAndView("redirect:" + defaultTargetUrl)
        }

        val model = ModelAndView()
        if (authentication_failed) {
            model.addObject("error", "Authentication failed!")
        } else if (invalid_session) {
            model.addObject("error", "You are currently not logged in!")
        } else if (access_denied) {
            model.addObject("error", "You have insufficient permissions to access this page!")
        } else if (logout) {
            model.addObject("msg", "Logout successful!")
        }

        return model
    }

    companion object {

        /**
         * Default URL to redirect to after successfully login.
         */
        val defaultTargetUrl = "/"
    }

}
