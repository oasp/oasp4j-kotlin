package com.carpool.general.dataaccess.api

import java.sql.Blob

import javax.persistence.Entity
import javax.persistence.Lob
import javax.persistence.Table

import com.carpool.general.common.api.BinaryObject

/**
 * [Entity][ApplicationPersistenceEntity] for [BinaryObject]. Contains the actual [Blob].

 */
/**
 * The constructor.
 */
@Entity
@Table(name = "BinaryObject")
class BinaryObjectEntity(var data: Blob) : ApplicationPersistenceEntity(), BinaryObject {

    /**
     * @return data
     */

    /**
     * Remove the following line completely (Type Annotation) in case of database other than PostGres and Uncomment the
     * annotation for @Lob
     */
    // @Type(type = "org.hibernate.type.BinaryType")
    /**
     * @param data the data to set
     */

    override var mimeType: String = ""

    override var size: Long = 0

    companion object {

        private val serialVersionUID = 1L
    }
}
