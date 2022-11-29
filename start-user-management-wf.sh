#!/usr/bin/env bash
set -x
set -e
mvn package exec:java -Dexec.mainClass="com.xpansiv.simulators.wf.WF_UMgmtClient" -Dexec.args="$1 $2 $3"
