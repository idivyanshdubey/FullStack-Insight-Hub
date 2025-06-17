import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_028"

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
    day = inputs.get("day", "").lower()

    if not isinstance(day, str):
        logging.error("Invalid input: 'day' must be a string.")
        sys.exit(1)

    logging.info(f"Input day: {day}")

    match day:
        case "monday":
            logging.info("Matched Monday")
            print("1")
        case "tuesday":
            logging.info("Matched Tuesday")
            print("2")
        case _:
            logging.info("No match found")
            print("not a day")

if __name__ == "__main__":
    main()
