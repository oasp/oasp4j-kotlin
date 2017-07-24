package com.carpool.general.logic.impl.config

import org.springframework.beans.BeansException
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.core.PriorityOrdered
import org.springframework.security.access.annotation.Jsr250MethodSecurityMetadataSource
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter

/**
 * This is an implementation of [BeanPostProcessor] that allows to change the role prefix of spring-security. By
 * default spring-security is magically adding a strange prefix called "ROLE_" to your granted authorities. In order to
 * prevent this we use this class with an empty prefix.
 */
class DefaultRolesPrefixPostProcessor
/**
 * Der Konstruktor.

 * @param rolePrefix das gewünschte Rollen-Präfix (z.B. der leere String).
 */
(private val rolePrefix: String) : BeanPostProcessor, PriorityOrdered {

    @Throws(BeansException::class)
    override fun postProcessAfterInitialization(bean: Any, beanName: String): Any {

        // remove this if you are not using JSR-250
        if (bean is Jsr250MethodSecurityMetadataSource) {
            bean.setDefaultRolePrefix(this.rolePrefix)
        }

        if (bean is DefaultMethodSecurityExpressionHandler) {
            bean.setDefaultRolePrefix(this.rolePrefix)
        }
        if (bean is DefaultWebSecurityExpressionHandler) {
            bean.setDefaultRolePrefix(this.rolePrefix)
        }
        if (bean is SecurityContextHolderAwareRequestFilter) {
            bean.setRolePrefix(this.rolePrefix)
        }
        return bean
    }

    @Throws(BeansException::class)
    override fun postProcessBeforeInitialization(bean: Any, beanName: String): Any {

        return bean
    }

    override fun getOrder(): Int {

        return PriorityOrdered.HIGHEST_PRECEDENCE
    }
}
