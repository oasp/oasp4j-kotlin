package com.carpool

import org.springframework.boot.SpringApplication
import org.springframework.boot.actuate.autoconfigure.EndpointAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.security.SecurityFilterAutoConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity

import io.oasp.module.jpa.dataaccess.api.AdvancedRevisionEntity

@SpringBootApplication(exclude = arrayOf(EndpointAutoConfiguration::class, SecurityAutoConfiguration::class, SecurityFilterAutoConfiguration::class))
@EntityScan(basePackages = arrayOf("com.carpool"), basePackageClasses = arrayOf(AdvancedRevisionEntity::class))
@EnableGlobalMethodSecurity(jsr250Enabled = false)
 open class SpringBootBatchApp 

    /**
     * Entry point for spring-boot based app

     * @param args - arguments
     */
    fun main(args: Array<String>) {

        SpringApplication.run(SpringBootApp::class.java, *args)
    }

