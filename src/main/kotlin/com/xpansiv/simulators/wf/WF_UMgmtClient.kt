package com.xpansiv.simulators.wf

import com.xpansiv.wf.user_management.*
import io.temporal.client.WorkflowClient
import io.temporal.client.WorkflowOptions

class WF_UMgmtClient(val temporalio: TemporalioConnector) {

    fun createUser(userId: String, userName: String) {
        val cmd = UserChangeCommand().setUserInfo(
            UserInfo().setId(userId).setName(userName)
        ).setOperation(UserOperation.CREATE)
        executeWorkflowFor(cmd)
    }

    fun updateUser(userId: String, userName: String) {
        val cmd = UserChangeCommand().setUserInfo(
            UserInfo().setId(userId).setName(userName)
        ).setOperation(UserOperation.UPDATE)
        executeWorkflowFor(cmd)
    }

    fun blockUser(userId: String) {
        val cmd = UserChangeCommand().setUserInfo(
            UserInfo().setId(userId)
        ).setOperation(UserOperation.BLOCK)
        executeWorkflowFor(cmd)
    }

    fun unblockUser(userId: String) {
        val cmd = UserChangeCommand().setUserInfo(
            UserInfo().setId(userId)
        ).setOperation(UserOperation.UN_BLOCK)
        executeWorkflowFor(cmd)
    }

    private fun executeWorkflowFor(cmd: UserChangeCommand) {
        println("Executing: " + JsonHelper.toJsonString(cmd))

        val wfStub = temporalio.client.newWorkflowStub( WFUserManagement::class.java,
            WorkflowOptions.newBuilder()
                .setTaskQueue(WFUserManagement.WF_TASKS_QUEUE)
                .setWorkflowId( cmd.userInfo.id) //we can reuse workflow ID
                .build()
        )
// synchronized WF start
//            wfStub.executeCommand(cmd)
//async start
        val wfExecution = WorkflowClient.start(wfStub::executeCommand, cmd)
        println("Started workflow: ${wfExecution.workflowId}")
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            try {
                val tioc = TemporalioConnector(createFactory = false)
                val wfClient = WF_UMgmtClient(tioc)
                if (args.isEmpty()) {
                    println("No arguments supplied")
                    System.exit(1)
                } else {
                    println("starting workflow for ${args.joinToString()}")
                    val op: String = args[0]
                    when (op) {
                        "create-user" -> wfClient.createUser(args[1], args[2])
                        "update-user" -> wfClient.updateUser(args[1], args[2])
                        "block-user" -> wfClient.blockUser(args[1])
                        "unblock-user" -> wfClient.unblockUser(args[1])
                    }
                }

            } catch (e: Exception) {
                e.printStackTrace()
                System.exit(1)

            }
            System.exit(0)
        }
    }
}

