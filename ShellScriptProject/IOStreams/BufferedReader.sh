#!/bin/bash

# Function to log messages in the desired format
log() {
    echo "[INFO] | $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

inputFile="/c/Workspace/ShellScriptProject/IOStreams/input1.txt"

if [[ -r "$inputFile" ]]; then
    log "Reading from file: $inputFile"
    
    # Remove carriage returns and read line by line
    while IFS= read -r line || [[ -n "$line" ]]; do
        echo "$line"
    done < <(tr -d '\r' < "$inputFile")

    log "Finished reading file"
else
    echo "[ERROR] | $(date '+%Y-%m-%d %H:%M:%S') - Cannot read file at $inputFile" >&2
fi
