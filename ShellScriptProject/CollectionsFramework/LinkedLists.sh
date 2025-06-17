#!/bin/bash

echo "[INFO] Starting LinkedList simulation..."

declare -a l

read -p "How many elements do you want to add? " n
for ((i = 0; i < n; i++)); do
  read -p "Enter element $((i + 1)): " element
  l+=("$element")
done

echo "[INFO] LinkedList: ${l[*]}"

# Update an element
read -p "Enter index to update: " updateIndex
if [[ "$updateIndex" -ge 0 && "$updateIndex" -lt "${#l[@]}" ]]; then
  read -p "Enter new value: " newValue
  l[$updateIndex]=$newValue
  echo "[INFO] Updated LinkedList: ${l[*]}"
else
  echo "[INFO] Invalid index."
fi

# Remove by index
read -p "Enter index to remove: " removeIndex
if [[ "$removeIndex" -ge 0 && "$removeIndex" -lt "${#l[@]}" ]]; then
  unset 'l[removeIndex]'
  l=("${l[@]}")  # Re-index
  echo "[INFO] After index removal: ${l[*]}"
else
  echo "[INFO] Invalid index."
fi

# Remove by value
read -p "Enter value to remove: " valueToRemove
for i in "${!l[@]}"; do
  if [[ "${l[$i]}" == "$valueToRemove" ]]; then
    unset 'l[i]'
    l=("${l[@]}")
    break
  fi
done
echo "[INFO] After value removal: ${l[*]}"

# Insert at index 1
read -p "Enter a value to insert at index 1: " insertValue
if [[ "${#l[@]}" -ge 1 ]]; then
  l=("${l[0]}" "$insertValue" "${l[@]:1}")
else
  l+=("$insertValue")
fi

# Add at the end
read -p "Enter another value to add at the end: " endValue
l+=("$endValue")

# Display using loop
echo "[INFO] Final LinkedList:"
for item in "${l[@]}"; do
  echo -n "$item "
done
echo ""

# Display size
echo "[INFO] The size of the linked list is: ${#l[@]}"
