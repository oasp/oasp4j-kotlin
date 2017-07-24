package com.carpool.general.logic.api.to

import com.carpool.general.common.api.BinaryObject
import io.oasp.module.basic.common.api.to.AbstractEto

/**
 * The [ETO][io.oasp.module.basic.common.api.to.AbstractEto] for a [BinaryObject].

 */
/**
 * Constructor.
 */
class BinaryObjectEto : AbstractEto(), BinaryObject {

    override var mimeType: String = ""

    override var size: Long = 0

    companion object {

        private val serialVersionUID = 1L
    }

}
