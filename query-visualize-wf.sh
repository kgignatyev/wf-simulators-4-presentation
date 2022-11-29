set -x
tctl  --ns seajug  workflow query --workflow_id "${WF_ID}" --query_type "currentStateName"
tctl  --ns seajug  workflow query --workflow_id "${WF_ID}" --query_type "describeWorkflowInDot"
