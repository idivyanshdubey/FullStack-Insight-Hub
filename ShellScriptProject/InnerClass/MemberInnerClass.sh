#!/bin/bash

# Function to log messages in the desired format
log() {
    echo "[INFO]  $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Outer function simulating the outer class method
display() {
    log "Entering display function"

    # Inner function simulating the local inner class
    showMessage() {
        log "Inside showMessage function"
        echo "Hello from the Local Inner Class!"
        log "Displayed message from showMessage function"
    }

    # Calling the inner function
    showMessage

    log "Exiting display function"
}

# Main execution simulating the main method
log "Script started"
display
log "Script ended"
