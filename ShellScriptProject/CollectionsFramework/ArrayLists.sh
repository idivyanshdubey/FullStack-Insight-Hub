#!/bin/bash

echo "[INFO] Starting ArrayList simulation..."

declare -a myList
declare -a myList1

read -p "How many elements do you want to add? " n
echo "Enter $n integers:"
for ((i = 0; i < n; i++)); do
  read -p "Element $((i + 1)): " val
  myList+=("$val")
done

# Add 111 at index 0
myList=("111" "${myList[@]}")

# Copy to another list
myList1=("${myList[@]}")

echo "[INFO] Original List: ${myList[*]}"
echo "[INFO] Copied List1: ${myList1[*]}"

# Update an element
read -p "Enter index to update: " updateIndex
read -p "Enter new value: " newValue
if [[ "$updateIndex" -ge 0 && "$updateIndex" -lt "${#myList[@]}" ]]; then
  myList[$updateIndex]=$newValue
fi
echo "[INFO] Updated List: ${myList[*]}"

# Remove by index
read -p "Enter index to remove: " removeIndex
if [[ "$removeIndex" -ge 0 && "$removeIndex" -lt "${#myList[@]}" ]]; then
  unset 'myList[removeIndex]'
  myList=("${myList[@]}")
fi

# Remove last and first if not empty
if [ ${#myList[@]} -gt 0 ]; then unset 'myList[-1]'; fi
if [ ${#myList[@]} -gt 0 ]; then unset 'myList[0]'; fi
myList=("${myList[@]}")

echo "[INFO] After Removing: ${myList[*]}"

# Access element at index 3
if [ ${#myList[@]} -gt 3 ]; then
  echo "[INFO] Element at index 3: ${myList[3]}"
fi

# Check contains
read -p "Enter element to check if it exists: " checkElement
found=false
for item in "${myList[@]}"; do
  if [[ "$item" == "$checkElement" ]]; then
    found=true
    break
  fi
done
echo "[INFO] Contains $checkElement? $found"

# indexOf and lastIndexOf
read -p "Enter element to find index of: " findIndex
firstIndex=-1
lastIndex=-1
for i in "${!myList[@]}"; do
  if [[ "${myList[$i]}" == "$findIndex" ]]; then
    if [[ "$firstIndex" -eq -1 ]]; then firstIndex=$i; fi
    lastIndex=$i
  fi
done
echo "[INFO] Index of $findIndex: $firstIndex"
echo "[INFO] Last index of $findIndex: $lastIndex"

# Check if empty
if [ ${#myList[@]} -eq 0 ]; then
  echo "[INFO] Is the list empty? true"
else
  echo "[INFO] Is the list empty? false"
fi

# Size
echo "[INFO] Size of the list: ${#myList[@]}"

# Sublist (1 to 3)
if [ ${#myList[@]} -ge 4 ]; then
  echo -n "[INFO] Sub List (1 to 3): "
  for ((i = 1; i <= 3; i++)); do
    echo -n "${myList[$i]} "
  done
  echo ""
fi

# Clear list
myList=()
echo "[INFO] After clearing the list: ${myList[*]}"
