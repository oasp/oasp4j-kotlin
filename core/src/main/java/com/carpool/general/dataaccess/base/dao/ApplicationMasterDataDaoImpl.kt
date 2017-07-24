package com.carpool.general.dataaccess.base.dao

import io.oasp.module.jpa.dataaccess.api.MutablePersistenceEntity
import io.oasp.module.jpa.dataaccess.api.RevisionedMasterDataDao

/**
 * This is the abstract base implementation of [RevisionedMasterDataDao] based on [ApplicationDaoImpl].

 * @param <ENTITY> is the [type][.getEntityClass] of the managed entity.
</ENTITY> */
/**
 * The constructor.
 */
abstract class ApplicationMasterDataDaoImpl<ENTITY : MutablePersistenceEntity<Long>> : ApplicationDaoImpl<ENTITY>(), RevisionedMasterDataDao<ENTITY> {

    override fun findAll(): List<ENTITY> {

        return super.findAll()
    }

}
