package com.xpansiv.simulators.wf


import com.xpansiv.wf.user_management.UserChangeCommand

import com.xpansiv.wf.user_management.WFUserManagement

class MaestroUserManagementImpl(tioc: TemporalioConnector) : AbstractUserManagementActivity(tioc,WFUserManagement.TASK_QUEUE_MAESTRO) {

    override fun _execute(cmd: UserChangeCommand) {
//        throw Exception("bad implementation of Maestro")
    }
}

fun main() {
    val tioc = TemporalioConnector()
    val umActivity = MaestroUserManagementImpl(tioc)
    umActivity.start()
}
