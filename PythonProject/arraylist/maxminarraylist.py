import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_017"

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
    numbers = inputs.get("numbers", [])

    if not isinstance(numbers, list) or not all(isinstance(x, int) for x in numbers):
        logging.error("Invalid input: 'numbers' must be a list of integers.")
        sys.exit(1)

    logging.info(f"Input numbers: {numbers}")

    maximum = max(numbers)
    minimum = min(numbers)

    logging.info(f"Maximum value: {maximum}")
    logging.info(f"Minimum value: {minimum}")

    print("Maximum value:", maximum)
    print("Minimum value:", minimum)

if __name__ == "__main__":
    main()
