#!/bin/bash

echo "[INFO] Starting HashMap simulation..."

declare -A hashmap

# Add entries
read -p "How many entries do you want to add? " n
for ((i = 0; i < n; i++)); do
  read -p "Enter key (integer): " key
  read -p "Enter value (string): " value
  hashmap[$key]="$value"
done

echo "[INFO] Initial Map:"
for k in "${!hashmap[@]}"; do
  echo "[INFO] Key: $k, Value: ${hashmap[$k]}"
done

# Update a value
read -p "Enter key to update its value: " updateKey
if [[ -n "${hashmap[$updateKey]}" ]]; then
  read -p "Enter new value: " newValue
  hashmap[$updateKey]="$newValue"
  echo "[INFO] Updated Map:"
  for k in "${!hashmap[@]}"; do
    echo "[INFO] Key: $k, Value: ${hashmap[$k]}"
  done
else
  echo "[INFO] Key not found."
fi

# Remove a key
read -p "Enter key to remove: " removeKey
unset hashmap[$removeKey]
echo "[INFO] Map after removal:"
for k in "${!hashmap[@]}"; do
  echo "[INFO] Key: $k, Value: ${hashmap[$k]}"
done

# Iterate through map
echo "[INFO] Map entries:"
for k in "${!hashmap[@]}"; do
  echo "[INFO] Key: $k, Value: ${hashmap[$k]}"
done

# Check for key
read -p "Enter key to check: " checkKey
if [[ -n "${hashmap[$checkKey]}" ]]; then
  echo "[INFO] Is the key '$checkKey' present? true"
else
  echo "[INFO] Is the key '$checkKey' present? false"
fi

# Check for value
read -p "Enter value to check: " checkValue
found=false
for v in "${hashmap[@]}"; do
  if [[ "$v" == "$checkValue" ]]; then
    found=true
    break
  fi
done
echo "[INFO] Is the value '$checkValue' present? $found"

# Get value by key
read -p "Enter key to get its value: " getKey
echo "[INFO] The value is: ${hashmap[$getKey]}"

# Clear the map
hashmap=()
echo "[INFO] After clear(): {}"
