package com.carpool.general.logic.base

import java.util.ArrayList
import java.util.HashMap

import net.sf.mmm.util.entity.api.GenericEntity
import net.sf.mmm.util.entity.api.PersistenceEntity
import net.sf.mmm.util.transferobject.api.AbstractTransferObject
import net.sf.mmm.util.transferobject.api.TransferObject

import com.carpool.general.common.base.AbstractBeanMapperSupport

import io.oasp.module.jpa.common.api.to.PaginatedListTo

/**
 * Abstract base class for any *use case* in this application. Actual implementations need to be annotated with
 * [javax.inject.Named] and [com.carpool.general.logic.api.UseCase].

 */
/**
 * The constructor.
 */
abstract class AbstractUc : AbstractBeanMapperSupport() {

    /**
     * Maps a [paginated list][PaginatedListTo] of persistent entities to a [paginated list][PaginatedListTo] of
     * transfer objects.

     * @param <T> is the generic type of the [transfer object][AbstractTransferObject].
     * *
     * @param <E> is the generic type of the [entity][PersistenceEntity].
     * *
     * @param paginatedList is the paginated list to map from.
     * *
     * @param klass is the target class to map the paginated entities to.
     * *
     * @return a [paginated list of entity transfer objects][PaginatedListTo].
    </E></T> */
    protected fun <T : TransferObject, E : PersistenceEntity<*>> mapPaginatedEntityList(
            paginatedList: PaginatedListTo<E>, klass: Class<T>): PaginatedListTo<T> {

        val etoList = beanMapper!!.mapList(paginatedList.result, klass)
        val result = PaginatedListTo(etoList, paginatedList.pagination)

        return result
    }

    companion object {

        /**
         * The limit for [maximum hit count][net.sf.mmm.util.search.base.AbstractSearchCriteria.getMaximumHitCount] for
         * UI requests.
         */
        protected val MAXIMUM_HIT_LIMIT = 500

        /**
         * Creates a [Map] with all [entities][GenericEntity] from the given [Collection] using their
         * [ID][GenericEntity.getId] as key. All [entities][GenericEntity] without an [ ID][GenericEntity.getId] (`null`) will be ignored.

         * @param <ID> is the generic type of the [ID][GenericEntity.getId].
         * *
         * @param <E> is the generic type of the [entity][GenericEntity].
         * *
         * @param entities is the [Collection] of [entities][GenericEntity].
         * *
         * @return a [Map] mapping from [ID][GenericEntity.getId] to [entity][GenericEntity].
        </E></ID> */
        protected fun <ID, E : GenericEntity<ID>> getEntityMap(entities: Collection<E>): Map<ID, E> {

            val id2EntityMap = HashMap<ID, E>()
            for (entity in entities) {
                val id = entity.id
                if (id != null) {
                    id2EntityMap.put(id, entity)
                }
            }
            return id2EntityMap
        }

        /**
         * Determines the [entities][GenericEntity] to delete if `currentList` is the current list from the
         * persistence and `listToSave` is the new list that shall be saved. In other words this method selects the
         * [entities][GenericEntity] from `currentList` that are not contained in `listToSave`.

         * @param <ID> is the generic type of the [ID][GenericEntity.getId].
         * *
         * @param <E> is the generic type of the [entity][GenericEntity].
         * *
         * @param currentEntities is the [Collection] of the [entities][GenericEntity] currently persisted. We
         * *        assume that all objects in this list have an [ID][GenericEntity.getId] value (that is not
         * *        `null`).
         * *
         * @param entitiesToSave is the [Collection] that contains the [entities][GenericEntity] that shall be
         * *        saved. It may contain [entities][GenericEntity] that have no [ID][GenericEntity.getId] that
         * *        shall be newly created.
         * *
         * @return the [List] with the [entities][GenericEntity] to delete.
        </E></ID> */
        protected fun <ID, E : GenericEntity<ID>> getEntities2Delete(currentEntities: Collection<E>,
                                                                     entitiesToSave: Collection<E>): List<E> {

            val result = ArrayList<E>(currentEntities.size)
            val entityMap = getEntityMap(entitiesToSave)
            for (entity in currentEntities) {
                if (!entityMap.containsKey(entity.id)) {
                    // entity from currentList is not contained in listToSave...
                    result.add(entity)
                }
            }
            return result
        }
    }

}
