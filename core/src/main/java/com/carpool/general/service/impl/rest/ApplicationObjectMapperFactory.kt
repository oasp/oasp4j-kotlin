package com.carpool.general.service.impl.rest


import io.oasp.module.rest.service.impl.json.ObjectMapperFactory

import javax.inject.Named

import com.fasterxml.jackson.databind.jsontype.NamedType
import com.fasterxml.jackson.databind.module.SimpleModule

/**
 * The MappingFactory class to resolve polymorphic conflicts within the carpool application.

 */
@Named("ApplicationObjectMapperFactory")
class ApplicationObjectMapperFactory : ObjectMapperFactory() {
    /**
     * The constructor.
     */
    init {
        // register polymorphic base classes


        val subtypes: Array<NamedType>
        // register mapping for polymorphic sub-classes

    }
}
