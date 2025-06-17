import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_007"

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

# Simulating a CLASS-level annotation using a decorator
def class_info(author="Unknown", version=""):
    def decorator(cls):
        cls._author = author
        cls._version = version
        return cls
    return decorator

def main():
    inputs = load_input(SCRIPT_ID)
    author = inputs.get("author", "Unknown")
    version = inputs.get("version", "")

    @class_info(author=author, version=version)
    class SampleClass:
        def display(self):
            logging.info("This is SampleClass.")

    s = SampleClass()
    s.display()
    # Metadata (_author, _version) is attached but not accessed

if __name__ == "__main__":
    main()
