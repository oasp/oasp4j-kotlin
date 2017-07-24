package com.carpool.general.service.impl.config

import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

import io.oasp.module.basic.common.api.config.SpringProfileConstants

/**
 * Security configuration based on [WebSecurityConfigurerAdapter]. This configuration is by purpose designed most
 * simple for two channels of authentication: simple login form and rest-url. (Copied from
 * [com.carpool.general.service.impl.config.BaseWebSecurityConfig]

 */
@Configuration
@EnableWebSecurity
@Profile("! junit")
open class WebSecurityConfig : BaseWebSecurityConfig()
