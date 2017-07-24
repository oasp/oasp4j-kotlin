package com.carpool.general.common.impl.security

import java.util.HashSet

import javax.inject.Inject
import javax.inject.Named

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

import com.carpool.general.common.api.UserProfile
import com.carpool.general.common.api.Usermanagement
import com.carpool.general.common.api.security.UserData;
import io.oasp.module.security.common.api.accesscontrol.AccessControl
import io.oasp.module.security.common.api.accesscontrol.AccessControlProvider
import io.oasp.module.security.common.api.accesscontrol.PrincipalAccessControlProvider
import io.oasp.module.security.common.base.accesscontrol.AccessControlGrantedAuthority

/**
 * This class represents a customized implementation of the [UserDetailsService] interface.<br></br>
 * <br></br>
 * It should be used in custom subclasses of [WebSecurityConfigurerAdapter] in the following way:
 *
 *  * Inject a fully configured instance of [BaseUserDetailsService] into the subclass of
 * [WebSecurityConfigurerAdapter]
 *  * Override method `configure(HttpSecurity)` of [WebSecurityConfigurerAdapter]
 *  * Add the [BaseUserDetailsService] to the `HttpSecurity` object.
 *
 * The following code snippet shows the above steps:<br></br>

 * <pre>
 * &#64;Configuration
 * &#64;EnableWebSecurity
 * public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {
 * // ...
 * &#64;Inject
 * private UserDetailsService userDetailsService;
 * // ...
 * &#64;Override
 * public void configure(HttpSecurity http) throws Exception {
 * http.userDetailsService(this.userDetailsService)... //add matchers and other stuff
 * }
 * }
</pre> *

 * <br></br>
 * For another example, have a look at [BaseWebSecurityConfig].
 */
@Named
class BaseUserDetailsService(var usermanagement: Usermanagement,var amBuilder: AuthenticationManagerBuilder,  var accessControlProvider: AccessControlProvider, var principalAccessControlProvider: PrincipalAccessControlProvider<UserProfile>) : UserDetailsService {

    /**
     * @return usermanagement
     */
    /**
     * @param usermanagement new value of [.getUsermanagement].
     */
   // var usermanagement: Usermanagement? = null

    /**
     * @return amBuilder
     */
    /**
     * @param amBuilder new value of [.getAmBuilder].
     */
 //   var amBuilder: AuthenticationManagerBuilder? = null

    /**
     * @return accessControlProvider
     */
    /**
     * @param accessControlProvider new value of [.getAccessControlProvider].
     */
  //  var accessControlProvider: AccessControlProvider? = null

    /**
     * @return principalAccessControlProvider
     */
    /**
     * @param principalAccessControlProvider new value of [.getPrincipalAccessControlProvider].
     */
    //var principalAccessControlProvider: PrincipalAccessControlProvider<UserProfile>? = null

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {

        val principal = retrievePrincipal(username)
        val authorities = getAuthorities(principal)
        val user: UserDetails
        try {
            // amBuilder uses the InMemoryUserDetailsManager, because it is configured in BaseWebSecurityConfig
            user = amBuilder!!.getDefaultUserDetailsService().loadUserByUsername(username)
            val userData = UserData(user.username, user.password, authorities)
            userData.userProfile=principal
            return userData
        } catch (e: Exception) {
            e.printStackTrace()
            val exception = UsernameNotFoundException("Authentication failed.", e)
            LOG.warn("Failed to get user {}.", username, exception)
            throw exception
        }

    }

    /**
     * Returns the [GrantedAuthority]s of the user associated with the provided [UserProfile].

     * @param principal the [UserProfile] of the user
     * *
     * @return the associated [GrantedAuthority]s
     * *
     * @throws AuthenticationException if no principal is retrievable for the given `username`
     */
    @Throws(AuthenticationException::class)
    protected fun getAuthorities(principal: UserProfile?): Set<GrantedAuthority> {

        if (principal == null) {
            LOG.warn("Principal must not be null.")
            throw IllegalArgumentException()
        }
        // determine granted authorities for spring-security...
        val authorities = HashSet<GrantedAuthority>()
        val accessControlIds = this.principalAccessControlProvider!!.getAccessControlIds(principal)
        val accessControlSet = HashSet<AccessControl>()
        for (id in accessControlIds) {
            val success = this.accessControlProvider!!.collectAccessControls(id, accessControlSet)
            if (!success) {
                LOG.warn("Undefined access control {}.", id)
            }
        }
        for (accessControl in accessControlSet) {
            authorities.add(AccessControlGrantedAuthority(accessControl))
        }
        return authorities
    }

    /**
     * @param username The `username` for which the `UserProfile` will be queried.
     * *
     * @return An instance of type [UserProfile] obtained by querying the `username`.
     */
    protected fun retrievePrincipal(username: String): UserProfile {

        try {
            return this.usermanagement!!.findUserProfileByLogin(username)
        } catch (e: RuntimeException) {
            e.printStackTrace()
            val exception = UsernameNotFoundException("Authentication failed.", e)
            LOG.warn("Failed to get user {}.", username, exception)
            throw exception
        }

    }

    companion object {

        /** Logger instance.  */
        private val LOG = LoggerFactory.getLogger(BaseUserDetailsService::class.java)
    }
}
