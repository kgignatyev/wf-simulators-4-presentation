0. Temporal overview

1. Simple workflow (unbounded sequential)

demo with unbounded workflow

 1.0.13

2. Simple workflow - bounded

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
