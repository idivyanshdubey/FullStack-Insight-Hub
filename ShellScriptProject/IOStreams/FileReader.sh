#!/bin/bash

# Function to log messages in the desired format
log() {
    echo "[INFO] | $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Define the input file path
input_file="/c/Workspace/ShellScriptProject/IOStreams/input1.txt"

# Check if the input file exists
if [ ! -f "$input_file" ]; then
    echo "[ERROR] | $(date '+%Y-%m-%d %H:%M:%S') - Input file does not exist: $input_file" >&2
    exit 1
fi

log "Starting character-by-character reading from $input_file"

# Read and print the file character by character
while IFS= read -r -n1 char; do
    printf "%s" "$char"
done < "$input_file"

# Print a newline before the final log
echo
log "Finished reading file"
