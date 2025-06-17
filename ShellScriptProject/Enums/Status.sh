#!/bin/bash

# Logging function
log_info() {
    echo "[INFO] $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Simulate enum values and their descriptions using associative arrays
declare -A StatusDescriptions=(
    ["SUCCESS"]="Operation was successful"
    ["FAILURE"]="Operation failed"
    ["IN_PROGRESS"]="Operation is in progress"
)

# Iterate over the enum-like keys and print their descriptions
for status in "${!StatusDescriptions[@]}"; do
    log_info "$status: ${StatusDescriptions[$status]}"
done

# Optional pause to keep terminal open if run by double-click
read -p "Press enter to exit"
