#!/bin/bash

# Function to log messages in the desired format
log() {
    echo "[INFO] | $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Define file paths
input_file="/c/Workspace/ShellScriptProject/IOStreams/input1.txt"
output_file="/c/Workspace/ShellScriptProject/IOStreams/output1.txt"

# Check if input file exists
if [ ! -f "$input_file" ]; then
    echo "[ERROR] | $(date '+%Y-%m-%d %H:%M:%S') - Input file does not exist: $input_file" >&2
    exit 1
fi

log "Starting byte-level copy from $input_file to $output_file"

# Copy the file byte by byte
dd if="$input_file" of="$output_file" bs=1 status=none

# Confirm success
if [ $? -eq 0 ]; then
    log "Data copied successfully using byte-level copying!"
else
    echo "[ERROR] | $(date '+%Y-%m-%d %H:%M:%S') - An error occurred during copying." >&2
fi
