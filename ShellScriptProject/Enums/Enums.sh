#!/bin/bash

# Logging function using echo
log_info() {
    echo "[INFO] $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Simulating enum using variables or arrays
COLORS=("RED" "GREEN" "BLUE")

# Parse command-line arguments
while getopts "d:c:p:" opt; do
  case $opt in
    d) DAY_INPUT="$OPTARG" ;;
    c) COLOR_INPUT="$OPTARG" ;;
    p) PHASE_INPUT="$OPTARG" ;;
    *) echo "Usage: $0 -d <DAY> -c <COLOR> -p <PHASE>"; exit 1 ;;
  esac
done

# Example 1: Print a color
log_info "Example 1:"
c1="RED"
log_info "$c1"

# Example 2: Enum inside a class (simulated as a function)
log_info "Example 2:"
function test_color {
    local color="RED"
    log_info "$color"
}
test_color

# Example 3: Enum in switch-case
log_info "Example 3:"
function day_is_like {
    local day="$1"
    case "$day" in
        "MONDAY")
            log_info "Mondays are bad."
            ;;
        "FRIDAY")
            log_info "Fridays are better."
            ;;
        "SATURDAY"|"SUNDAY")
            log_info "Weekends are best."
            ;;
        *)
            log_info "Midweek days are so-so."
            ;;
    esac
}
day_is_like "${DAY_INPUT:-MONDAY}"

# Example 4: Iterating over enum values
log_info "Example 4:"
DAYS=("Monday" "Tuesday" "Wednesday")
for day in "${DAYS[@]}"; do
    log_info "$day"
done

# Example 5: Enum in switch-case with colors
log_info "Example 5:"
function color_observed {
    local color="$1"
    case "$color" in
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
}
color_observed "${COLOR_INPUT:-YELLOW}"

# Example 6: Enum with constructor-like behavior
log_info "Example 6:"
function cicd_phase {
    local phase="$1"
    log_info "Constructor called for: $phase"
    log_info "Starting Phase"
}
cicd_phase "${PHASE_INPUT:-SOURCE}"
