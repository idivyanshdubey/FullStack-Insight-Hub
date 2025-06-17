import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_024"

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

    num = inputs.get("num")
    num1 = inputs.get("num1")
    decimal_val = inputs.get("decimal_val")

    if not all(isinstance(x, (int, float)) for x in [num, num1, decimal_val]):
        logging.error("All inputs must be numbers (int or float).")
        sys.exit(1)

    # Implicit conversions
    decimal = float(num)
    logging.info(f"Implicit conversion of {num} to float: {decimal}")
    print(decimal)

    dec = float(num1)
    logging.info(f"Implicit conversion of {num1} to float: {dec}")
    print(dec)

    # Explicit conversions
    num_from_float = int(decimal_val)
    logging.info(f"Explicit conversion of {decimal_val} to int: {num_from_float}")
    print(num_from_float)

    num2 = float(num1)
    logging.info(f"Explicit conversion of {num1} to float: {num2}")
    print(num2)

if __name__ == "__main__":
    main()
