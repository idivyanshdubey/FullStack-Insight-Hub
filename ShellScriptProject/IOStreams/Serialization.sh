#!/bin/bash

# Function to log messages in the desired format
log() {
    echo "[INFO] | $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Define the file to store serialized data
filename="/c/Workspace/ShellScriptProject/IOStreams/person.txt"

# Prompt user for input using Bash's read command
log "Prompting user for name and age"
echo "Enter name:"
read name

echo "Enter age:"
read age

# Serialization: Save data to file
echo "$name" > "$filename"
echo "$age" >> "$filename"
log "Object serialized successfully to $filename"

# Deserialization: Read data from file
if [ -f "$filename" ]; then
    read deserialized_name < "$filename"
    read deserialized_age < <(tail -n 1 "$filename")
    log "Object deserialized successfully"
    echo
    log "Deserialized Data: Name: $deserialized_name, Age: $deserialized_age"
else
    echo "[ERROR] | $(date '+%Y-%m-%d %H:%M:%S') - Deserialization error: File not found." >&2
fi
