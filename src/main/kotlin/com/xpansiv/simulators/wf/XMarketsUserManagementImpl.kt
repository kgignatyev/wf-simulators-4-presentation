package com.xpansiv.simulators.wf


import com.xpansiv.wf.user_management.WFUserManagement

class XMarketsUserManagementImpl(tioc: TemporalioConnector) : AbstractUserManagementActivity(tioc,WFUserManagement.TASK_QUEUE_XMARKETS) {

}

fun main() {
    val tioc = TemporalioConnector()
    val umActivity = XMarketsUserManagementImpl(tioc)
    umActivity.start()
}
