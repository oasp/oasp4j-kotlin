package com.carpool.general.service.impl.config

import java.util.ArrayList

import javax.inject.Inject
import javax.ws.rs.Path
import javax.ws.rs.ext.Provider
import javax.xml.ws.Endpoint

import org.apache.cxf.bus.spring.SpringBus
import org.apache.cxf.endpoint.Server
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean
import org.apache.cxf.jaxws.EndpointImpl
import org.apache.cxf.transport.servlet.CXFServlet
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.servlet.ServletRegistrationBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.ImportResource
import org.springframework.ws.config.annotation.EnableWs
import org.springframework.ws.config.annotation.WsConfigurerAdapter

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider


import io.oasp.module.rest.service.impl.RestServiceExceptionFacade
import io.oasp.module.rest.service.impl.json.ObjectMapperFactory

/**
 * [Configuration] for (REST or SOAP) services using CXF.
 */
@Configuration
@EnableWs
@ImportResource("classpath:META-INF/cxf/cxf.xml")
open class ServiceConfig : WsConfigurerAdapter() {

  @Value("\${security.expose.error.details}")
  internal var exposeInternalErrorDetails: Boolean = false

  @Inject
  private val applicationContext: ApplicationContext? = null

  @Inject
  private val objectMapperFactory: ObjectMapperFactory? = null

  @Bean(name = arrayOf("cxf"))
  open fun springBus(): SpringBus {

    return SpringBus()
  }

  @Bean
  open fun jacksonJsonProvider(): JacksonJsonProvider {

    return JacksonJsonProvider(this.objectMapperFactory!!.createInstance())
  }

  @Bean
  open fun servletRegistrationBean(): ServletRegistrationBean {

    val cxfServlet = CXFServlet()
    val servletRegistration = ServletRegistrationBean(cxfServlet, URL_PATH_SERVICES + "/*")
    return servletRegistration
  }

  @Bean
  open fun jaxRsServer(): Server? {

    // List<Object> restServiceBeans = new
    // ArrayList<>(this.applicationContext.getBeansOfType(RestService.class).values());
    val restServices = findRestServices()
    if (restServices.isEmpty()) {
      LOG.info("No REST Services have been found. Rest Endpoint will not be enabled in CXF.")
      return null
    }
    val providers = this.applicationContext!!.getBeansWithAnnotation(Provider::class.java).values

    val factory = JAXRSServerFactoryBean()
    factory.bus = springBus()
    factory.address = URL_FOLDER_REST
    // factory.set
    factory.setServiceBeans(ArrayList(restServices))
    factory.providers = ArrayList(providers)

    return factory.create()
  }

  private fun findRestServices(): Collection<Any> {

    return this.applicationContext!!.getBeansWithAnnotation(Path::class.java).values
  }

  @Bean
  open fun restServiceExceptionFacade(): RestServiceExceptionFacade {

    val exceptionFacade = RestServiceExceptionFacade()
    exceptionFacade.isExposeInternalErrorDetails = this.exposeInternalErrorDetails
    return exceptionFacade
  }

  companion object {

    /** Logger instance.  */
    private val LOG = LoggerFactory.getLogger(ServiceConfig::class.java)

    /** The services "folder" of an URL.  */
    val URL_FOLDER_SERVICES = "services"

    val URL_PATH_SERVICES = "/" + URL_FOLDER_SERVICES

    val URL_FOLDER_REST = "/rest"

    val URL_FOLDER_WEB_SERVICES = "/ws"

    val URL_PATH_REST_SERVICES = URL_PATH_SERVICES + "/" + URL_FOLDER_REST

    val URL_PATH_WEB_SERVICES = URL_PATH_SERVICES + "/" + URL_FOLDER_WEB_SERVICES
  }


}