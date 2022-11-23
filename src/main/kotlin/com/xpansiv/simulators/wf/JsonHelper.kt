package com.xpansiv.simulators.wf

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

object JsonHelper {
    fun toJsonString(v: Any): String {
        return om.writeValueAsString( v)
    }

    val om = jacksonObjectMapper()
}
