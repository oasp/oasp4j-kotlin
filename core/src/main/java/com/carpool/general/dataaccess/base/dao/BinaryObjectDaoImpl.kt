package com.carpool.general.dataaccess.base.dao

import com.carpool.general.dataaccess.api.BinaryObjectEntity
import com.carpool.general.dataaccess.api.dao.BinaryObjectDao

import javax.inject.Named

/**
 * Implementation of [BinaryObjectDao].

 */
@Named
open class BinaryObjectDaoImpl : ApplicationDaoImpl<BinaryObjectEntity>(), BinaryObjectDao {

    public override fun getEntityClass(): Class<BinaryObjectEntity> {

        return BinaryObjectEntity::class.java
    }

}
