import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_013"

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
    arr = inputs.get("array", [])

    if not isinstance(arr, list):
        logging.error("Invalid input: 'array' must be a list.")
        sys.exit(1)

    logging.info("Array elements are:")
    for i, val in enumerate(arr):
        logging.info(f"Element {i + 1}: {val}")

if __name__ == "__main__":
    main()
