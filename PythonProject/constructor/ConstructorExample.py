import json
import logging
import sys
import os

# Configure logging
logging.basicConfig(level=logging.INFO, format='%(asctime)s - %(levelname)s - %(message)s')

SCRIPT_ID = "script_021"

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

# Car class with optional constructor
class Car:
    def __init__(self, car_color=None):
        self.color = car_color

    def display(self):
        logging.info(f"The car color is: {self.color}")
        print(f"The car color is: {self.color}")

# Car2This class demonstrating use of 'self'
class Car2This:
    def __init__(self, color):
        self.color = color

    def display(self):
        logging.info(f"Color: {self.color}")
        print(f"Color: {self.color}")

def main():
    inputs = load_input(SCRIPT_ID)
    car_color = inputs.get("car_color")
    car2_color = inputs.get("car2_color")

    my_car = Car(car_color)
    my_car.display()

    another_car = Car2This(car2_color)
    another_car.display()

if __name__ == "__main__":
    main()
