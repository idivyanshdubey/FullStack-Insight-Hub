import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_040"

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
    a = inputs.get("a", 0)
    b = inputs.get("b", 1)  # Avoid division by zero

    logging.info(f"Addition: {a + b}")
    logging.info(f"Subtraction: {a - b}")
    logging.info(f"Multiplication: {a * b}")
    logging.info(f"Division: {a / b}")
    logging.info(f"Modulo: {a % b}")

    logging.info("Exiting...")

if __name__ == "__main__":
    main()
