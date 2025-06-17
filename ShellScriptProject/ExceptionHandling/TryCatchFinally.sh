#!/bin/bash

# Logging function
log_info() {
    echo "[INFO] $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Example 1: Basic try-catch simulation
log_info "Example 1: Basic Arithmetic Check"
{
    result=$((10 / 0))
    log_info "$result"
} 2>/dev/null || log_info "Exception caught: Division by zero"
log_info "I will always execute"

echo

# Example 2: Multiple catch simulation
log_info "Example 2: Multiple Error Types"
{
    result=$((10 / 0))
    log_info "$result"

    s=""
    log_info "String length: ${#s}"
} 2>/dev/null || log_info "Caught ArithmeticException or NullPointerException"

echo

# Example 3: Nested try-catch simulation
log_info "Example 3: Nested Try-Catch"
{
    log_info "Outer try block started"

    {
        n=10
        result=$((n / 0))
        log_info "$result"
    } 2>/dev/null || log_info "Caught ArithmeticException in inner try-catch"

    {
        s=""
        log_info "String length: ${#s}"
    } 2>/dev/null || log_info "Caught NullPointerException in inner try-catch"

} || log_info "Caught exception in outer try-catch"
log_info "Finally block executed"

# Pause to keep terminal open if run by double-click
read -p "Press enter to exit"
