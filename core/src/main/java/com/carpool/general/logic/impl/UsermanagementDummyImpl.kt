package com.carpool.general.logic.impl

import com.carpool.general.common.api.UserProfile
import com.carpool.general.common.api.Usermanagement
import com.carpool.general.common.api.datatype.Role
import com.carpool.general.common.api.to.UserDetailsClientTo
import com.carpool.general.common.base.AbstractBeanMapperSupport

import javax.inject.Named

import org.springframework.stereotype.Component

/**
 * Implementation of [Usermanagement].
 */
@Named
@Component
class UsermanagementDummyImpl : AbstractBeanMapperSupport(), Usermanagement {

    override fun findUserProfileByLogin(login: String): UserProfile {
        // this is only a dummy - please replace with a real implementation
        val profile = UserDetailsClientTo()
        profile.name = login
        profile.firstName = "Peter"
        profile.lastName = login
        profile.role = Role.CHIEF
      
        return profile
    }

}
