#!/bin/bash

echo "[INFO] Starting DataTypes.sh..."

# Integer input
read -p "Enter first integer: " a
read -p "Enter second integer: " b
echo "[INFO] Sum: $((a + b))"

# Boolean input
read -p "Is Java fun? (true/false): " b1
read -p "Is fish tasty? (true/false): " b2
echo "[INFO] Is Java fun? $b1"
echo "[INFO] Is fish tasty? $b2"

# Byte input
read -p "Enter age (byte): " h
read -p "Enter temperature (byte): " t
echo "[INFO] Age: $h"
echo "[INFO] Temperature: $t"

# Short input
read -p "Enter number of students (short): " num
read -p "Enter temperature (short): " s
echo "[INFO] Number of Students: $num"
echo "[INFO] Temperature: $s"

# Long input
read -p "Enter world population (long): " w
read -p "Enter light year distance (long): " l
echo "[INFO] World Population: $w"
echo "[INFO] Light Year Distance: $l"

# Float input
read -p "Enter value of Pi (float): " pi
read -p "Enter gravity (float): " gravity
echo "[INFO] Value of Pi: $pi"
echo "[INFO] Gravity: $gravity"

# Double input
read -p "Enter value of Pi1 (double): " pi1
read -p "Enter Avogadro's number (double): " an
echo "[INFO] Value of Pi1: $pi1"
echo "[INFO] Avogadro's Number: $an"

# Char input
read -p "Enter grade (char): " g
read -p "Enter symbol (char): " f
echo "[INFO] Grade: $g"
echo "[INFO] Symbol: $f"

# String input
read -p "Enter name: " n
read -p "Enter message: " m
echo "[INFO] Name: $n"
echo "[INFO] Message: $m"

# Array input
echo "Enter 5 integers:"
read -p "1: " arr0
read -p "2: " arr1
read -p "3: " arr2
read -p "4: " arr3
read -p "5: " arr4
echo "Enter 3 strings:"
read -p "1: " str1
read -p "2: " str2
read -p "3: " str3
echo "[INFO] First Number: $arr0"
echo "[INFO] Second String: $str2"

# Car object input
read -p "Enter car model: " model
read -p "Enter car year: " year
echo "[INFO] Car Model: $model"
echo "[INFO] Car Year: $year"

echo "[INFO] DataTypes.sh completed."
