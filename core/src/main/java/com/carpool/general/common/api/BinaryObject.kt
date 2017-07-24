package com.carpool.general.common.api

/**
 * This is the interface for a [BinaryObject] of the carpool.

 */
interface BinaryObject : ApplicationEntity {

    /**
     * Returns MIME-Type of thie [BinaryObject]

     * @return the MIME-Type, e.g image/jpeg
     */
    /**
     * @param mimeType is the MIME-Type of this [BinaryObject]
     */
    var mimeType: String

    /**
     * @return Returns the size in bytes
     */
    /**
     * Sets the size of bytes

     * @param size the size in bytes
     */
    var size: Long

}
