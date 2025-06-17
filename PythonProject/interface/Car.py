import json
import logging
import sys
import os
from abc import ABC, abstractmethod

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_032"

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

# Abstract base class
class Vehicle(ABC):
    @abstractmethod
    def start(self):
        pass

# Concrete subclass
class Car(Vehicle):
    def start(self):
        logging.info("Car starts.")
        print("Car starts.")

    def run(self):
        logging.info("Car is running.")
        print("running")

def main():
    inputs = load_input(SCRIPT_ID)
    action = inputs.get("action", "start")

    car = Car()

    if action == "start":
        car.start()
    elif action == "run":
        car.run()
    else:
        logging.warning(f"Unknown action: {action}")
        print("Unknown action.")

if __name__ == "__main__":
    main()
