package com.carpool.general.dataaccess.api.dao

import io.oasp.module.jpa.dataaccess.api.GenericRevisionedDao
import io.oasp.module.jpa.dataaccess.api.RevisionedDao
import io.oasp.module.jpa.dataaccess.api.MutablePersistenceEntity

/**
 * Interface for all [DAOs][GenericRevisionedDao] (Data Access Object) of this application.


 * @param <ENTITY> is the type of the managed entity.
</ENTITY> */
interface ApplicationRevisionedDao<ENTITY : MutablePersistenceEntity<Long>> : ApplicationDao<ENTITY>, RevisionedDao<ENTITY>
