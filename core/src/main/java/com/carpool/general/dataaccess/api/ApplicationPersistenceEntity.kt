package com.carpool.general.dataaccess.api

import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.Transient
import javax.persistence.Version

import com.carpool.general.common.api.ApplicationEntity

import io.oasp.module.jpa.dataaccess.api.MutablePersistenceEntity

/**
 * Abstract Entity for all Entities with an id and a version field.

 */
/**
 * The constructor.
 */
@MappedSuperclass
abstract class ApplicationPersistenceEntity : ApplicationEntity, MutablePersistenceEntity<Long> {

    /** @see .getId
     */
    private var id: Long? = null

    /** @see .getModificationCounter
     */
    private var modificationCounter: Int = 0

    /** @see .getRevision
     */
    private var revision: Number = 0

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    override fun getId(): Long? {

        return this.id
    }

    /**
     * {@inheritDoc}
     */
    override fun setId(id: Long?) {

        this.id = id
    }

    @Version
    override fun getModificationCounter(): Int {

        return this.modificationCounter
    }

    override fun setModificationCounter(version: Int) {

        this.modificationCounter = version
    }

    @Transient
    override fun getRevision(): Number {

        return this.revision
    }

    /**
     * @param revision the revision to set
     */
    override fun setRevision(revision: Number) {

        this.revision = revision
    }

    override fun toString(): String {

        val buffer = StringBuilder()
        toString(buffer)
        return buffer.toString()
    }

    /**
     * Method to extend [.toString] logic.

     * @param buffer is the [StringBuilder] where to [append][StringBuilder.append] the string
     * *        representation.
     */
    protected fun toString(buffer: StringBuilder) {

        buffer.append(javaClass.simpleName)
        if (this.id != null) {
            buffer.append("[id=")
            buffer.append(""+this.id)
            buffer.append("]")
        }
    }

    companion object {

        private val serialVersionUID = 1L
    }
}