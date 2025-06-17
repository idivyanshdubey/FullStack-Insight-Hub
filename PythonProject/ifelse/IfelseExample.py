import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_025"

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
    number = inputs.get("number")

    if not isinstance(number, (int, float)):
        logging.error("Invalid input: 'number' must be an integer or float.")
        sys.exit(1)

    logging.info(f"Input number: {number}")

    if number > 0:
        logging.info("The number is positive.")
        print("The number is positive.")
    else:
        logging.info("The number is not positive.")
        print("The number is not positive.")

if __name__ == "__main__":
    main()
