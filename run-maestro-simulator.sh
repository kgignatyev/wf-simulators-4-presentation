#!/usr/bin/env bash
mvn package
mvn exec:java -Dexec.mainClass="com.xpansiv.simulators.wf.MaestroUserManagementImplKt"
