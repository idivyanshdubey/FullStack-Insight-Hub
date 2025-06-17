#!/bin/bash

declare -A hashset

echo "[INFO] Starting HashSet simulation..."

read -p "How many elements do you want to add to the HashSet? " n
echo "Enter $n integers:"
for ((i = 0; i < n; i++)); do
  read -p "Element $((i+1)): " element
  if [[ -z "${hashset[$element]}" ]]; then
    hashset[$element]=1
    echo "[INFO] Added $element? true"
  else
    echo "[INFO] Added $element? false"
  fi
done

echo -n "[INFO] HashSet: "
for key in "${!hashset[@]}"; do
  echo -n "$key "
done
echo ""

read -p "Enter a number to check if it exists in the HashSet: " check
if [[ -n "${hashset[$check]}" ]]; then
  echo "[INFO] HashSet contains $check? true"
else
  echo "[INFO] HashSet contains $check? false"
fi

read -p "Enter a number to remove from the HashSet: " remove
unset hashset[$remove]
echo -n "[INFO] HashSet after removing $remove: "
for key in "${!hashset[@]}"; do
  echo -n "$key "
done
echo ""

if [ ${#hashset[@]} -eq 0 ]; then
  echo "[INFO] Is the HashSet empty? true"
else
  echo "[INFO] Is the HashSet empty? false"
fi

echo "[INFO] Size of HashSet: ${#hashset[@]}"

echo "[INFO] The iterator values are:"
for key in "${!hashset[@]}"; do
  echo -n "$key "
done
echo ""

hashset=()
echo "[INFO] HashSet After clear(): {}"
