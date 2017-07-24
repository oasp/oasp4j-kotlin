package com.carpool.general.common.api.to

import net.sf.mmm.util.transferobject.api.EntityTo

/**
 * Abstract base class for an *[entity transfer-object][EntityTo]* in this application.
 */
/**
 * The constructor.
 */
open class AbstractEto : EntityTo<Long>() {
    companion object {

        private val serialVersionUID = 1L
    }

}
