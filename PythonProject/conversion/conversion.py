import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_023"

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
    str_number = inputs.get("str_number")

    if not isinstance(str_number, str):
        logging.error("Invalid input: 'str_number' must be a string.")
        sys.exit(1)

    # String to int
    num = int(str_number)
    logging.info(f"String to int: {num}")
    print("String to int:", num)

    # Primitive to wrapper (Python treats all as objects)
    float_num = float(num)
    logging.info(f"Primitive to wrapper (float): {float_num}")
    print("Primitive to wrapper:", float_num)

    # Wrapper to string
    str_val = str(float_num)
    logging.info(f"Wrapper to string: {str_val}")
    print("Wrapper to string:", str_val)

    # Wrapper to primitive (same object in Python)
    primitive_float = float_num
    logging.info(f"Wrapper to primitive: {primitive_float}")
    print("Wrapper to primitive:", primitive_float)

if __name__ == "__main__":
    main()
