#!/bin/bash

# Logging function
log_info() {
    echo "[INFO] $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Function to simulate division with error handling
divide() {
    local a=$1
    local b=$2

    if [ "$b" -eq 0 ]; then
        log_info "Caught Exception: Division by zero is not allowed."
        return 1
    else
        result=$((a / b))
        log_info "Result: $result"
    fi
}

# Main logic
divide 10 0

# Optional pause to keep terminal open if run by double-click
read -p "Press enter to exit"
