package com.xpansiv.simulators.wf

import com.xpansiv.wf.user_management.WFUserManagement
import com.xpansiv.wf.user_management.WFUserManagementImpl

class WFRunner(val temporalio:TemporalioConnector, val wfQueue:String) {
    fun start() {
        val factory = temporalio.factory!!
        val tioWorker =  factory.newWorker( wfQueue)
        tioWorker.registerWorkflowImplementationTypes( WFUserManagementImpl::class.java)
        factory.start()
    }
}


fun main() {
    val tioc = TemporalioConnector()
    val wfRunner = WFRunner(tioc, WFUserManagement.WF_TASKS_QUEUE)
    wfRunner.start()
}
