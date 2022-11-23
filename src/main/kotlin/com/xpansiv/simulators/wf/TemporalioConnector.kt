package com.xpansiv.simulators.wf

import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowClientOptions
import io.temporal.serviceclient.WorkflowServiceStubs
import io.temporal.serviceclient.WorkflowServiceStubsOptions
import io.temporal.worker.WorkerFactory

class TemporalioConnector(val host_port: String = "localhost:7233", val namespace:String = "seajug" , createFactory: Boolean = true) {

    val service = WorkflowServiceStubs.newServiceStubs(
        WorkflowServiceStubsOptions.newBuilder()
            .setTarget(host_port)
            .build()
    )
    val client = WorkflowClient.newInstance(service, WorkflowClientOptions.newBuilder().setNamespace(namespace).build())

    var factory: WorkerFactory? =
        if (createFactory) {
            WorkerFactory.newInstance(client)
        } else {
            null
        }

}
