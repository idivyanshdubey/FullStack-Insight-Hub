#!/bin/bash

# Function to simulate logger.info
log_info() {
    echo "[INFO] $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Check if three arguments are provided
if [ "$#" -ne 3 ]; then
    log_info "Usage: $0 <space_delimited_string> <colon_delimited_string> <colon_delimited_with_delimiters>"
    exit 1
fi

input=$1
input2=$2
input3=$3

# --- Basic Tokenization with space delimiter ---
IFS=' ' read -ra tokens <<< "$input"
log_info "Tokens using space delimiter:"
for token in "${tokens[@]}"; do
    log_info "$token"
done

# --- Tokenization with custom delimiter ---
IFS=':' read -ra tokens2 <<< "$input2"
log_info "Tokens using ':' delimiter:"
for token in "${tokens2[@]}"; do
    log_info "$token"
done

# --- Tokenization with delimiter included (simulated) ---
log_info "Tokens including delimiters:"
echo "$input3" | sed -E 's/([:])/ \1 /g' | tr -s ' ' '\n' | while read -r token; do
    log_info "$token"
done
