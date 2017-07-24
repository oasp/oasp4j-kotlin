package com.carpool.general.common.impl.security

import javax.inject.Inject
import javax.inject.Named

import com.carpool.general.common.api.security.UserData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

import com.carpool.general.common.api.UserProfile
import com.carpool.general.common.api.Usermanagement
import io.oasp.module.security.common.base.accesscontrol.AbstractAccessControlBasedAuthenticationProvider

/**
 * This class is responsible for the security aspects of authentication as well as providing user profile
 * data and the access-controls for authoriziation.
@Deprecate d("As of bug-fix release 2.1.2 the authentication mechanism changes. It is now based upon custom\n              implementations of {@link UserDetailsService} in combination with {@link WebSecurityConfigurerAdapter}.\n              For further information have a look at the sample application. <br/>\n              <br/>")
 */
/**
 * The constructor.
 */
@Deprecated("")
@Named("ApplicationAuthenticationProvider")
class ApplicationAuthenticationProvider : AbstractAccessControlBasedAuthenticationProvider<UserData, UserProfile>() {

    private var usermanagement: Usermanagement? = null

    @Throws(AuthenticationException::class)
    override fun additionalAuthenticationChecks(userDetails: UserDetails,
                                                authentication: UsernamePasswordAuthenticationToken) {

        authentication.details = userDetails
    }

    /**
     * @param usermanagement the [Usermanagement] to set
     */
    @Inject
    fun setUsermanagement(usermanagement: Usermanagement) {

        this.usermanagement = usermanagement
    }

    override fun retrievePrincipal(username: String, authentication: UsernamePasswordAuthenticationToken): UserProfile {

        try {
            return this.usermanagement!!.findUserProfileByLogin(username)
        } catch (e: RuntimeException) {
            e.printStackTrace()
            val exception = UsernameNotFoundException("Authentication failed.", e)
            LOG.warn("Failed to get user {}.", username, exception)
            throw exception
        }

    }

    override fun createUser(username: String, password: String, principal: UserProfile,
                            authorities: Set<GrantedAuthority>): UserData {

        val user = UserData(username, password, authorities)
        user.userProfile = principal
        return user
    }

    /*
   * Leave empty on purpose. Not used in this version.
   */
    override fun retrievePrincipal(username: String): UserProfile? {

        return null
    }

    companion object {

        /** Logger instance.  */
        private val LOG = LoggerFactory.getLogger(ApplicationAuthenticationProvider::class.java)
    }

}
