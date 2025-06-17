import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_012"

# Locate the input file in the parent directory under 'inputs/inputs.json'
INPUT_FILE = os.path.join(os.path.dirname(os.path.dirname(os.path.abspath(__file__))), 'inputs', 'inputs.json')

def load_input(script_id):
    try:
        with open(INPUT_FILE, 'r') as f:
            data = json.load(f)
        return data.get(script_id, {})
    except Exception as e:
        logging.error(f"Failed to load input: {e}")
        sys.exit(1)

def bubble_sort(arr):
    n = len(arr)
    for i in range(n - 1):
        swapped = False
        for j in range(n - 1 - i):
            if arr[j] > arr[j + 1]:
                arr[j], arr[j + 1] = arr[j + 1], arr[j]
                swapped = True
        if not swapped:
            break

def main():
    inputs = load_input(SCRIPT_ID)

    if SCRIPT_ID in ["script_009", "script_010"]:
        rows = inputs.get("rows", 0)
        cols = inputs.get("cols", 0)
        matrix = inputs.get("matrix", [])

        if rows != len(matrix) or any(len(row) != cols for row in matrix):
            logging.error("Matrix dimensions do not match the provided rows and columns.")
            sys.exit(1)

        logging.info("Matrix:")
        for row in matrix:
            logging.info(" ".join(map(str, row)))

    elif SCRIPT_ID == "script_012":
        array = inputs.get("array", [])
        logging.info(f"Original array: {' '.join(map(str, array))}")
        bubble_sort(array)
        logging.info(f"Sorted array: {' '.join(map(str, array))}")

    else:
        logging.info(f"No specific logic for {SCRIPT_ID}")

if __name__ == "__main__":
    main()
