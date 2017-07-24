package com.carpool.general.common.api.datatype

import java.security.Principal

enum class Role(val roleName: String): Principal{
  
  CHIEF("Chief");
  
 override fun getName():String{
    return this.roleName;
  }

}