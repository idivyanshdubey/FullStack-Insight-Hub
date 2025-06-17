import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_027"

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
    start = inputs.get("start", 1)
    end = inputs.get("end", 6)
    stop = inputs.get("stop", 3)

    if not all(isinstance(x, int) for x in [start, end, stop]):
        logging.error("Inputs 'start', 'end', and 'stop' must be integers.")
        sys.exit(1)

    for i in range(start, end):
        if i == stop:
            logging.info(f"Breaking loop at count {i}")
            break
        logging.info(f"Count: {i}")
        print("Count:", i)

if __name__ == "__main__":
    main()
