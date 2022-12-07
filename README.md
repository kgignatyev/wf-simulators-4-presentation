0. Xpansiv overview
    * A bit about carbon offsets and renewable energy credits
    * Challenge of integration: external and internal systems
    * Everything is evolving
1. Temporal overview ( https://docs.temporal.io/workflows )
   2. concepts
   3. queues without pain of managing them
   4. state management and even sourcing
   5. workflows are unit testable!!!
2. Simple workflow (unbounded sequential)
   * have local temporal cluster up with ```docker-compose up``` UI is at 
   * demo with unbounded workflow 1.0.13
```shell
./start-user-management-wf.sh 
./run-ema-simulator.sh
./run-maestro-simulator.sh
./run-xmarkets-simulator.sh

./start-user-management-wf.sh create-user u1 User1
```
* stop maestro
* start workflow 
```shell
./start-user-management-wf.sh update-user u1 User1
```
show 'running workflow' and retries (if intentionally break maestro implementation)
start maestro, demo that workflow recovered

1. Simple workflow - bounded

demo failing workflow with 1.0.14
start workflow while maestro simulator fails

3. Simple workflow - retry failed workflow

https://docs.temporal.io/tctl-v1/workflow#--reset_type

event ID should be the one before first failure

    tctl -ns seajug  workflow reset --reason 'kgi'  --event_id 10 \
      --workflow_id e946d3b1-d2cd-4911-8aed-da36c7a3aad6 \
      --run_id fb11eafa-4e54-4156-8b61-dc9cf523c631 \
      --reset_reapply_type None


4. Parallel processing workflow

workflow 1.0.15

 demo that stuck activity in the middle does not prevent other systems from receiving notifications

 2nd demo: same stuck activity and use of queries (keep refreshing UI while starting downed activity )

5. WorkflowID, RunID, sync and async execution of workflows
   6. demo of code and explanation
6. Signals and Queries, running a FSM (Final State Machine)
   7. demo of asset transfer workflow
8. Brief overview of SCM library for writing FSMs
9. Teaser of other Temporal.io capabilities: Saga, Eternal Workflows, Child workflows
