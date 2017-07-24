package com.carpool.general.service.impl.config

import javax.servlet.Filter

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.filter.CharacterEncodingFilter

import io.oasp.module.logging.common.api.DiagnosticContextFacade
import io.oasp.module.logging.common.impl.DiagnosticContextFacadeImpl
import io.oasp.module.logging.common.impl.DiagnosticContextFilter
import io.oasp.module.logging.common.impl.PerformanceLogFilter

/**
 * Registers a number of filters for web requests.

 */
@Configuration
open class WebConfig {

    @Autowired private val beanFactory: AutowireCapableBeanFactory? = null

    /**
     * Register PerformanceLogFilter to log running time of requests.

     * @return filter
     */
    @Bean
    open fun performanceLogFilter(): FilterRegistrationBean {

        val registration = FilterRegistrationBean()
        val performanceLogFilter = PerformanceLogFilter()
        this.beanFactory!!.autowireBean(performanceLogFilter)
        registration.filter = performanceLogFilter
        registration.addUrlPatterns("/*")
        return registration
    }

    /**
     * Bean definition for DiagnosticContextFacade.

     * @return DiagnosticContextFacade
     */
    @Bean(name = arrayOf("DiagnosticContextFacade"))
    open fun diagnosticContextFacade(): DiagnosticContextFacade {

        return DiagnosticContextFacadeImpl()
    }

    /**
     * Register DiagnosticContextFilter to log service calls with correlation id.

     * @return filter
     */
    @Bean
    open fun diagnosticContextFilter(): FilterRegistrationBean {

        val registration = FilterRegistrationBean()
        val diagnosticContextFilter = DiagnosticContextFilter()
        this.beanFactory!!.autowireBean(diagnosticContextFilter)
        registration.filter = diagnosticContextFilter
        registration.addUrlPatterns("/services/*")
        return registration
    }

    /**
     * Register SetCharacterEncodingFilter to convert specical characters correctly.

     * @return filter
     */
    @Bean
    open fun setCharacterEncodingFilter(): FilterRegistrationBean {

        val registration = FilterRegistrationBean()
        val characterEncodingFilter = CharacterEncodingFilter()
        characterEncodingFilter.encoding = "UTF-8"
        characterEncodingFilter.setForceEncoding(false)
        this.beanFactory!!.autowireBean(characterEncodingFilter)
        registration.filter = characterEncodingFilter
        registration.addUrlPatterns("/*")
        return registration
    }
}