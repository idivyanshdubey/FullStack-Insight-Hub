#!/bin/bash

echo "[INFO] Starting Employee Management Simulation..."

declare -a ids
declare -a names
declare -a salaries

read -p "How many employees do you want to add? " count

for ((i = 0; i < count; i++)); do
  read -p "Enter ID for employee $((i + 1)): " id
  read -p "Enter name: " name
  read -p "Enter salary: " salary

  ids+=("$id")
  names+=("$name")
  salaries+=("$salary")
done

echo ""
echo "[INFO] Employees:"
for ((i = 0; i < count; i++)); do
  echo "[INFO] Employee{id=${ids[$i]}, name='${names[$i]}', salary=${salaries[$i]}}"
done

# Sort by salary
sorted_indices=($(for i in "${!salaries[@]}"; do echo "$i ${salaries[$i]}"; done | sort -k2 -n | awk '{print $1}'))

echo ""
echo "[INFO] Employees sorted by salary:"
for i in "${sorted_indices[@]}"; do
  echo "[INFO] Employee{id=${ids[$i]}, name='${names[$i]}', salary=${salaries[$i]}}"
done

# Update salary
read -p "Enter the name of the employee to update salary: " updateName
updated=false
for i in "${!names[@]}"; do
  if [[ "${names[$i],,}" == "${updateName,,}" ]]; then
    read -p "Enter new salary: " newSalary
    salaries[$i]=$newSalary
    updated=true
    break
  fi
done
if ! $updated; then
  echo "[INFO] Employee not found."
fi

# Delete employee by ID
read -p "Enter the ID of the employee to delete: " deleteId
deleted=false
for i in "${!ids[@]}"; do
  if [[ "${ids[$i]}" == "$deleteId" ]]; then
    unset 'ids[i]'
    unset 'names[i]'
    unset 'salaries[i]'
    ids=("${ids[@]}")
    names=("${names[@]}")
    salaries=("${salaries[@]}")
    deleted=true
    break
  fi
done
if ! $deleted; then
  echo "[INFO] Employee with ID $deleteId not found."
fi

# Final list
echo ""
echo "[INFO] Employees after update and delete:"
for i in "${!ids[@]}"; do
  echo "[INFO] Employee{id=${ids[$i]}, name='${names[$i]}', salary=${salaries[$i]}}"
done
