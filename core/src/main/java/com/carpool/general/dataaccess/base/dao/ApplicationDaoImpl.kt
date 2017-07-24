package com.carpool.general.dataaccess.base.dao

import org.springframework.stereotype.Repository

import com.carpool.general.dataaccess.api.dao.ApplicationDao

import io.oasp.module.jpa.dataaccess.api.MutablePersistenceEntity
import io.oasp.module.jpa.dataaccess.base.AbstractRevisionedDao

/**
 * This is the abstract base implementation of [ApplicationDao].

 * @param <ENTITY> is the [type][.getEntityClass] of the managed entity.
</ENTITY> */
/**
 * The constructor.
 */
@Repository
abstract class ApplicationDaoImpl<ENTITY : MutablePersistenceEntity<Long>> : AbstractRevisionedDao<ENTITY>(), ApplicationDao<ENTITY>
