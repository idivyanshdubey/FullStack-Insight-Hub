import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_031"

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

# Outer class
class OuterClass:
    def __init__(self, message):
        self.message = message

    # Member inner class
    class InnerClass:
        def __init__(self, outer_instance):
            self.outer = outer_instance  # Reference to the outer class instance

        def display(self):
            logging.info(f"Accessing outer class message: {self.outer.message}")
            print(self.outer.message)

def main():
    inputs = load_input(SCRIPT_ID)
    message = inputs.get("message", "Hello from OuterClass")

    outer = OuterClass(message)
    inner = OuterClass.InnerClass(outer)
    inner.display()
    inner.display()

if __name__ == "__main__":
    main()
