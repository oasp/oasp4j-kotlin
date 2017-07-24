package com.carpool.general.logic.impl

import com.carpool.general.dataaccess.api.BinaryObjectEntity
import com.carpool.general.dataaccess.api.dao.BinaryObjectDao
import com.carpool.general.logic.api.to.BinaryObjectEto
import com.carpool.general.logic.base.AbstractUc
import com.carpool.general.logic.base.UcManageBinaryObject

import java.sql.Blob

import javax.inject.Inject
import javax.inject.Named

/**
 * Implementation of the [UcManageBinaryObject] intreface.

 */
@Named
open class UcManageBinaryObjectImpl(var binaryObjectDao: BinaryObjectDao) : AbstractUc(), UcManageBinaryObject {

    /** @see .binaryObjectDao
     */

    /**
     * @return binaryObjectDao
     */


    /**
     * @param binaryObjectDao the binaryObjectDao to set
     */
  /*  @Inject
    fun setBinaryObjectDao(binaryObjectDao: BinaryObjectDao) {

        this.binaryObjectDao = binaryObjectDao
    }

    fun binaryObjectDao(): BinaryObjectDao {

        return this.binaryObjectDao
    }*/

    override fun saveBinaryObject(data: Blob, binaryObjectEto: BinaryObjectEto): BinaryObjectEto {

        val binaryObjectEntity = beanMapper!!.map(binaryObjectEto, BinaryObjectEntity::class.java)
        binaryObjectEntity.data = data
        this.binaryObjectDao!!.save(binaryObjectEntity)
        return beanMapper!!.map(binaryObjectEntity, BinaryObjectEto::class.java)
    }

    override fun deleteBinaryObject(binaryObjectId: Long?) {

        this.binaryObjectDao!!.delete(binaryObjectId)

    }

    override fun findBinaryObject(binaryObjectId: Long?): BinaryObjectEto {

        return beanMapper!!.map(this.binaryObjectDao!!.findOne(binaryObjectId), BinaryObjectEto::class.java)
    }

    override fun getBinaryObjectBlob(binaryObjectId: Long?): Blob {

        return this.binaryObjectDao!!.findOne(binaryObjectId).data
    }

}
