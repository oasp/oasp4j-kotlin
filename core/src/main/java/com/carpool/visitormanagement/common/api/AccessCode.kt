package com.carpool.visitormanagement.common.api

import java.sql.Timestamp

import com.carpool.general.common.api.ApplicationEntity

interface AccessCode : ApplicationEntity {

    var code: String

    var dateAndTime: Timestamp

    var visitorId: Long?

}
