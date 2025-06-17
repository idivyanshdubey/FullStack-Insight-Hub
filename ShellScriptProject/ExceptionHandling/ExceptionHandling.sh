#!/bin/bash

# Logging function
log_info() {
    echo "[INFO] $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Example 1: Basic try-catch simulation
log_info "Example 1: Basic Arithmetic Check"
{
    result=$((10 / 0))
} 2>/dev/null || log_info "Exception caught: Division by zero"
log_info "I will always execute"

echo

# Example 2: Multiple catch simulation
log_info "Example 2: Multiple Error Types"
{
    result=$((10 / 0))
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
    } 2>/dev/null || log_info "Caught ArithmeticException in inner try-catch"

    {
        s=""
        log_info "String length: ${#s}"
    } 2>/dev/null || log_info "Caught NullPointerException in inner try-catch"

} || log_info "Caught exception in outer try-catch"
log_info "Finally block executed"

echo

# Example 4: Throw and rethrow simulation
log_info "Example 4: Throw and Rethrow"
fun() {
    log_info "Caught inside fun()"
    return 1
}
fun || log_info "Caught in main"

echo

# Example 5: Throws simulation
log_info "Example 5: Throws Simulation"
fun2() {
    log_info "Inside fun()"
    return 1
}
fun2 || log_info "Caught in main"

echo

# Example 6: Custom Checked Exception Simulation
log_info "Example 6: Age Validation"
validate_age() {
    local age=$1
    if [ "$age" -lt 18 ]; then
        log_info "Caught Exception: Age must be 18 or above."
        return 1
    else
        log_info "Valid age: $age"
    fi
}
validate_age 12

echo

# Example 7: Custom Unchecked Exception Simulation
log_info "Example 7: Division with Custom Exception"
divide() {
    local a=$1
    local b=$2
    if [ "$b" -eq 0 ]; then
        log_info "Caught Exception: Division by zero is not allowed."
        return 1
    else
        log_info "Result: $((a / b))"
    fi
}
divide 10 0

# Pause to keep terminal open if run by double-click
read -p "Press enter to exit"
