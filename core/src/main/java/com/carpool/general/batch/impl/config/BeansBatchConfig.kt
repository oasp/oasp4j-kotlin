package com.carpool.general.batch.impl.config

import javax.inject.Inject
import javax.sql.DataSource

import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor
import org.springframework.batch.core.configuration.support.MapJobRegistry
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.launch.support.SimpleJobOperator
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean
import org.springframework.beans.factory.BeanCreationException
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.DependsOn
import org.springframework.transaction.PlatformTransactionManager

/**
 * This class contains the configuration like jobLauncher,Jobrepository etc.
 */

import io.oasp.module.batch.common.impl.JobLauncherWithAdditionalRestartCapabilities

/**
 * This class contains configuration of batch beans.
 */
@Configuration
open class BeansBatchConfig(var dataSource :DataSource,  var transactionManager: PlatformTransactionManager) {

  var jobRepository = JobRepositoryFactoryBean()
  var jobRegistry= MapJobRegistry()
  var jobLauncher= JobLauncherWithAdditionalRestartCapabilities()
  var jobExplorer= JobExplorerFactoryBean()
    /**
     * @return datasource
     */
    /**
     * @param datasource the datasource to set
     */

    /**

     * @return transactionManager
     */
    /**

     * @param transactionManager the transactionManager to set
     */
  //? = null

    @Value("ISOLATION_DEFAULT")
    private val isolationLevelForCreate: String? = null

    /**
     * This method is creating joboperator bean

     * @return SimpleJobOperator
     */
    @Bean
    @DependsOn("jobRepository", "jobExplorer", "jobRegistry", "jobLauncher")
   open fun jobOperator(): SimpleJobOperator {

        val jobOperator = SimpleJobOperator()
        try {
            jobOperator.setJobExplorer(this.jobExplorer!!.`object`)
        } catch (e: Exception) {
            throw BeanCreationException("Could not create BatchJobOperator", e)
        }

        jobOperator.setJobLauncher(this.jobLauncher)
        jobOperator.setJobRegistry(this.jobRegistry)

        try {
            jobOperator.setJobRepository(this.jobRepository!!.`object`)
        } catch (e: Exception) {
            throw BeanCreationException("Could not create BatchJobOperator", e)
        }

        return jobOperator
    }

    /**
     * This method is creating jobrepository

     * @return JobRepositoryFactoryBean
     */
    @Bean(name = arrayOf("jobRepository"))
   open fun jobRepository(): JobRepositoryFactoryBean {

        this.jobRepository = JobRepositoryFactoryBean()
        this.jobRepository!!.setDataSource(this.dataSource)
        this.jobRepository!!.transactionManager = this.transactionManager
        this.jobRepository!!.setIsolationLevelForCreate(this.isolationLevelForCreate)
        return this.jobRepository
    }

    /**
     * This method is creating jobLauncher bean

     * @return SimpleJobLauncher
     */
    @Bean
    @DependsOn("jobRepository")
   open fun jobLauncher(): JobLauncherWithAdditionalRestartCapabilities {

        this.jobLauncher = JobLauncherWithAdditionalRestartCapabilities()

        try {
            this.jobLauncher!!.setJobRepository(this.jobRepository!!.`object`)
        } catch (e: Exception) {
            throw BeanCreationException("Could not create BatchJobOperator", e)
        }

        return this.jobLauncher
    }

    /**
     * This method is creating jobExplorer bean

     * @return JobExplorerFactoryBean
     */
    @Bean
    @DependsOn("dataSource")
   open fun jobExplorer(): JobExplorerFactoryBean {

        this.jobExplorer = JobExplorerFactoryBean()
        this.jobExplorer!!.setDataSource(this.dataSource)
        return this.jobExplorer
    }

    /**
     * This method is creating jobRegistry bean

     * @return MapJobRegistry
     */
    @Bean
    open fun jobRegistry(): MapJobRegistry {

        this.jobRegistry = MapJobRegistry()
        return this.jobRegistry
    }

    /**
     * This method is creating JobRegistryBeanPostProcessor

     * @return JobRegistryBeanPostProcessor
     */
    @Bean
    @DependsOn("jobRegistry")
    open fun jobRegistryBeanPostProcessor(): JobRegistryBeanPostProcessor {

        val postProcessor = JobRegistryBeanPostProcessor()
        postProcessor.setJobRegistry(this.jobRegistry)
        return postProcessor
    }

    /**
     * This method is creating incrementer

     * @return RunIdIncrementer
     */
    @Bean
   open fun incrementer(): RunIdIncrementer {

        return RunIdIncrementer()
    }

}
