package com.carpool.general.common.api.exception

import net.sf.mmm.util.nls.api.NlsMessage

/**
 * Abstract technical *checked* main exception.

 */
abstract class ApplicationTechnicalException : ApplicationException {

    /**
     * @param message the error [message][.getNlsMessage].
     */
    constructor(message: NlsMessage) : super(message) {
    }

    /**
     * @param cause the error [cause][.getCause].
     * *
     * @param message the error [message][.getNlsMessage].
     */
    constructor(cause: Throwable, message: NlsMessage) : super(cause, message) {
    }

    companion object {

        private val serialVersionUID = 1L
    }

}
