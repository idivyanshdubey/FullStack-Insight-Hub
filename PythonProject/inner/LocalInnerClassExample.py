import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_030"

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
class OuterClass12:
    def __init__(self, message):
        self.message = message

    def method_with_inner_class(self):
        logging.info("Inside method_with_inner_class")

        # Local inner class defined inside a method
        class LocalInnerClass:
            def display(inner_self):
                logging.info(f"Inner class message: {self.message}")
                print(self.message)

        # Create an instance of the local inner class and call its method
        inner = LocalInnerClass()
        inner.display()

def main():
    inputs = load_input(SCRIPT_ID)
    message = inputs.get("message", "Hello from Local Inner Class")

    outer = OuterClass12(message)
    outer.method_with_inner_class()

if __name__ == "__main__":
    main()
