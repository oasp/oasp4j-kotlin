package com.carpool.general.common.impl.security

import java.util.Arrays

import javax.inject.Named

import com.carpool.general.common.api.UserProfile
import io.oasp.module.security.common.api.accesscontrol.PrincipalAccessControlProvider

/**
 * The implementation of [PrincipalAccessControlProvider] for this sample application.<br></br>
 * ATTENTION:<br></br>
 * In reality you would typically receive the user-profile from the central identity-management (via LDAP) and the roles
 * (and groups) from a central access manager (that might also implement the identify-management). This design was only
 * chosen to keep our sample application simple. Otherwise one would have to start a separate external server
 * application to make everything work what would be too complicated to get things running easily.

 */
/**
 * The constructor.
 */
@Named
class PrincipalAccessControlProviderImpl : PrincipalAccessControlProvider<UserProfile> {

    override fun getAccessControlIds(principal: UserProfile): Collection<String> {

        return Arrays.asList(principal.role.getName())
    }

}
