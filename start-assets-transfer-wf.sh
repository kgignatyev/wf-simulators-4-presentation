#!/usr/bin/env bash
set -x
mvn exec:java -Dexec.mainClass="com.xpansiv.simulators.wf.WF_AssetsTransferClientKt" -Dexec.args="$1 $2 $3 $4"
