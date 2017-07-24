package com.carpool.general.common.api.to

import com.carpool.general.common.api.UserProfile
import com.carpool.general.common.api.datatype.Role
import io.oasp.module.basic.common.api.to.AbstractTo

/**
 * This is the [TO][AbstractTo] for the client view on the user details.

 */
/**
 * The constructor.
 */
open class UserDetailsClientTo() : AbstractTo(), UserProfile {

  override var id: Long? = null

  private var name: String = ""

  override var lastName: String = ""
  override var firstName: String = ""
  override var role: Role = Role.CHIEF

  override fun getName(): String {
    return this.name
  }

  fun setName(name:String) {
    this.name = name
  }

  companion object {

    /** UID for serialization.  */
    private val serialVersionUID = 1L
  }

}
