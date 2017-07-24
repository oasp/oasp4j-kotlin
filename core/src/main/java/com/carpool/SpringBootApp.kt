package com.carpool

import org.springframework.boot.SpringApplication
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity

import io.oasp.module.jpa.dataaccess.api.AdvancedRevisionEntity

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, SecurityFilterAutoConfiguration.class })
@SpringBootApplication(exclude = arrayOf(EndpointAutoConfiguration::class))
@EntityScan(basePackages = arrayOf("com.carpool"), basePackageClasses = arrayOf(AdvancedRevisionEntity::class))
@EnableGlobalMethodSecurity(jsr250Enabled = true)
open class SpringBootApp 

    /**
     * Entry point for spring-boot based app
     * @param args - arguments
     */
     fun main(args: Array<String>) {

        SpringApplication.run(SpringBootApp::class.java, *args)
    }

