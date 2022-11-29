package com.xpansiv.simulators.wf

import com.xpansiv.wf.assets_transfer.WFAssetsTransfer
import com.xpansiv.wf.assets_transfer.WFAssetsTransferConst
import io.temporal.client.WorkflowOptions
import org.apache.commons.io.FileUtils
import org.apache.commons.io.IOUtils
import java.io.File
import java.io.FileWriter

class WFVisualizer (val tioc: TemporalioConnector) {

    fun wfStub(): WFAssetsTransfer {
        return tioc.client.newWorkflowStub(
            WFAssetsTransfer::class.java,
            WorkflowOptions.newBuilder()
                .setTaskQueue(WFAssetsTransferConst.WF_TASKS_QUEUE)
                .build()
        )
    }

    fun renderWFState( wfID:String){
        //this is the example of using untyped WF stub
        val wfStub = tioc.client.newUntypedWorkflowStub( wfID )
        val stateName = wfStub.query( "currentStateName", String::class.java)
        val dotFileContent = wfStub.query( "describeWorkflowInDot", String::class.java)
        println( dotFileContent)
        val dotFileName = "wf-description.dot"
        FileUtils.write( File("target",dotFileName), dotFileContent)
        println( stateName)
        visualizeState( stateName, dotFileName, "${stateName}.png")
    }

    @Throws(java.lang.Exception::class)
    private fun visualizeState(fullWfStateName: String, dotFileName:String, pngFileName: String) {
        val stateName = fullWfStateName.split("[.]".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()[1]
        val p = Runtime.getRuntime().exec(
            arrayOf(
                "docker", "run", "--rm",
                "-v", System.getProperty("user.dir") + "/target:/dot/data",
                "xpansiv.jfrog.io/default-docker-virtual/xpansiv-smcdot:1",
                "/usr/bin/dot_file2png.py",
                "--in", "data/$dotFileName",
                "--out", "data/$pngFileName",
                "--state", stateName
            )
        )
        p.waitFor()
        val output = IOUtils.toString(p.inputStream)
        val errorOutput = IOUtils.toString(p.errorStream)
        println(output)
        System.err.println(errorOutput)
        if (p.exitValue() != 0) {
            throw java.lang.Exception("png generation error")
        }
    }
}

fun main() {

    val wfID = System.getenv("WF_ID")
    val wfVisualizer =  WFVisualizer(TemporalioConnector())
    wfVisualizer.renderWFState(wfID )
    System.exit(0)

}
