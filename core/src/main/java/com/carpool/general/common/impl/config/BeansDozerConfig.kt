package com.carpool.general.common.impl.config

import java.util.ArrayList

import org.dozer.DozerBeanMapper
import org.dozer.Mapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

/**
 * Java bean configuration for Dozer

 */
@Configuration
@ComponentScan(basePackages = arrayOf("io.oasp.module.beanmapping"))
open class BeansDozerConfig {

    /**
     * @return the [DozerBeanMapper].
     */
    open val dozer: Mapper
        @Bean
        get() {

            val beanMappings = ArrayList<String>()
            beanMappings.add(DOZER_MAPPING_XML)
            return DozerBeanMapper(beanMappings)

        }

    companion object {

        private val DOZER_MAPPING_XML = "config/app/common/dozer-mapping.xml"
    }
}
