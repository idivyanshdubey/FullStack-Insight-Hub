import json
import logging
import sys
import os
import gc

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_038"

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

class GarbageCollectionExample:
    def __del__(self):
        print("Garbage collector called!")

def main():
    inputs = load_input(SCRIPT_ID)

    if inputs.get("simulate_gc"):
        obj = GarbageCollectionExample()
        obj = None  # Eligible for garbage collection
        collected = gc.collect()
        logging.info(f"Garbage collection invoked. Objects collected: {collected}")
    else:
        logging.info("Garbage collection simulation skipped.")

    logging.info("Exiting...")

if __name__ == "__main__":
    main()
