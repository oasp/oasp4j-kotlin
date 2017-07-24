package com.carpool.general.common.base

import javax.inject.Inject

import io.oasp.module.beanmapping.common.api.BeanMapper

/**
 * This abstract class provides [access][.getBeanMapper] to the [BeanMapper].

 */
abstract class AbstractBeanMapperSupport {

    /** @see .getBeanMapper
     */
    /**
     * @return the [BeanMapper] instance.
     */
    /**
     * @param beanMapper is the [BeanMapper] to [Inject]
     */
    protected var beanMapper: BeanMapper? = null
        set

}
