#!/bin/bash

echo "[INFO] Starting Iterators simulation..."

read -p "How many elements do you want to add to the list? " n
declare -a al

echo "Enter $n elements:"
for ((i = 0; i < n; i++)); do
  read -p "Element $((i+1)): " element
  al+=("$element")
done

echo ""
echo "[INFO] Iterating through the list:"
for item in "${al[@]}"; do
  echo "[INFO] $item"
done

read -p "Enter index to start iterating from: " startIndex
if [[ "$startIndex" -ge 0 && "$startIndex" -lt "${#al[@]}" ]]; then
  echo "[INFO] Iterating from index $startIndex:"
  for ((i = startIndex; i < ${#al[@]}; i++)); do
    echo "[INFO] ${al[i]}"
  done
else
  echo "[INFO] Invalid index."
fi
