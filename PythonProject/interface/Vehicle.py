import json
import logging
import sys
import os
from abc import ABC, abstractmethod

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_036"

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

    @staticmethod
    def run():
        logging.info("Static method with body")
        print("Static method with body")

    def drive(self):
        logging.info("Default-like method in Python")
        print("Default-like method in Python")

    def _internal_check(self):
        logging.info("Private-like method")
        print("Private-like method")

# Concrete class
class Car(Vehicle):
    def start(self):
        logging.info("Car is starting...")
        print("Car is starting...")

def main():
    _ = load_input(SCRIPT_ID)  # No specific input needed for this script

    car = Car()
    car.start()
    car.drive()
    car._internal_check()
    Car.run()

if __name__ == "__main__":
    main()
