package com.carpool.general.common.api

import net.sf.mmm.util.entity.api.MutableGenericEntity

/**
 * This is the abstract interface for a [MutableGenericEntity] of this application. We are using [Long] for
 * all [primary keys][.getId].

 */
interface ApplicationEntity : MutableGenericEntity<Long>
