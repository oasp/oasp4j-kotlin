package com.carpool.general.logic.api

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

import javax.inject.Qualifier

/**
 * This is a [Qualifier] to mark all use-cases. In your use-case implementation add the annotations
 * [javax.inject.Named] and [UseCase]. In your component facade implementation add the annotations
 * [javax.inject.Inject] and [UseCase] to the setters in order to inject your use case implementations.

 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
annotation class UseCase
