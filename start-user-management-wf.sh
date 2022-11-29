#!/usr/bin/env bash
set -x
mvn exec:java -Dexec.mainClass="com.xpansiv.simulators.wf.WF_UMgmtStarter" -Dexec.args="$1 $2 $3"
