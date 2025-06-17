import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_008"

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

# Simulating a source-level annotation using a no-op decorator
def source_annotation(info="This is a source annotation"):
    def decorator(func):
        return func
    return decorator

# Class with methods
class Emp:
    def dis(self):
        logging.info("playing")

    @source_annotation(info="Method for testing")
    def test(self):
        logging.info("Executing test method.")

def main():
    inputs = load_input(SCRIPT_ID)
    methods_to_call = inputs.get("methods_to_call", [])

    e = Emp()
    for method_name in methods_to_call:
        method = getattr(e, method_name, None)
        if method:
            method()

if __name__ == "__main__":
    main()
