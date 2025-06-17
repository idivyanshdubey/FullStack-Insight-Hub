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

log "Starting character-level copy from $input_file to $output_file"

# Empty the output file if it exists
> "$output_file"
log "Cleared contents of output file"

# Read and write character by character
while IFS= read -r -n1 char; do
    printf "%s" "$char" >> "$output_file"
done < "$input_file"

log "Data copied successfully using character-level copying!"
