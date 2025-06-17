#!/bin/bash

# Function to log messages in the desired format
log() {
    echo "[INFO] | $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Define the output file path
output_file="/c/Workspace/ShellScriptProject/IOStreams/output1.txt"

# Prompt the user for input
log "Prompting user for input"
echo "Enter the data you want to write to the file (press Enter when done):"
read user_input

# Write the input to the file
echo "$user_input" > "$output_file"
log "User input written to $output_file"

# Confirm success on a new line
echo
log "Data written successfully to the file"
