package com.carpool.general.logic.base

import com.carpool.general.logic.api.to.BinaryObjectEto

import java.sql.Blob

/**
 * Use case for managing BinaryObject.

 */
interface UcManageBinaryObject {

    /**
     * @param data the Blob data to save
     * *
     * @param binaryObjectEto the [BinaryObjectEto]
     * *
     * @return [BinaryObjectEto]
     */
    fun saveBinaryObject(data: Blob, binaryObjectEto: BinaryObjectEto): BinaryObjectEto

    /**
     * @param binaryObjectId the ID of the [BinaryObjectEto] that should be deleted
     */
    fun deleteBinaryObject(binaryObjectId: Long?)

    /**
     * @param binaryObjectId the ID of the [BinaryObjectEto] to find
     * *
     * @return [BinaryObjectEto]
     */
    fun findBinaryObject(binaryObjectId: Long?): BinaryObjectEto

    /**
     * @param binaryObjectId the ID of the [BinaryObjectEto] the blob corresponds to
     * *
     * @return [Blob]
     */
    fun getBinaryObjectBlob(binaryObjectId: Long?): Blob

}
