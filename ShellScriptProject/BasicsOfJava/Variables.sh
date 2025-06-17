#!/bin/bash

echo "[INFO] === Local Variables Example ==="
read -p "Enter a value for x: " x
read -p "Enter a message: " message
echo "[INFO] x = $x"
echo "[INFO] message = $message"

if [ "$x" -gt 5 ]; then
  result="x is greater than 5"
  echo "[INFO] $result"
fi

for i in 0 1 2; do
  loopMessage="Iteration $i"
  echo "[INFO] $loopMessage"
done

echo ""
echo "[INFO] === Instance Variables Example ==="
read -p "Enter developer name: " devName
developer=$devName
i=0
I="null"
echo "[INFO] Developer name is: $developer"
echo "[INFO] Default value for int is $i"
echo "[INFO] Default value for Integer is $I"

echo ""
echo "[INFO] === Static Variable Example ==="
read -p "Enter geek name: " geek
echo "[INFO] Geek Name is : $geek"

echo ""
echo "[INFO] === Static vs Instance Counter Example ==="
staticCounter=0
instanceCounter1=0
instanceCounter2=0

# Simulate object creation
((staticCounter++))
((instanceCounter1++))

((staticCounter++))
((instanceCounter2++))

echo "[INFO] Static Counter: $staticCounter"
echo "[INFO] Instance Counter of obj1: $instanceCounter1"
echo "[INFO] Instance Counter of obj2: $instanceCounter2"
