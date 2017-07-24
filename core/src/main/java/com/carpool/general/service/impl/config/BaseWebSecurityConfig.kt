package com.carpool.general.service.impl.config

import javax.inject.Inject
import javax.servlet.Filter

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.authentication.logout.LogoutFilter
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import org.springframework.security.web.csrf.CsrfFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.UrlBasedCorsConfigurationSource
import org.springframework.web.filter.CorsFilter

import com.carpool.general.common.impl.security.CsrfRequestMatcher

import io.oasp.module.security.common.impl.rest.AuthenticationSuccessHandlerSendingOkHttpStatusCode
import io.oasp.module.security.common.impl.rest.JsonUsernamePasswordAuthenticationFilter
import io.oasp.module.security.common.impl.rest.LogoutSuccessHandlerReturningOkHttpStatusCode

/**
 * This type serves as a base class for extensions of the `WebSecurityConfigurerAdapter` and provides a default
 * configuration. <br></br>
 * Security configuration is based on [WebSecurityConfigurerAdapter]. This configuration is by purpose designed
 * most simple for two channels of authentication: simple login form and rest-url.
 */
abstract class BaseWebSecurityConfig : WebSecurityConfigurerAdapter() {

    @Value("\${security.cors.enabled}")
    internal var corsEnabled = true

    @Inject
    private val userDetailsService: UserDetailsService? = null

    private val corsFilter: CorsFilter
        get() {

            val source = UrlBasedCorsConfigurationSource()
            val config = CorsConfiguration()
            config.allowCredentials = true
            config.addAllowedOrigin("*")
            config.addAllowedHeader("*")
            config.addAllowedMethod("OPTIONS")
            config.addAllowedMethod("HEAD")
            config.addAllowedMethod("GET")
            config.addAllowedMethod("PUT")
            config.addAllowedMethod("POST")
            config.addAllowedMethod("DELETE")
            config.addAllowedMethod("PATCH")
            source.registerCorsConfiguration("/**", config)
            return CorsFilter(source)
        }

    /**
     * Configure spring security to enable a simple webform-login + a simple rest login.
     */
    @Throws(Exception::class)
    public override fun configure(http: HttpSecurity) {

        val unsecuredResources = arrayOf("/login", "/security/**", "/services/rest/login", "/services/rest/logout")

        http
                //
                .userDetailsService(this.userDetailsService)
                // define all urls that are not to be secured
                .authorizeRequests().antMatchers(*unsecuredResources).permitAll().anyRequest().authenticated().and()

                // activate crsf check for a selection of urls (but not for login & logout)
                .csrf().requireCsrfProtectionMatcher(CsrfRequestMatcher()).and()

                // configure parameters for simple form login (and logout)
                .formLogin().successHandler(SimpleUrlAuthenticationSuccessHandler()).defaultSuccessUrl("/")
                .failureUrl("/login.html?error").loginProcessingUrl("/j_spring_security_login").usernameParameter("username")
                .passwordParameter("password").and()
                // logout via POST is possible
                .logout().logoutSuccessUrl("/login.html").and()

                // register login and logout filter that handles rest logins
                .addFilterAfter(simpleRestAuthenticationFilter, BasicAuthenticationFilter::class.java)
                .addFilterAfter(simpleRestLogoutFilter, LogoutFilter::class.java).csrf().disable()

        if (this.corsEnabled) {
            http.addFilterBefore(corsFilter, CsrfFilter::class.java)
        }
    }

    /**
     * Create a simple filter that allows logout on a REST Url /services/rest/logout and returns a simple HTTP status 200
     * ok.

     * @return the filter.
     */
    protected // configure logout for rest logouts
    val simpleRestLogoutFilter: Filter
        get() {

            val logoutFilter = LogoutFilter(LogoutSuccessHandlerReturningOkHttpStatusCode(), SecurityContextLogoutHandler())
            logoutFilter.setLogoutRequestMatcher(AntPathRequestMatcher("/services/rest/logout"))

            return logoutFilter
        }

    /**
     * Create a simple authentication filter for REST logins that reads user-credentials from a json-parameter and returns
     * status 200 instead of redirect after login.

     * @return the [JsonUsernamePasswordAuthenticationFilter].
     * *
     * @throws Exception if something goes wrong.
     */
    protected // set failurehandler that uses no redirect in case of login failure; just HTTP-status: 401
            // set successhandler that uses no redirect in case of login success; just HTTP-status: 200
    val simpleRestAuthenticationFilter: JsonUsernamePasswordAuthenticationFilter
        @Throws(Exception::class)
        get() {

            val jsonFilter = JsonUsernamePasswordAuthenticationFilter(AntPathRequestMatcher("/services/rest/login"))
            jsonFilter.passwordParameter = "j_password"
            jsonFilter.usernameParameter = "j_username"
            jsonFilter.setAuthenticationManager(authenticationManager())
            jsonFilter.setAuthenticationManager(authenticationManagerBean())
            jsonFilter.setAuthenticationFailureHandler(SimpleUrlAuthenticationFailureHandler())
            jsonFilter.setAuthenticationSuccessHandler(AuthenticationSuccessHandlerSendingOkHttpStatusCode())
            return jsonFilter
        }

    @Inject
    @Throws(Exception::class)
    fun configureGlobal(auth: AuthenticationManagerBuilder) {

        auth.inMemoryAuthentication().withUser("waiter").password("waiter").roles("Waiter").and().withUser("cook")
                .password("cook").roles("Cook").and().withUser("barkeeper").password("barkeeper").roles("Barkeeper").and()
                .withUser("chief").password("chief").roles("Chief")
    }

}
