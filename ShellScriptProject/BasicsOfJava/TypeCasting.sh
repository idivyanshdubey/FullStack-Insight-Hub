#!/bin/bash

echo "[INFO] === Widening Type Casting ==="
read -p "Enter an integer: " i
l=$i
d=$i
echo "[INFO] Integer: $i"
echo "[INFO] Long: $l"
echo "[INFO] Double: $d"

echo ""
echo "[INFO] === Narrowing Type Casting ==="
read -p "Enter a double value: " i
j=$(printf "%.0f" "$i")  # Simulate short
k=$(printf "%.0f" "$i")  # Simulate int
echo "[INFO] Original Value before Casting: $i"
echo "[INFO] After Type Casting to short: $j"
echo "[INFO] After Type Casting to int: $k"

echo ""
echo "[INFO] === Inheritance and Downcasting Simulation ==="
read -p "Enter developer name: " dev_name
read -p "Enter developer salary: " dev_salary
echo "[INFO] $dev_name is working"
echo "[INFO] $dev_name is writing code"

echo ""
echo "[INFO] === Upcasting Example ==="
echo "[INFO] The dog barks"

echo ""
echo "[INFO] === Downcasting Example ==="
echo "[INFO] The animal is eating."
echo "[INFO] The cat is meowing."
