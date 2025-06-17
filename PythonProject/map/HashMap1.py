import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_037"

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

    # Convert string keys to integers for consistency
    input_map = {int(k): v for k, v in inputs.get("map", {}).items()}
    retrieve_key = inputs.get("retrieve_key")
    check_key = inputs.get("check_key")
    check_value = inputs.get("check_value")
    remove_key = inputs.get("remove_key")

    # Retrieve a value using a key
    logging.info(f"Value for key {retrieve_key}: {input_map.get(retrieve_key)}")

    # Check if a key or value exists
    logging.info(f"Contains key {check_key}? {'Yes' if check_key in input_map else 'No'}")
    logging.info(f"Contains value '{check_value}'? {'Yes' if check_value in input_map.values() else 'No'}")

    # Remove a key-value pair
    if remove_key in input_map:
        input_map.pop(remove_key)
        logging.info(f"Removed key {remove_key}. Updated Map: {input_map}")
    else:
        logging.info(f"Key {remove_key} not found. No removal performed.")

    logging.info("Exiting...")

if __name__ == "__main__":
    main()
