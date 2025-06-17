#!/bin/bash

# Function to log messages with timestamp
log_info() {
    echo "[INFO] $(date '+%Y-%m-%d %H:%M:%S') $1"
}

# Function to simulate age validation
validate_age() {
    local age=$1
    if [ "$age" -lt 18 ]; then
        log_info "Caught Exception: Age must be 18 or above."
        return 1
    else
        log_info "Valid age: $age"
    fi
}

# Ask user for age
read -p "Enter your age: " user_age

# Validate the input
validate_age "$user_age"

# Pause to keep terminal open if run by double-click
read -p "Press enter to exit"
