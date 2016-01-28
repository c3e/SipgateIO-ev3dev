#!/usr/bin/env bash

source src/main/scripts/.ssh-password
scp src/main/scripts/runSipgateIO-ev3dev.sh $LOGIN@$IP:~/
scp target/sipgateIOEV3dev-1.0-SNAPSHOT-jar-with-dependencies.jar $LOGIN@$IP:~/
ssh $LOGIN@$IP 'chmod +x runSipgateIO-ev3dev.sh && chmod +x sipgateIOEV3dev-1.0-SNAPSHOT-jar-with-dependencies.jar'
