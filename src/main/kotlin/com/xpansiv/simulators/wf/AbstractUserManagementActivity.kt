package com.xpansiv.simulators.wf

import com.xpansiv.wf.user_management.UserChangeCommand
import com.xpansiv.wf.user_management.UserChangeResult
import com.xpansiv.wf.user_management.UserManagementActivity

abstract class AbstractUserManagementActivity(val temporalio: TemporalioConnector, val activityQueue:String ) : UserManagementActivity {

    fun start() {
        val factory = temporalio.factory!!
        val tioWorker =  factory.newWorker( activityQueue)
        tioWorker.registerActivitiesImplementations( this )
        factory.start()
    }

    override fun execute(cmd: UserChangeCommand): UserChangeResult {
        println("${this::class.java.simpleName} got = " + JsonHelper.toJsonString( cmd) )
        _execute(cmd)
        return  UserChangeResult().setStatus("OK")
    }

    open fun _execute( cmd: UserChangeCommand) {}

}
