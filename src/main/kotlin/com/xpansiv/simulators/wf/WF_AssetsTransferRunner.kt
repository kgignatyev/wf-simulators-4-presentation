package com.xpansiv.simulators.wf

import com.xpansiv.wf.assets_transfer.CounterpartsInfoSupplier
import com.xpansiv.wf.assets_transfer.WFAssetsTransferConst
import com.xpansiv.wf.assets_transfer.WFAssetsTransferImpl
import com.xpansiv.wf.user_management.WFUserManagementImpl

class CounterpartsInfoSupplierFakeImpl : CounterpartsInfoSupplier {

    var _orgAutoApprovesTransfers = true

    override fun orgAutoApprovesTransfers(orgId: String): Boolean {
        println("orgAutoApprovesTransfers $orgId -> $_orgAutoApprovesTransfers")
        return _orgAutoApprovesTransfers
    }

}

class WF_AssetsTransferRunner(val temporalio:TemporalioConnector) {
    fun start() {
        val factory = temporalio.factory!!
        val tioWorker =  factory.newWorker( WFAssetsTransferConst.WF_TASKS_QUEUE)
        tioWorker.registerWorkflowImplementationTypes( WFAssetsTransferImpl::class.java)
        val activityWorker = factory.newWorker(WFAssetsTransferConst.TASKS_QUEUE )
        activityWorker.registerActivitiesImplementations(  *arrayOf( CounterpartsInfoSupplierFakeImpl()))
        factory.start()
    }
}


fun main() {
    val tioc = TemporalioConnector()
    val wfRunner = WF_AssetsTransferRunner(tioc)
    wfRunner.start()
}
