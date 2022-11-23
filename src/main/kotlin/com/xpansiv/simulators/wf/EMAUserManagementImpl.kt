package com.xpansiv.simulators.wf


import com.xpansiv.wf.user_management.WFUserManagement

class EMAUserManagementImpl(tioc: TemporalioConnector) : AbstractUserManagementActivity(tioc,WFUserManagement.TASK_QUEUE_EMA) {

}

fun main() {
    val tioc = TemporalioConnector()
    val umActivity = EMAUserManagementImpl(tioc)
    umActivity.start()
}
