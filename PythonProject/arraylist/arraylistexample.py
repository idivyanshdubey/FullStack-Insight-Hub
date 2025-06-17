import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_016"

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
    fruits = inputs.get("fruits", [])
    view_index = inputs.get("view_index", -1)
    remove_index = inputs.get("remove_index", -1)

    if not isinstance(fruits, list) or not all(isinstance(f, str) for f in fruits):
        logging.error("Invalid input: 'fruits' must be a list of strings.")
        sys.exit(1)

    logging.info(f"Initial list of fruits: {fruits}")

    # View fruit at a specific index
    if 0 <= view_index < len(fruits):
        logging.info(f"Fruit at index {view_index}: {fruits[view_index]}")
        print(f"Fruit at index {view_index}: {fruits[view_index]}")
    else:
        logging.warning("Invalid index for viewing.")
        print("Invalid index.")

    # Remove fruit at a specific index
    if 0 <= remove_index < len(fruits):
        removed = fruits.pop(remove_index)
        logging.info(f"Removed '{removed}' from the list.")
        print(f"Removed '{removed}' from the list.")
    else:
        logging.warning("Invalid index for removal.")
        print("Invalid index. No fruit removed.")

    # Display updated list
    print("Updated list of fruits:", fruits)
    logging.info(f"Updated list: {fruits}")

    # Display size
    print("Size of the list:", len(fruits))

    # Iterate through the list
    print("Fruits in the list:")
    for fruit in fruits:
        print(fruit)

    # Accessing a fruit using index 1
    if len(fruits) > 1:
        print("Using get:", fruits[1])
    else:
        print("Not enough elements to access index 1.")

if __name__ == "__main__":
    main()
