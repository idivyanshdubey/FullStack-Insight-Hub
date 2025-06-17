import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_033"

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

# Interface-like class
class MathOperation:
    def operate(self, a, b):
        pass

def main():
    inputs = load_input(SCRIPT_ID)
    a = inputs.get("a")
    b = inputs.get("b")

    if not all(isinstance(x, (int, float)) for x in [a, b]):
        logging.error("Inputs 'a' and 'b' must be numbers.")
        sys.exit(1)

    # Lambda function as implementation
    addition = lambda a, b: a + b
    result = addition(a, b)

    logging.info(f"Addition of {a} and {b} is {result}")
    print(result)

if __name__ == "__main__":
    main()
