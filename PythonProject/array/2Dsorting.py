import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_010"

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

def main():
    inputs = load_input(SCRIPT_ID)
    rows = inputs.get("rows", 0)
    cols = inputs.get("cols", 0)
    matrix = inputs.get("matrix", [])

    if rows != len(matrix) or any(len(row) != cols for row in matrix):
        logging.error("Matrix dimensions do not match the provided rows and columns.")
        sys.exit(1)

    for row in matrix:
        row.sort()

    logging.info("Sorted matrix (row-wise):")
    for row in matrix:
        logging.info(" ".join(map(str, row)))

if __name__ == "__main__":
    main()
