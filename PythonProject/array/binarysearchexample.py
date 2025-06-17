
import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_011"

# Locate the input file in the parent directory under 'inputs/inputs.json'
INPUT_FILE = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), 'inputs', 'inputs.json')

def load_input(script_id):
    try:
        with open(INPUT_FILE, 'r') as f:
            data = json.load(f)
        logging.info(f"Loaded input for script ID: {script_id}")
        return data.get(script_id, {})
    except Exception as e:
        logging.error(f"Failed to load input: {e}")
        sys.exit(1)

def binary_search(arr, target):
    low, high = 0, len(arr) - 1
    while low <= high:
        mid = low + (high - low) // 2
        if arr[mid] == target:
            return mid
        elif arr[mid] < target:
            low = mid + 1
        else:
            high = mid - 1
    return -1

def main():
    inputs = load_input(SCRIPT_ID)
    print("Loaded inputs:", inputs)  # Debug print

    arr = inputs.get("array", [])
    target = inputs.get("target")

    print("Array:", arr)
    print("Target:", target)

    result = binary_search(arr, target)

    if result != -1:
        message = f"Element {target} found at index: {result}"
    else:
        message = f"Element {target} not found in the array."

    logging.info(message)
    print(message)

if __name__ == "__main__":
    main()
