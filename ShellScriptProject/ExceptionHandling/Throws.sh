#!/bin/bash

# Logging function
log_info() {
    echo "[INFO] $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Function that simulates throwing an exception
fun() {
    log_info "Inside fun()."
    return 1  # Simulate an exception being thrown
}

# Main logic that "catches" the exception
fun
if [ $? -ne 0 ]; then
    log_info "Caught in main."
fi

# Pause to keep terminal open if run by double-click
read -p "Press enter to exit"
