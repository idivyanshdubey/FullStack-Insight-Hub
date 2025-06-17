import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_039"

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

class Example:
    def __init__(self):
        print("Instance block executed")
        print("Constructor executed")

def main():
    inputs = load_input(SCRIPT_ID)
    count = inputs.get("create_objects", 1)

    for i in range(count):
        logging.info(f"Creating object {i + 1}")
        Example()

    logging.info("Exiting...")

if __name__ == "__main__":
    main()
