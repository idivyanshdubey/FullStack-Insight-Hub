#!/bin/bash

echo "[INFO] Starting TreeSet simulation..."

declare -A treeSet
declare -A treeSetTwo
declare -A treeSetInt

# First TreeSet
read -p "How many elements do you want to add to the first TreeSet? " n
for ((i = 0; i < n; i++)); do
  read -p "Enter element $((i + 1)): " val
  treeSet["$val"]=1
done

echo -n "[INFO] TreeSet: "
for key in "${!treeSet[@]}"; do echo "$key"; done | sort

# Second TreeSet
read -p "How many elements do you want to add to the second TreeSet? " m
for ((i = 0; i < m; i++)); do
  read -p "Enter element $((i + 1)): " val
  treeSetTwo["$val"]=1
done

# Merge sets
for key in "${!treeSetTwo[@]}"; do
  treeSet["$key"]=1
done

echo -n "[INFO] Merged TreeSet: "
for key in "${!treeSet[@]}"; do echo "$key"; done | sort

# Integer TreeSet
read -p "How many integers do you want to add to the numeric TreeSet? " k
for ((i = 0; i < k; i++)); do
  read -p "Enter integer $((i + 1)): " val
  treeSetInt["$val"]=1
done

echo -n "[INFO] Numeric TreeSet: "
for key in "${!treeSetInt[@]}"; do echo "$key"; done | sort -n

read -p "Enter a number to find its ceiling: " ceilingInput
ceilingValue=""
for key in $(for k in "${!treeSetInt[@]}"; do echo "$k"; done | sort -n); do
  if [[ "$key" -ge "$ceilingInput" ]]; then
    ceilingValue=$key
    break
  fi
done
if [[ -n "$ceilingValue" ]]; then
  echo "[INFO] Ceiling value for $ceilingInput: $ceilingValue"
else
  echo "[INFO] No ceiling value found for $ceilingInput"
fi

# Check for specific element
read -p "Enter a string to check if it exists in the TreeSet: " checkElement
if [[ -n "${treeSet[$checkElement]}" ]]; then
  echo "[INFO] Does the TreeSet contain '$checkElement'? true"
else
  echo "[INFO] Does the TreeSet contain '$checkElement'? false"
fi

# Check if empty
if [ ${#treeSet[@]} -eq 0 ]; then
  echo "[INFO] Is the TreeSet empty? true"
else
  echo "[INFO] Is the TreeSet empty? false"
fi

# Iterate through TreeSet
echo "[INFO] The iterator values are:"
for key in "${!treeSet[@]}"; do echo "$key"; done | sort

# Clear TreeSet
treeSet=()
echo "[INFO] After clearing TreeSet: {}"
