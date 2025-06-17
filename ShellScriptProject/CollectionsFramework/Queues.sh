#!/bin/bash

echo "[INFO] Starting Queue simulation..."

declare -a queue

read -p "How many elements do you want to add to the queue? " n
for ((i = 0; i < n; i++)); do
  read -p "Enter element $((i + 1)): " element
  queue+=("$element")
done

echo "[INFO] Initial Queue: ${queue[*]}"

# Remove an element
read -p "Enter an element to remove from the queue: " toRemove
for i in "${!queue[@]}"; do
  if [[ "${queue[$i]}" == "$toRemove" ]]; then
    unset 'queue[i]'
    queue=("${queue[@]}")  # Re-index
    break
  fi
done
echo "[INFO] After Remove: ${queue[*]}"

# Iterate through the queue
echo "[INFO] Iterating through the queue:"
for item in "${queue[@]}"; do
  echo "[INFO] $item"
done

# Poll and peek
if [ ${#queue[@]} -gt 0 ]; then
  echo "[INFO] Top element (poll): ${queue[0]}"
  queue=("${queue[@]:1}")
else
  echo "[INFO] Queue is empty, nothing to poll."
fi

if [ ${#queue[@]} -gt 0 ]; then
  echo "[INFO] Peeked element: ${queue[0]}"
else
  echo "[INFO] Queue is empty, nothing to peek."
fi

# Check for presence
read -p "Enter an element to check if it exists: " toCheck
found=false
for item in "${queue[@]}"; do
  if [[ "$item" == "$toCheck" ]]; then
    found=true
    break
  fi
done
echo "[INFO] Queue contains \"$toCheck\": $found"

# Check if empty
if [ ${#queue[@]} -eq 0 ]; then
  echo "[INFO] Queue is empty: true"
else
  echo "[INFO] Queue is empty: false"
fi

# Retrieve head without removing
if [ ${#queue[@]} -gt 0 ]; then
  echo "[INFO] Head of queue: ${queue[0]}"
else
  echo "[INFO] Queue is empty, no head element."
fi

# Offer a new element
read -p "Enter an element to offer to the queue: " offerElement
queue+=("$offerElement")
echo "[INFO] Element added: true"

# Size and final state
echo "[INFO] Size of Queue: ${#queue[@]}"
echo "[INFO] Final Queue: ${queue[*]}"
