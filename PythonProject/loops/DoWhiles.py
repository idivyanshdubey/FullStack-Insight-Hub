import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_001"

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
    choices = inputs.get("choices", [])
    index = 0

    while index < len(choices):
        logging.info("Menu:")
        logging.info("1. Option A")
        logging.info("2. Option B")
        logging.info("3. Exit")

        choice = choices[index]
        logging.info(f"User selected: {choice}")
        index += 1

        if choice == 3:
            break

    logging.info("Exiting...")

if __name__ == "__main__":
    main()
