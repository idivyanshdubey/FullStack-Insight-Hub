#!/bin/bash

# Function to log messages in the desired format
log() {
    echo "[INFO] | $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Simulating the static nested class with a top-level function
staticNestedClass_displayMessage() {
    log "Inside staticNestedClass_displayMessage function"
    echo "Hello from the Static Nested Class!"
    log "Displayed message from staticNestedClass_displayMessage"
}

# Simulating the main method
main() {
    log "Main function started"
    staticNestedClass_displayMessage
    log "Main function ended"
}

# Run the main function
log "Script started"
main
log "Script ended"
