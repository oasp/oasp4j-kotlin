package com.carpool.visitormanagement.common.api

import com.carpool.general.common.api.ApplicationEntity

interface Visitor : ApplicationEntity {

    var name: String

    var email: String

    var phone: String

    var codeId: Long?

}
