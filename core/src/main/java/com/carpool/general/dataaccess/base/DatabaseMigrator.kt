package com.carpool.general.dataaccess.base

import javax.sql.DataSource

import org.flywaydb.core.Flyway

/**
 * Type to migrate the database. Gets initialized before hibernate.

 */
class DatabaseMigrator {

    /** Property is true if database migration is enabled.  */
    /**
     * @return enabled
     */
    /**
     * @param enabled the enabled to set
     */
    var isEnabled: Boolean = false

    /** The JDBC data source.  */
    /**
     * @return datasource
     */
    /**
     * @param datasource the datasource to set
     */
    var dataSource: DataSource? = null

    /** Property is true if test data should be included in migration.  */
    private var testdata: Boolean = false

    /** Property is true if database should be cleaned before migration.  */
    private var clean: Boolean = false

    /**
     * Migrate the database to the latest available migration.
     */
    fun migrate() {

        if (this.isEnabled) {
            val flyway = Flyway()
            flyway.dataSource = this.dataSource
            if (this.testdata) {
                flyway.setLocations(masterDataPath, testDataPath)
            } else {
                flyway.setLocations(masterDataPath)
            }
            if (this.clean) {
                flyway.clean()
            }
            flyway.migrate()
        }
    }

    /**
     * Import test data.

     * @param importTestDataPath path to directory with files with test data
     */
    fun importTestData(importTestDataPath: String) {

        val flyway = Flyway()
        flyway.dataSource = this.dataSource
        flyway.setLocations(importTestDataPath)
        flyway.migrate()
    }

    /**
     * @param testdata the testdata to set
     */
    fun setTestdata(testdata: Boolean) {

        this.testdata = testdata
    }

    /**
     * @param clean the clean to set
     */
    fun setClean(clean: Boolean) {

        this.clean = clean
    }

    companion object {

        /** Path of test data location.  */
        private val testDataPath = "classpath:db/test-data"

        /** Path of master data location.  */
        private val masterDataPath = "classpath:db/migration"
    }

}
