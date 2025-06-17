#!/bin/bash

echo "[INFO] Starting Deque simulation..."

declare -a deque

read -p "How many elements do you want to add to the deque? " n
for ((i = 0; i < n; i++)); do
  read -p "Enter element $((i + 1)): " val
  read -p "Add to (1) Front or (2) Back? " choice
  if [[ "$choice" == "1" ]]; then
    deque=("$val" "${deque[@]}")
  else
    deque+=("$val")
  fi
done

echo "[INFO] Deque: ${deque[*]}"

# Check for an element
read -p "Enter a number to check if it exists in the deque: " check
found=false
for item in "${deque[@]}"; do
  if [[ "$item" == "$check" ]]; then
    found=true
    break
  fi
done
echo "[INFO] Does the deque contain '$check'? $found"

# Reverse iteration
echo "[INFO] Deque in reverse order:"
for ((i = ${#deque[@]} - 1; i >= 0; i--)); do
  echo "[INFO] ${deque[$i]}"
done

# Access head and tail
if [ ${#deque[@]} -gt 0 ]; then
  echo "[INFO] Deque's head (element): ${deque[0]}"
  echo "[INFO] Deque's head (getFirst): ${deque[0]}"
  echo "[INFO] Deque's tail (getLast): ${deque[-1]}"
fi

# Forward iteration
echo "[INFO] Deque in forward order:"
for item in "${deque[@]}"; do
  echo "[INFO] $item"
done

# Offer to end
read -p "Enter a number to offer to the end: " offerVal
deque+=("$offerVal")
echo "[INFO] Inserted $offerVal at the end."

# Offer to front
read -p "Enter a number to offer to the front: " offerFirstVal
deque=("$offerFirstVal" "${deque[@]}")
echo "[INFO] Inserted $offerFirstVal at the front."

# Offer to back
read -p "Enter a number to offer to the back: " offerLastVal
deque+=("$offerLastVal")
echo "[INFO] Inserted $offerLastVal at the back."

echo "[INFO] Final Deque: ${deque[*]}"
