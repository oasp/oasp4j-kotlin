package com.carpool.general.service.impl.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.web.csrf.CsrfTokenRepository
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository

import com.carpool.general.logic.impl.config.DefaultRolesPrefixPostProcessor
import io.oasp.module.security.common.api.accesscontrol.AccessControlProvider
import io.oasp.module.security.common.base.accesscontrol.AccessControlSchemaProvider
import io.oasp.module.security.common.impl.accesscontrol.AccessControlProviderImpl
import io.oasp.module.security.common.impl.accesscontrol.AccessControlSchemaProviderImpl

/**
 * This configuration class provides factory methods for several Spring security related beans.

 */
@Configuration
open class WebSecurityBeansConfig {

    /**
     * This method provides a new instance of `AccessControlProvider`

     * @return the newly created `AccessControlProvider`
     */
    @Bean
    open fun accessControlProvider(): AccessControlProvider {

        return AccessControlProviderImpl()
    }

    /**
     * This method provides a new instance of `AccessControlSchemaProvider`

     * @return the newly created `AccessControlSchemaProvider`
     */
    @Bean
    open fun accessControlSchemaProvider(): AccessControlSchemaProvider {

        return AccessControlSchemaProviderImpl()
    }

    /**
     * This method provides a new instance of `CsrfTokenRepository`

     * @return the newly created `CsrfTokenRepository`
     */
    @Bean
    open fun csrfTokenRepository(): CsrfTokenRepository {

        return HttpSessionCsrfTokenRepository()
    }

    companion object {

        /**
         * This method provides a new instance of `DefaultRolesPrefixPostProcessor`

         * @return the newly create `DefaultRolesPrefixPostProcessor`
         */
        @Bean
        fun defaultRolesPrefixPostProcessor(): DefaultRolesPrefixPostProcessor {

            // By default Spring-Security is setting the prefix "ROLE_" for all permissions/authorities.
            // We disable this undesired behavior here...
            return DefaultRolesPrefixPostProcessor("")
        }
    }
}
