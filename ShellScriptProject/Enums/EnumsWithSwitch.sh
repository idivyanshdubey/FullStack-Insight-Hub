#!/bin/bash

# Logging function
log_info() {
    echo "[INFO] $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Prompt user for color input
read -p "Enter a color (RED, GREEN, BLUE): " color

# Convert to uppercase for case-insensitive matching
color_upper=$(echo "$color" | tr '[:lower:]' '[:upper:]')

# Switch-case equivalent in shell
case "$color_upper" in
    "RED")
        log_info "Red color observed"
        ;;
    "GREEN")
        log_info "Green color observed"
        ;;
    "BLUE")
        log_info "Blue color observed"
        ;;
    *)
        log_info "Other color observed"
        ;;
esac
