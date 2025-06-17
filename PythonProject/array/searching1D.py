import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_015"

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
    array = inputs.get("array", [])
    key = inputs.get("key")

    if not isinstance(array, list) or not all(isinstance(x, int) for x in array):
        logging.error("Invalid input: 'array' must be a list of integers.")
        sys.exit(1)

    if not isinstance(key, int):
        logging.error("Invalid input: 'key' must be an integer.")
        sys.exit(1)

    logging.info(f"Array: {array}")
    logging.info(f"Key to search: {key}")

    found = False
    for i, val in enumerate(array):
        if val == key:
            logging.info(f"Found at index: {i}")
            print(f"Found at index: {i}")
            found = True
            break

    if not found:
        logging.info("Not found.")
        print("Not found.")

if __name__ == "__main__":
    main()
