import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_022"

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

def auto_unbox_example(boxed_int, num1, num2):
    logging.info(f"Autoboxed value: {boxed_int}")
    print("Autoboxed value:", boxed_int)

    primitive_int = boxed_int
    logging.info(f"Unboxed value: {primitive_int}")
    print("Unboxed value:", primitive_int)

    sum_result = num1 + num2
    logging.info(f"Sum of {num1} and {num2}: {sum_result}")
    print("Sum:", sum_result)

def main():
    inputs = load_input(SCRIPT_ID)
    boxed_int = inputs.get("boxed_int")
    num1 = inputs.get("num1")
    num2 = inputs.get("num2")

    if not all(isinstance(x, int) for x in [boxed_int, num1, num2]):
        logging.error("All inputs must be integers.")
        sys.exit(1)

    auto_unbox_example(boxed_int, num1, num2)

if __name__ == "__main__":
    main()
