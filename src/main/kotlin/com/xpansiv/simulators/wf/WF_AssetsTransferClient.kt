package com.xpansiv.simulators.wf

import com.xpansiv.wf.assets_transfer.WFAssetsTransfer
import com.xpansiv.wf.assets_transfer.WFAssetsTransferConst
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions

class WF_AssetsTransferClient(val tioc: TemporalioConnector) {

    fun wfStub():WFAssetsTransfer {
        return tioc.client.newWorkflowStub(
            WFAssetsTransfer::class.java,
            WorkflowOptions.newBuilder()
                .setTaskQueue(WFAssetsTransferConst.WF_TASKS_QUEUE)
                .build()
        )
    }


    fun startTransfer(senderId: String, recipientId: String, ein: String, quantity: Long) {
        val wfExecution = WorkflowClient.start(wfStub()::transferAssets, senderId, recipientId, ein, quantity)
        println("Started workflow: ${wfExecution.workflowId}")
    }
}



fun main(args:Array<String>) {
    if (args.isEmpty()) {
        println("No arguments supplied")
    } else {
        val wfClient = WF_AssetsTransferClient(TemporalioConnector())
        println("starting workflow for ${args.joinToString()}")
        wfClient.startTransfer(args[0], args[1],args[2], args[3].toLong())
    }
    System.exit(0)
}

