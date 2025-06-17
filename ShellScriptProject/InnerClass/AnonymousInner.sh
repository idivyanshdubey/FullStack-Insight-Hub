#!/bin/bash

# Function to log messages in the desired format
log() {
    echo "[INFO]  $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Function simulating the anonymous inner class behavior
displayMessage() {
    log "Entering displayMessage function"

    show() {
        log "Inside show function"
        echo "Hello from the Anonymous Inner Class!"
        log "Displayed message from show function"
    }

    show

    log "Exiting displayMessage function"
}

# Main execution
log "Script started"
displayMessage
log "Script ended"
